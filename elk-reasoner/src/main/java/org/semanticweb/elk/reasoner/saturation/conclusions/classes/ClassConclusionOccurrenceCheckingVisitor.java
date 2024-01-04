 
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.Reference;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.ClassConclusionSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link ClassConclusion.Visitor} that checks if visited
 * {@link ClassConclusion} is contained the {@link ClassConclusionSet} value of
 * the given {@link Reference}. The visit method returns {@code true} if the
 * {@link ClassConclusion} occurs in the {@link ClassConclusionSet} and
 * {@code false} otherwise.
 * 
 * @see ClassConclusionInsertionVisitor
 * @see ClassConclusionDeletionVisitor
 * @see ClassConclusionOccurrenceCheckingVisitor
 * 
 * @author "Yevgeny Kazakov"
 */
public class ClassConclusionOccurrenceCheckingVisitor extends
		DummyClassConclusionVisitor<Boolean> {

	// logger for events
	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(ClassConclusionOccurrenceCheckingVisitor.class);
	
	private final Reference<? extends ClassConclusionSet> conclusionsRef_;
	
	public ClassConclusionOccurrenceCheckingVisitor(Reference<? extends ClassConclusionSet> conclusions) {
		this.conclusionsRef_ = conclusions;
	}

	// TODO: make this by combining the visitor in order to avoid overheads when
	// logging is switched off
	@Override
	protected Boolean defaultVisit(ClassConclusion conclusion) {
		ClassConclusionSet conclusions = conclusionsRef_.get();		
		boolean result = conclusions == null
				? false
				: conclusions.containsConclusion(conclusion);
		if (LOGGER_.isTraceEnabled()) {
			LOGGER_.trace("{}: check occurrence of {}: {}", conclusions,
					conclusion, result ? "success" : "failure");
		}
		return result;
	}

}
