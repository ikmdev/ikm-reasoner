
package org.semanticweb.elk.matching.inferences;



import java.util.Arrays;

import org.semanticweb.elk.matching.ElkMatchException;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkTransitiveObjectPropertyAxiomConversion;

public class ElkTransitiveObjectPropertyAxiomConversionMatch1 extends
		AbstractInferenceMatch<ElkTransitiveObjectPropertyAxiomConversion> {

	ElkTransitiveObjectPropertyAxiomConversionMatch1(
			ElkTransitiveObjectPropertyAxiomConversion parent,
			IndexedSubObjectPropertyOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubObjectPropertyOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkTransitiveObjectPropertyAxiomConversion parent = getParent();
		ElkTransitiveObjectPropertyAxiom premise = parent.getOriginalAxiom();
		ElkObjectPropertyExpression propertyExpression = premise.getProperty();
		if (propertyExpression instanceof ElkObjectProperty) {
			return factory.getIndexedSubObjectPropertyOfAxiomMatch2(
					factory.getIndexedSubObjectPropertyOfAxiomMatch1(
							parent.getConclusion(factory)),
					factory.getObjectPropertyChain(Arrays
							.asList(propertyExpression, propertyExpression)),
					(ElkObjectProperty) propertyExpression);
		} else {
			throw new ElkMatchException(
					parent.getConclusion(factory).getSuperProperty(),
					propertyExpression);
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

		O visit(ElkTransitiveObjectPropertyAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkTransitiveObjectPropertyAxiomConversionMatch1 getElkTransitiveObjectPropertyAxiomConversionMatch1(
				ElkTransitiveObjectPropertyAxiomConversion parent,
				IndexedSubObjectPropertyOfAxiomMatch1 conclusionMatch);

	}

}
