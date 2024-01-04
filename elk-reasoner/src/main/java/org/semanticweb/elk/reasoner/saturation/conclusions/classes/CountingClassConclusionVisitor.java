
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
 * A {@link ClassConclusion.Visitor} that increments the corresponding counters of the
 * given {@link ClassConclusionCounter} when visiting {@link ClassConclusion}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class CountingClassConclusionVisitor implements
		ClassConclusion.Visitor<Boolean> {

	private final ClassConclusionCounter counter_;

	public CountingClassConclusionVisitor(ClassConclusionCounter counter) {
		this.counter_ = counter;
	}

	@Override
	public Boolean visit(BackwardLink subConclusion) {
		counter_.countBackwardLink++;
		return true;
	}

	@Override
	public Boolean visit(SubClassInclusionComposed conclusion) {
		counter_.countSubClassInclusionComposed++;
		return true;
	}

	@Override
	public Boolean visit(ContextInitialization conclusion) {
		counter_.countContextInitialization++;
		return true;
	}

	@Override
	public Boolean visit(ClassInconsistency conclusion) {
		counter_.countContradiction++;
		return true;
	}

	@Override
	public Boolean visit(SubClassInclusionDecomposed conclusion) {
		counter_.countSubClassInclusionDecomposed++;
		return true;
	}

	@Override
	public Boolean visit(DisjointSubsumer conclusion) {
		counter_.countDisjointSubsumer++;
		return true;
	}

	@Override
	public Boolean visit(ForwardLink conclusion) {
		counter_.countForwardLink++;
		return true;
	}

	@Override
	public Boolean visit(Propagation subConclusion) {
		counter_.countPropagation++;
		return true;
	}

	@Override
	public Boolean visit(SubContextInitialization subConclusion) {
		counter_.countSubContextInitialization++;
		return true;
	}

}
