
package org.semanticweb.elk.owl.inferences;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 * 
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  ⊥ ⊑ C
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionOwlNothing extends AbstractElkInference {

	public final static String NAME = "Bottom Subclass";

	private final ElkClassExpression superClass_;

	ElkClassInclusionOwlNothing(ElkClassExpression superClass) {
		this.superClass_ = superClass;
	}

	public ElkClassExpression getSuperClass() {
		return superClass_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return 0;
	}

	@Override
	public ElkAxiom getPremise(int index, ElkObject.Factory factory) {
		return failGetPremise(index);
	}

	@Override
	public ElkSubClassOfAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(factory.getOwlNothing(), superClass_);
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

		ElkClassInclusionOwlNothing getElkClassInclusionOwlNothing(
				ElkClassExpression superClass);

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

		O visit(ElkClassInclusionOwlNothing inference);

	}

}
