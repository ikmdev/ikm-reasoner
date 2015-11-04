/**
 * 
 */
package org.semanticweb.elk.reasoner.saturation.inferences;

/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2014 Department of Computer Science, University of Oxford
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

import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.implementation.BackwardLinkImpl;
import org.semanticweb.elk.reasoner.saturation.conclusions.implementation.ContradictionImpl;
import org.semanticweb.elk.reasoner.saturation.conclusions.interfaces.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.interfaces.Contradiction;
import org.semanticweb.elk.reasoner.saturation.inferences.visitors.ContradictionInferenceVisitor;

/**
 * Represents a {@link Contradiction} produced via a propagation over a
 * {@link BackwardLink}.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 */
public class PropagatedContradiction extends AbstractContradictionInference {

	private final IndexedObjectProperty premiseRelation_;

	private final IndexedContextRoot inferenceRoot_;

	public PropagatedContradiction(IndexedContextRoot inferenceRoot,
			IndexedObjectProperty relation, IndexedContextRoot conclusionRoot) {
		super(conclusionRoot);
		premiseRelation_ = relation;
		inferenceRoot_ = inferenceRoot;
	}

	public PropagatedContradiction(BackwardLink premise) {
		this(premise.getConclusionRoot(), premise.getBackwardRelation(),
				premise.getOriginRoot());
	}

	@Override
	public IndexedContextRoot getInferenceRoot() {
		return inferenceRoot_;
	}

	public BackwardLink getLinkPremise() {
		return new BackwardLinkImpl(getInferenceRoot(), premiseRelation_,
				getConclusionRoot());
	}

	public Contradiction getContradictionPremise() {
		return new ContradictionImpl(getInferenceRoot());
	}

	@Override
	public String toString() {
		return "Propagated contradiction" + premiseRelation_ + "<-"
				+ inferenceRoot_;
	}

	@Override
	public <I, O> O accept(ContradictionInferenceVisitor<I, O> visitor, I input) {
		return visitor.visit(this, input);
	}

}