package org.semanticweb.elk.matching.conclusions;

/*
 * #%L
 * ELK Proofs Package
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

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.PropertyRange;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

public class ConclusionMatchBaseFactory implements ConclusionMatch.Factory {

	@Override
	public BackwardLinkMatch1 getBackwardLinkMatch1(BackwardLink parent,
			IndexedContextRootMatch sourceMatch,
			ElkObjectProperty relationMatch) {
		return new BackwardLinkMatch1(parent, sourceMatch, relationMatch);
	}

	@Override
	public BackwardLinkMatch2 getBackwardLinkMatch2(BackwardLinkMatch1 parent,
			IndexedContextRootMatch destinationMatch) {
		return new BackwardLinkMatch2(parent, destinationMatch);
	}

	@Override
	public ForwardLinkMatch1 getForwardLinkMatch1(ForwardLink parent,
			IndexedContextRootMatch destinationMatch,
			ElkSubObjectPropertyExpression fullForwardChainMatch,
			int forwardChainStartPos) {
		return new ForwardLinkMatch1(parent, destinationMatch,
				fullForwardChainMatch, forwardChainStartPos);
	}

	@Override
	public ForwardLinkMatch2 getForwardLinkMatch2(ForwardLinkMatch1 parent,
			IndexedContextRootMatchChain intermediateRoots,
			IndexedContextRootMatch targetMatch) {
		return new ForwardLinkMatch2(parent, intermediateRoots, targetMatch);
	}

	@Override
	public IndexedEquivalentClassesAxiomMatch1 getIndexedEquivalentClassesAxiomMatch1(
			IndexedEquivalentClassesAxiom parent) {
		return new IndexedEquivalentClassesAxiomMatch1(parent);
	}

	@Override
	public IndexedEquivalentClassesAxiomMatch2 getIndexedEquivalentClassesAxiomMatch2(
			IndexedEquivalentClassesAxiomMatch1 parent, ElkClassExpression firstMemberMatch,
			ElkClassExpression secondMemberMatch) {
		return new IndexedEquivalentClassesAxiomMatch2(parent, firstMemberMatch,
				secondMemberMatch);
	}

	@Override
	public IndexedDisjointClassesAxiomMatch1 getIndexedDisjointClassesAxiomMatch1(
			IndexedDisjointClassesAxiom parent) {
		return new IndexedDisjointClassesAxiomMatch1(parent);
	}

	@Override
	public IndexedDisjointClassesAxiomMatch2 getIndexedDisjointClassesAxiomMatch2(
			IndexedDisjointClassesAxiomMatch1 parent,
			List<? extends ElkClassExpression> memberMatches) {
		return new IndexedDisjointClassesAxiomMatch2(parent, memberMatches);
	}

	@Override
	public IndexedObjectPropertyRangeAxiomMatch1 getIndexedObjectPropertyRangeAxiomMatch1(
			IndexedObjectPropertyRangeAxiom parent) {
		return new IndexedObjectPropertyRangeAxiomMatch1(parent);
	}

	@Override
	public IndexedObjectPropertyRangeAxiomMatch2 getIndexedObjectPropertyRangeAxiomMatch2(
			IndexedObjectPropertyRangeAxiomMatch1 parent,
			ElkObjectProperty propertyMatch, ElkClassExpression rangeMatch) {
		return new IndexedObjectPropertyRangeAxiomMatch2(parent, propertyMatch,
				rangeMatch);
	}

	@Override
	public IndexedSubClassOfAxiomMatch1 getIndexedSubClassOfAxiomMatch1(
			IndexedSubClassOfAxiom parent) {
		return new IndexedSubClassOfAxiomMatch1(parent);
	}

	@Override
	public IndexedSubClassOfAxiomMatch2 getIndexedSubClassOfAxiomMatch2(
			IndexedSubClassOfAxiomMatch1 parent,
			ElkClassExpression subClassMatch,
			ElkClassExpression superClassMatch) {
		return new IndexedSubClassOfAxiomMatch2(parent, subClassMatch,
				superClassMatch);
	}

	@Override
	public IndexedSubObjectPropertyOfAxiomMatch1 getIndexedSubObjectPropertyOfAxiomMatch1(
			IndexedSubObjectPropertyOfAxiom parent) {
		return new IndexedSubObjectPropertyOfAxiomMatch1(parent);
	}

	@Override
	public IndexedSubObjectPropertyOfAxiomMatch2 getIndexedSubObjectPropertyOfAxiomMatch2(
			IndexedSubObjectPropertyOfAxiomMatch1 parent,
			ElkSubObjectPropertyExpression subPropertyChainMatch,
			ElkObjectProperty superPropertyMatch) {
		return new IndexedSubObjectPropertyOfAxiomMatch2(parent,
				subPropertyChainMatch, superPropertyMatch);
	}

	@Override
	public PropagationMatch1 getPropagationMatch1(Propagation parent,
			ElkObjectSomeValuesFrom carryMatch) {
		return new PropagationMatch1(parent, carryMatch);
	}

	@Override
	public PropagationMatch2 getPropagationMatch2(PropagationMatch1 parent,
			ElkObjectProperty relationMatch) {
		return new PropagationMatch2(parent, relationMatch);
	}

	@Override
	public PropagationMatch3 getPropagationMatch3(PropagationMatch2 parent,
			IndexedContextRootMatch destinationMatch) {
		return new PropagationMatch3(parent, destinationMatch);
	}

	@Override
	public PropertyRangeMatch1 getPropertyRangeMatch1(PropertyRange parent,
			ElkObjectProperty propertyMatch) {
		return new PropertyRangeMatch1(parent, propertyMatch);
	}

	@Override
	public PropertyRangeMatch2 getPropertyRangeMatch2(
			PropertyRangeMatch1 parent, ElkClassExpression rangeMatch) {
		return new PropertyRangeMatch2(parent, rangeMatch);
	}

	@Override
	public SubClassInclusionComposedMatch1 getSubClassInclusionComposedMatch1(
			SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			ElkClassExpression subsumerMatch) {
		return new SubClassInclusionComposedMatch1(parent, destinationMatch,
				subsumerMatch);
	}

	@Override
	public SubClassInclusionComposedMatch1 getSubClassInclusionComposedMatch1(
			SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			ElkObjectIntersectionOf fullSubsumerMatch,
			int subsumerPrefixLength) {
		return new SubClassInclusionComposedMatch1(parent, destinationMatch,
				fullSubsumerMatch, subsumerPrefixLength);
	}

	@Override
	public SubClassInclusionDecomposedMatch1 getSubClassInclusionDecomposedMatch1(
			SubClassInclusionDecomposed parent,
			IndexedContextRootMatch destinationMatch) {
		return new SubClassInclusionDecomposedMatch1(parent, destinationMatch);
	}

	@Override
	public SubClassInclusionDecomposedMatch2 getSubClassInclusionDecomposedMatch2(
			SubClassInclusionDecomposedMatch1 parent,
			ElkClassExpression subsumerMatch) {
		return new SubClassInclusionDecomposedMatch2(parent, subsumerMatch);
	}

	@Override
	public SubClassInclusionDecomposedMatch2 getSubClassInclusionDecomposedMatch2(
			SubClassInclusionDecomposedMatch1 parent,
			ElkObjectIntersectionOf subsumerFullConjunctionMatch,
			int subsumerConjunctionPrefixLength) {
		return new SubClassInclusionDecomposedMatch2(parent,
				subsumerFullConjunctionMatch, subsumerConjunctionPrefixLength);
	}

	@Override
	public SubPropertyChainMatch1 getSubPropertyChainMatch1(
			SubPropertyChain parent,
			ElkSubObjectPropertyExpression fullSuperChainMatch,
			int superChainStartPos) {
		return new SubPropertyChainMatch1(parent, fullSuperChainMatch,
				superChainStartPos);
	}

	@Override
	public SubPropertyChainMatch2 getSubPropertyChainMatch2(
			SubPropertyChainMatch1 parent,
			ElkSubObjectPropertyExpression fullSubChainMatch,
			int subChainStartPos) {
		return new SubPropertyChainMatch2(parent, fullSubChainMatch,
				subChainStartPos);
	}

}
