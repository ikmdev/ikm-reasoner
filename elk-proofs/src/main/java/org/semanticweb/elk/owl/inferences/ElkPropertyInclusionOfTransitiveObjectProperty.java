
package org.semanticweb.elk.owl.inferences;



import java.util.Arrays;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *  TransitiveObjectProperty(T)
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *         T∘T ⊑ T
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkPropertyInclusionOfTransitiveObjectProperty
		extends AbstractElkInference {

	public final static String NAME = "Transitive Property Translation";

	private final ElkObjectPropertyExpression property_;

	ElkPropertyInclusionOfTransitiveObjectProperty(
			ElkObjectPropertyExpression property) {
		this.property_ = property;
	}

	public ElkObjectPropertyExpression getProperty() {
		return property_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return 1;
	}

	@Override
	public ElkAxiom getPremise(int index, ElkObject.Factory factory) {
		if (index == 0) {
			return getPremise(factory);
		}
		// else
		return failGetPremise(index);
	}

	public ElkTransitiveObjectPropertyAxiom getPremise(
			ElkObject.Factory factory) {
		return factory.getTransitiveObjectPropertyAxiom(property_);
	}

	@Override
	public ElkSubObjectPropertyOfAxiom getConclusion(
			ElkObject.Factory factory) {
		return factory
				.getSubObjectPropertyOfAxiom(
						factory.getObjectPropertyChain(
								Arrays.asList(property_, property_)),
						property_);
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

		ElkPropertyInclusionOfTransitiveObjectProperty getElkPropertyInclusionOfTransitiveObjectProperty(
				ElkObjectPropertyExpression property);
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

		O visit(ElkPropertyInclusionOfTransitiveObjectProperty inference);

	}

}
