
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.ElkMatchException;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyRangeAxiomConversion;

public class ElkObjectPropertyRangeAxiomConversionMatch1
		extends AbstractInferenceMatch<ElkObjectPropertyRangeAxiomConversion> {

	ElkObjectPropertyRangeAxiomConversionMatch1(
			ElkObjectPropertyRangeAxiomConversion parent,
			IndexedObjectPropertyRangeAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedObjectPropertyRangeAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkObjectPropertyRangeAxiomConversion parent = getParent();
		ElkObjectPropertyRangeAxiom premise = parent.getOriginalAxiom();
		ElkObjectPropertyExpression property = premise.getProperty();
		if (property instanceof ElkObjectProperty) {
			return factory.getIndexedObjectPropertyRangeAxiomMatch2(
					factory.getIndexedObjectPropertyRangeAxiomMatch1(
							parent.getConclusion(factory)),
					(ElkObjectProperty) property, premise.getRange());
		} else {
			throw new ElkMatchException(
					parent.getConclusion(factory).getProperty(), property);
		}
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

		O visit(ElkObjectPropertyRangeAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkObjectPropertyRangeAxiomConversionMatch1 getElkObjectPropertyRangeAxiomConversionMatch1(
				ElkObjectPropertyRangeAxiomConversion parent,
				IndexedObjectPropertyRangeAxiomMatch1 conclusionMatch);

	}

}
