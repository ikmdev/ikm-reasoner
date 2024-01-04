
package org.semanticweb.elk.matching.inferences;



import java.util.List;

import org.semanticweb.elk.matching.ElkMatchException;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentObjectPropertiesAxiomConversion;

public class ElkEquivalentObjectPropertiesAxiomConversionMatch1 extends
		AbstractInferenceMatch<ElkEquivalentObjectPropertiesAxiomConversion> {

	ElkEquivalentObjectPropertiesAxiomConversionMatch1(
			ElkEquivalentObjectPropertiesAxiomConversion parent,
			IndexedSubObjectPropertyOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubObjectPropertyOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkEquivalentObjectPropertiesAxiomConversion parent = getParent();
		ElkEquivalentObjectPropertiesAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkObjectPropertyExpression> members = premise
				.getObjectPropertyExpressions();
		ElkObjectPropertyExpression superPropertyExpression = members
				.get(parent.getSuperPropertyPosition());
		if (superPropertyExpression instanceof ElkObjectProperty) {
			ElkObjectProperty superProperty = (ElkObjectProperty) superPropertyExpression;
			return factory.getIndexedSubObjectPropertyOfAxiomMatch2(
					factory.getIndexedSubObjectPropertyOfAxiomMatch1(
							parent.getConclusion(factory)),
					members.get(parent.getSubPropertyPosition()),
					superProperty);
		} else {
			throw new ElkMatchException(
					parent.getConclusion(factory).getSuperProperty(),
					superPropertyExpression);
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

		O visit(ElkEquivalentObjectPropertiesAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkEquivalentObjectPropertiesAxiomConversionMatch1 getElkEquivalentObjectPropertiesAxiomConversionMatch1(
				ElkEquivalentObjectPropertiesAxiomConversion parent,
				IndexedSubObjectPropertyOfAxiomMatch1 conclusionMatch);

	}

}
