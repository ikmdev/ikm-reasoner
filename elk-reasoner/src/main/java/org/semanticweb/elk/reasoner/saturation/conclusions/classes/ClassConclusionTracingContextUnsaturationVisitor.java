
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.saturation.SaturationStateWriter;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A {@link ClassConclusion.Visitor} that marks the {@link Context} for the root
 * returned by {@link ClassConclusion#getTraceRoot()} for the visited
 * {@link ClassConclusion}s as not saturated if the {@link ClassConclusion} can
 * potentially be re-derived. The visit method returns always {@code true}.
 * 
 * @see ClassConclusion#getTraceRoot()
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ClassConclusionTracingContextUnsaturationVisitor
		extends
			DummyClassConclusionVisitor<Boolean> {

	private final SaturationStateWriter<?> writer_;

	public ClassConclusionTracingContextUnsaturationVisitor(
			SaturationStateWriter<?> writer) {
		this.writer_ = writer;
	}

	@Override
	protected Boolean defaultVisit(ClassConclusion conclusion) {
		writer_.markAsNotSaturated(conclusion.getTraceRoot());
		return true;
	}

	Boolean defaultVisit(SubClassInclusion conclusion) {
		// if the super-class does not occur in the ontology anymore, it cannot
		// be
		// re-derived, and thus, the context should not be modified
		// TODO: extend this check to other types of conclusions
		if (conclusion.getSubsumer().occurs()) {
			return defaultVisit((ClassConclusion) conclusion);
		}
		return true;
	}

	@Override
	public Boolean visit(SubClassInclusionComposed conclusion) {
		return defaultVisit(conclusion);
	}

	@Override
	public Boolean visit(SubClassInclusionDecomposed conclusion) {
		return defaultVisit(conclusion);
	}
}
