
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

/**
 * {@link ElkObjectPropertyDomainAxiom} was entailed because inclusion of
 * <code>ObjectSomeValuesFrom( {@link ElkObjectPropertyDomainAxiom#getProperty()} owl:Thing )</code>
 * in {@link ElkObjectPropertyDomainAxiom#getDomain()} was derived.
 * <p>
 * {@link #getReason()} returns a {@link SubClassInclusionComposed} with
 * {@link SubClassInclusionComposed#getDestination()} corresponding to
 * <code>ObjectSomeValuesFrom( {@link ElkObjectPropertyDomainAxiom#getProperty()} owl:Thing )</code>
 * and {@link SubClassInclusionComposed#getSubsumer()} corresponding to
 * {@link ElkObjectPropertyDomainAxiom#getDomain()}.
 * 
 * @author Peter Skocovsky
 */
public interface DerivedClassInclusionEntailsObjectPropertyDomainAxiom
		extends AxiomEntailmentInference<ElkObjectPropertyDomainAxiom>,
		HasReason<SubClassInclusionComposed> {

	@Override
	ObjectPropertyDomainAxiomEntailment getConclusion();

	public static interface Visitor<O> {
		O visit(DerivedClassInclusionEntailsObjectPropertyDomainAxiom derivedClassInclusionEntailsObjectPropertyDomainAxiom);
	}

}
