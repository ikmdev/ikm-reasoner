
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

import org.semanticweb.elk.Reference;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.ClassConclusionSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link ClassConclusion.Visitor} that removes the visited
 * {@link ClassConclusion} from the {@link ClassConclusionSet} of the given
 * {@link Reference}. The visit method returns {@code true} if the
 * {@link ClassConclusionSet} was modified as the result of this operation,
 * i.e., the {@link ClassConclusion} was contained in the
 * {@link ClassConclusionSet}.
 * 
 * @see ClassConclusionInsertionVisitor
 * @see ClassConclusionOccurrenceCheckingVisitor
 * 
 * @author "Yevgeny Kazakov"
 */
public class ClassConclusionDeletionVisitor extends
		DummyClassConclusionVisitor<Boolean> {

	// logger for events
	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(ClassConclusionDeletionVisitor.class);
	
	private final Reference<? extends ClassConclusionSet> conclusionsRef;
	
	public ClassConclusionDeletionVisitor(Reference<? extends ClassConclusionSet> conclusionsRef) {
		this.conclusionsRef = conclusionsRef;

	}

	// TODO: make this by combining the visitor in order to avoid overheads when
	// logging is switched off
	@Override
	protected Boolean defaultVisit(ClassConclusion conclusion) {
		ClassConclusionSet conclusions = conclusionsRef.get();
		boolean result = conclusions.removeConclusion(conclusion);
		if (LOGGER_.isTraceEnabled()) {
			LOGGER_.trace("{}: deleting {}: {}", conclusions, conclusion,
					result ? "success" : "failure");
		}
		return result;
	}

}
