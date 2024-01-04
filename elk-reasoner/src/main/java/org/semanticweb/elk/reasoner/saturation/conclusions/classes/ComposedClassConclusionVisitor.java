
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.DisjointSubsumer;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;

/**
 * A {@link ClassConclusion.Visitor} that is composed of several given
 * {@link ClassConclusion.Visitor}s. The visit method of the composed visitor
 * calls the original {@link ClassConclusion.Visitor}s in the specified order
 * and returns {@code true} for the {@link ClassConclusion} if and only all of
 * the {@link ClassConclusion.Visitor}s return {@code true}. The result is
 * evaluated lazily, i.e., if some {@link ClassConclusion.Visitor} returns
 * {@code false}, the subsequent {@link ClassConclusion.Visitor}s are not
 * called.
 * 
 * @author "Yevgeny Kazakov"
 */
public class ComposedClassConclusionVisitor implements
		ClassConclusion.Visitor<Boolean> {

	/**
	 * The original {@link ClassConclusion.Visitor}s to be called in the specified
	 * order
	 */
	final private ClassConclusion.Visitor<Boolean>[] visitors_;

	/**
	 * Creates a new {@link ClassConclusion.Visitor} that combines several given
	 * {@link ClassConclusion.Visitor}s. The visit method of the combined
	 * visitor calls the original {@link ClassConclusion.Visitor}s in the
	 * specified order and returns {@code true} for the {@link ClassConclusion}
	 * if and only all of the {@link ClassConclusion.Visitor}s return
	 * {@code true}. The result is evaluated lazily, i.e., if some
	 * {@link ClassConclusion.Visitor} returns {@code false}, the subsequent
	 * {@link ClassConclusion.Visitor}s are not called.
	 * 
	 * @param visitors
	 *            the {@link ClassConclusion.Visitor} to be composed
	 */
	@SafeVarargs
	public ComposedClassConclusionVisitor(
			ClassConclusion.Visitor<Boolean>... visitors) {
		this.visitors_ = visitors;
	}

	@Override
	public Boolean visit(BackwardLink subConclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(subConclusion))
				return false;
		}
		return true;
	}

	@Override
	public Boolean visit(SubClassInclusionComposed conclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(conclusion))
				return false;
		}
		return true;
	}

	@Override
	public Boolean visit(ContextInitialization conclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(conclusion))
				return false;
		}
		return true;
	}

	@Override
	public Boolean visit(ClassInconsistency conclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(conclusion))
				return false;
		}
		return true;
	}

	@Override
	public Boolean visit(SubClassInclusionDecomposed conclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(conclusion))
				return false;
		}
		return true;
	}

	@Override
	public Boolean visit(DisjointSubsumer conclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(conclusion))
				return false;
		}
		return true;
	}

	@Override
	public Boolean visit(ForwardLink conclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(conclusion))
				return false;
		}
		return true;
	}

	@Override
	public Boolean visit(Propagation subConclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(subConclusion))
				return false;
		}
		return true;
	}

	@Override
	public Boolean visit(SubContextInitialization subConclusion) {
		for (int i = 0; i < visitors_.length; i++) {
			if (!visitors_[i].visit(subConclusion))
				return false;
		}
		return true;
	}

}
