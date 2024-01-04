
package org.semanticweb.elk.owl.inferences;

import org.liveontologies.puli.AssertedConclusionInference;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;

/**
 * Represents the inference:
 * 
 * <pre>
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *   Axiom
 * </pre>
 * 
 * where Axiom occurs in the ontology
 * 
 * @author Yevgeny Kazakov
 */
public class ElkToldAxiom extends AbstractElkInference {

	public final static String NAME = AssertedConclusionInference.NAME;

	private final ElkAxiom axiom_;

	ElkToldAxiom(ElkAxiom axiom) {
		this.axiom_ = axiom;
	}

	public ElkAxiom getAxiom() {
		return axiom_;
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
	public ElkAxiom getConclusion(ElkObject.Factory factory) {
		return axiom_;
	}

	public <O> O accept(Visitor<O> visitor) {
		return visitor.visit(this);
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

		ElkToldAxiom getElkToldAxiom(ElkAxiom axiom);

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

		O visit(ElkToldAxiom inference);

	}

}
