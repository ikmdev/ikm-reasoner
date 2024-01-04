
package org.semanticweb.elk.owl.inferences;

import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 * ⊤ ⊑ ∃ owl:topObjectProperty . Self
 * </pre>
 * 
 * @author Peter Skocovsky
 */
public class ElkClassInclusionOwlTopObjectProperty
		extends AbstractElkInference {

	static final ElkClassInclusionOwlTopObjectProperty INSTANCE = new ElkClassInclusionOwlTopObjectProperty();

	private final static String NAME_ = "Top Object Property Tautology";

	private ElkClassInclusionOwlTopObjectProperty() {
		// Empty.
	}

	@Override
	public String getName() {
		return NAME_;
	}

	@Override
	public int getPremiseCount() {
		return 0;
	}

	@Override
	public ElkSubClassOfAxiom getPremise(final int index,
			final ElkObject.Factory factory) {
		return failGetPremise(index);
	}

	@Override
	public ElkSubClassOfAxiom getConclusion(final ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(factory.getOwlThing(),
				factory.getObjectHasSelf(factory.getOwlTopObjectProperty()));
	}

	@Override
	public <O> O accept(final ElkInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances.
	 * 
	 * @author Peter Skocovsky
	 */
	public interface Factory {

		ElkClassInclusionOwlTopObjectProperty getElkClassInclusionOwlTopObjectProperty();

	}

	/**
	 * The visitor pattern for instances.
	 * 
	 * @author Peter Skocovsky
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkClassInclusionOwlTopObjectProperty inference);

	}

}
