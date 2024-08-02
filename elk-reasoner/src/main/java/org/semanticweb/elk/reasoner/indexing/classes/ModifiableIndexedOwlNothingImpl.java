package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.RevertibleAction;

/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2016 Department of Computer Science, University of Oxford
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

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.reasoner.completeness.Feature;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassEntity;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPredefinedClass;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;

/**
 * Represents {@code owl:Nothing}.
 * 
 * @author Yevgeny Kazakov
 *
 */
final class ModifiableIndexedOwlNothingImpl
		extends StructuralIndexedClassEntryImpl
		implements ModifiableIndexedClass, IndexedPredefinedClass {

	ModifiableIndexedOwlNothingImpl(ElkClass entity) {
		super(entity);
	}

	@Override
	public RevertibleAction getIndexingAction(ModifiableOntologyIndex index,
			OccurrenceIncrement increment) {
		return super.getIndexingAction(index, increment)
				.then(RevertibleAction.create(() -> {
					index.occurrenceChanged(Feature.OWL_NOTHING_POSITIVE,
							increment.positiveIncrement);
					return true;
				}, () -> index.occurrenceChanged(Feature.OWL_NOTHING_POSITIVE,
						-increment.positiveIncrement)));
	}

	@Override
	public <O> O accept(IndexedClassEntity.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
