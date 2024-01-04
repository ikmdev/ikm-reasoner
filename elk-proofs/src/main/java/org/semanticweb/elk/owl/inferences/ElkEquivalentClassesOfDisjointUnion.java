
package org.semanticweb.elk.owl.inferences;



import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;

/**
 * Represents the inference:
 * 
 * <pre>
 *    DisjointUnion(D C0 C1 ... Cn)
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  EquivalentClasses(D (C1 ⊔...⊔ Cn))
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkEquivalentClassesOfDisjointUnion extends AbstractElkInference {

	public final static String NAME = "Disjoint Union to Equivalent Classes";

	private final ElkClass defined_;

	private final List<? extends ElkClassExpression> disjoint_;

	ElkEquivalentClassesOfDisjointUnion(ElkClass defined,
			List<? extends ElkClassExpression> disjoint) {
		this.defined_ = defined;
		this.disjoint_ = disjoint;
	}

	public ElkClass getDefined() {
		return defined_;
	}

	public List<? extends ElkClassExpression> getDisjoint() {
		return disjoint_;
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

	public ElkDisjointUnionAxiom getPremise(ElkObject.Factory factory) {
		return factory.getDisjointUnionAxiom(defined_, disjoint_);
	}

	@Override
	public ElkEquivalentClassesAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getEquivalentClassesAxiom(defined_,
				factory.getObjectUnionOf(disjoint_));
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

		ElkEquivalentClassesOfDisjointUnion getElkEquivalentClassesOfDisjointUnion(
				ElkClass defined, List<? extends ElkClassExpression> disjoint);

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

		O visit(ElkEquivalentClassesOfDisjointUnion inference);

	}

}
