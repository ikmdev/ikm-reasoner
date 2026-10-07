/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2012 Department of Computer Science, University of Oxford
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.reasoner.ProgressMonitor;
import org.semanticweb.elk.reasoner.ReasonerComputationWithInputs;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.taxonomy.model.NonBottomTaxonomyNode;
import org.semanticweb.elk.reasoner.taxonomy.model.UpdateableTaxonomy;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentExecutor;
import org.semanticweb.elk.util.concurrent.computation.DelegateInterruptMonitor;
import org.semanticweb.elk.util.concurrent.computation.InputProcessor;
import org.semanticweb.elk.util.concurrent.computation.InputProcessorFactory;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * Cleans the class taxonomy concurrently.
 *
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 */
public class TaxonomyCleaning
		extends
		ReasonerComputationWithInputs<IndexedClass, TaxonomyCleaningFactory> {

	public TaxonomyCleaning(final Collection<IndexedClass> inputs,
			final InterruptMonitor interrupter,
			final UpdateableTaxonomy<ElkClass> classTaxonomy,
			final ConcurrentExecutor executor, final int maxWorkers,
			final ProgressMonitor progressMonitor) {
		super(inputs, new TaxonomyCleaningFactory(interrupter, classTaxonomy),
				executor, maxWorkers, progressMonitor);
	}

}

/**
 *
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 */
class TaxonomyCleaningFactory extends DelegateInterruptMonitor
		implements
		InputProcessorFactory<IndexedClass, InputProcessor<IndexedClass>> {

	private final UpdateableTaxonomy<ElkClass> classTaxonomy_;

	TaxonomyCleaningFactory(final InterruptMonitor interrupter,
			final UpdateableTaxonomy<ElkClass> classTaxonomy) {
		super(interrupter);
		classTaxonomy_ = classTaxonomy;
	}

	@Override
	public InputProcessor<IndexedClass> getEngine() {
		return new InputProcessor<IndexedClass>() {

			@Override
			public void submit(IndexedClass indexedClass) {
				final ElkClass elkClass = indexedClass.getElkEntity();

				if (elkClass == classTaxonomy_.getBottomNode()
						.getCanonicalMember()) {
					return;
				}

				/*
				 * shouldn't modify the set of members and iterate over them (to
				 * mark as modified) at the same time
				 */
				synchronized (classTaxonomy_.getBottomNode()) {
					if (classTaxonomy_.removeFromBottomNode(elkClass)) {
						return;
					}
				}

				final NonBottomTaxonomyNode<ElkClass> node = classTaxonomy_
						.getNonBottomNode(elkClass);

				if (node == null) {
					return;
				}

				classTaxonomy_.removeDirectSupernodes(node);

				// add all its direct satisfiable sub-nodes to the queue
				final List<NonBottomTaxonomyNode<ElkClass>> subNodes;
				synchronized (node) {
					subNodes = new ArrayList<NonBottomTaxonomyNode<ElkClass>>(
							node.getDirectNonBottomSubNodes());
				}
				for (NonBottomTaxonomyNode<ElkClass> subNode : subNodes) {
					classTaxonomy_.removeDirectSupernodes(subNode);
				}

				classTaxonomy_.removeNode(node.getCanonicalMember());
			}

			@Override
			public void process() {
				// Currently does nothing.
				// TODO: The work done in submit should be moved here.
			}

			@Override
			public void finish() {
				// nothing to do
			}

		};
	}

	@Override
	public void finish() {
		// nothing to do
	}

}
