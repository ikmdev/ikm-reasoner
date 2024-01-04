
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.DisjointSubsumer;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;

/**
 * A {@link ClassConclusion.Visitor} that always returns {@code null}. Can be
 * used as prototype to implement other visitors by overriding the corresponding
 * (default) methods.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public class DummyClassConclusionVisitor<O>
		extends
			DummySubClassConclusionVisitor<O>
		implements
			ClassConclusion.Visitor<O> {

	/**
	 * The default implementation of all methods
	 * 
	 * @param conclusion
	 * @return
	 */
	protected O defaultVisit(ClassConclusion conclusion) {
		return null;
	}

	@Override
	protected O defaultVisit(SubClassConclusion subConclusion) {
		return defaultVisit((ClassConclusion) subConclusion);
	}

	@Override
	public O visit(SubClassInclusionComposed conclusion) {
		return defaultVisit(conclusion);
	}

	@Override
	public O visit(ContextInitialization conclusion) {
		return defaultVisit(conclusion);
	}

	@Override
	public O visit(ClassInconsistency conclusion) {
		return defaultVisit(conclusion);
	}

	@Override
	public O visit(SubClassInclusionDecomposed conclusion) {
		return defaultVisit(conclusion);
	}

	@Override
	public O visit(DisjointSubsumer conclusion) {
		return defaultVisit(conclusion);
	}

	@Override
	public O visit(ForwardLink conclusion) {
		return defaultVisit(conclusion);
	}

}
