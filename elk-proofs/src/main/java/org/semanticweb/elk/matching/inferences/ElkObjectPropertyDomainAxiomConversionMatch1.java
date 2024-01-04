
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyDomainAxiomConversion;

public class ElkObjectPropertyDomainAxiomConversionMatch1
		extends AbstractInferenceMatch<ElkObjectPropertyDomainAxiomConversion> {

	ElkObjectPropertyDomainAxiomConversionMatch1(
			ElkObjectPropertyDomainAxiomConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkObjectPropertyDomainAxiomConversion parent = getParent();
		ElkObjectPropertyDomainAxiom premise = parent.getOriginalAxiom();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				factory.getObjectSomeValuesFrom(premise.getProperty(),
						factory.getOwlThing()),
				premise.getDomain());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> {

		O visit(ElkObjectPropertyDomainAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkObjectPropertyDomainAxiomConversionMatch1 getElkObjectPropertyDomainAxiomConversionMatch1(
				ElkObjectPropertyDomainAxiomConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
