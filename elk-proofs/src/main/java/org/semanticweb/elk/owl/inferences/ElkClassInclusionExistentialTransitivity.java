
package org.semanticweb.elk.owl.inferences;

import java.util.Arrays;



import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *   (1)           (2)               (n)              (n+1)
 *  C0 ⊑ ∃T.C1  C1 ⊑ ∃T.C2 ... Cn-1 ⊑ ∃T.Cn  TransitiveObjectProperty(T)
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *                         C0 ⊑ ∃T.Cn
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionExistentialTransitivity
		extends AbstractElkInference {

	public final static String NAME = "Existential Transitivity Composition";

	private final List<? extends ElkClassExpression> classExpressions_;

	private final ElkObjectPropertyExpression transitiveProperty_;

	ElkClassInclusionExistentialTransitivity(
			ElkObjectPropertyExpression transitiveProperty,
			List<? extends ElkClassExpression> classExpressions) {
		if (classExpressions.size() < 3) {
			throw new IllegalArgumentException(classExpressions.toString());
		}
		this.classExpressions_ = classExpressions;
		this.transitiveProperty_ = transitiveProperty;
	}

	ElkClassInclusionExistentialTransitivity(
			ElkObjectPropertyExpression transitiveProperty,
			ElkClassExpression... classExpressions) {
		this(transitiveProperty, Arrays.asList(classExpressions));
	}

	public List<? extends ElkClassExpression> getClassExpressions() {
		return classExpressions_;
	}

	public ElkObjectPropertyExpression getTransitiveProperty() {
		return transitiveProperty_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return classExpressions_.size();
	}

	@Override
	public ElkAxiom getPremise(int index, ElkObject.Factory factory) {
		checkPremiseIndex(index);
		if (index < getExistentialPremiseCount()) {
			return getExistentialPremise(index, factory);
		}
		// else
		return getLastPremise(factory);
	}

	public int getExistentialPremiseCount() {
		return classExpressions_.size() - 1;
	}

	public ElkSubClassOfAxiom getExistentialPremise(int index,
			ElkObject.Factory factory) {
		if (index < 0 || index >= getExistentialPremiseCount()) {
			throw new IndexOutOfBoundsException(
					"No existential premise with index: " + index);
		}
		// else
		return factory.getSubClassOfAxiom(classExpressions_.get(index),
				factory.getObjectSomeValuesFrom(transitiveProperty_,
						classExpressions_.get(index + 1)));
	}

	public ElkTransitiveObjectPropertyAxiom getLastPremise(
			ElkObject.Factory factory) {
		return factory.getTransitiveObjectPropertyAxiom(transitiveProperty_);
	}

	@Override
	public ElkSubClassOfAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(classExpressions_.get(0),
				factory.getObjectSomeValuesFrom(transitiveProperty_,
						classExpressions_.get(classExpressions_.size() - 1)));

	}

	@Override
	public <O> O accept(ElkInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkClassInclusionExistentialTransitivity getElkClassInclusionExistentialTransitivity(
				ElkObjectPropertyExpression transitiveProperty,
				ElkClassExpression... classExpressions);

		ElkClassInclusionExistentialTransitivity getElkClassInclusionExistentialTransitivity(
				ElkObjectPropertyExpression transitiveProperty,
				List<? extends ElkClassExpression> classExpressions);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkClassInclusionExistentialTransitivity inference);

	}

}
