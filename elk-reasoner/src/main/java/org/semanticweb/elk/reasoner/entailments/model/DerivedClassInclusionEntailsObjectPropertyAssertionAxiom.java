
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

/**
 * {@link ElkObjectPropertyAssertionAxiom} was entailed because inclusion of
 * <code>ObjectOneOf( {@link ElkObjectPropertyAssertionAxiom#getSubject()} )</code>
 * in
 * <code>ObjectSomeValuesFrom( {@link ElkObjectPropertyAssertionAxiom#getProperty()} ObjectOneOf( {@link ElkObjectPropertyAssertionAxiom#getObject()} ) )</code>
 * was derived.
 * <p>
 * {@link #getReason()} returns a {@link SubClassInclusionComposed} with
 * {@link SubClassInclusionComposed#getDestination()} corresponding to
 * <code>ObjectOneOf( {@link ElkObjectPropertyAssertionAxiom#getSubject()} )</code>
 * and {@link SubClassInclusionComposed#getSubsumer()} corresponding to
 * <code>ObjectSomeValuesFrom( {@link ElkObjectPropertyAssertionAxiom#getProperty()} ObjectOneOf( {@link ElkObjectPropertyAssertionAxiom#getObject()} ) )</code>.
 * 
 * @author Peter Skocovsky
 */
public interface DerivedClassInclusionEntailsObjectPropertyAssertionAxiom
		extends AxiomEntailmentInference<ElkObjectPropertyAssertionAxiom>,
		HasReason<SubClassInclusionComposed> {

	@Override
	ObjectPropertyAssertionAxiomEntailment getConclusion();

	public static interface Visitor<O> {
		O visit(DerivedClassInclusionEntailsObjectPropertyAssertionAxiom derivedClassInclusionEntailsObjectPropertyAssertionAxiom);
	}

}
