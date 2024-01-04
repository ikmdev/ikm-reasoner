
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.Reference;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link ClassConclusion.Visitor} that checks if the {@link Context} for the
 * root returned by {@link ClassConclusion#getTraceRoot()} for the visited
 * {@link ClassConclusion}s is not saturated, and reports an error otherwise.
 * Should be used for debugging.
 * 
 * @see ClassConclusion#getTraceRoot()
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ClassConclusionTracingContextNotSaturatedCheckingVisitor
		extends
			DummyClassConclusionVisitor<Boolean>
		implements Reference<Context> {

	// logger for events
	private static final Logger LOGGER_ = LoggerFactory.getLogger(
			ClassConclusionTracingContextNotSaturatedCheckingVisitor.class);

	private final Reference<Context> contextRef_;

	private final SaturationState<?> state_;

	public ClassConclusionTracingContextNotSaturatedCheckingVisitor(
			Reference<Context> context, SaturationState<?> state) {
		this.contextRef_ = context;
		this.state_ = state;
	}

	@Override
	public Context get() {
		return contextRef_.get();
	}

	@Override
	protected Boolean defaultVisit(SubClassConclusion subConclusion) {
		// ignore sub-conclusions
		return true;
	}

	@Override
	protected Boolean defaultVisit(ClassConclusion conclusion) {
		Context originContext = state_.getContext(conclusion.getTraceRoot());
		if (originContext.isInitialized() && originContext.isSaturated()) {
			LOGGER_.error("{}: adding conclusion {} to saturated context {}",
					contextRef_, conclusion,
					get().containsConclusion(conclusion)
							? "(it is already there)"
							: "");
		}
		return true;
	}

}
