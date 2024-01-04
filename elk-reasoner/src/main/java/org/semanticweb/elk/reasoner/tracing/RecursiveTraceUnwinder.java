
package org.semanticweb.elk.reasoner.tracing;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.semanticweb.elk.owl.visitors.DummyElkAxiomVisitor;

/**
 * Recursively visits inferences which were used to produce a given conclusion.
 * It stops when the inference visitors passed by the outer code returns
 * {@code false} for a particular inference.
 * 
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class RecursiveTraceUnwinder implements TraceUnwinder<Boolean> {

	private final TracingProof proof_;

	private final Queue<TracingInference> innferencesToDo_ = new LinkedList<TracingInference>();

	public RecursiveTraceUnwinder(TracingProof proof) {
		this.proof_ = proof;
	}

	@Override
	public void accept(final Conclusion conclusion,
			final TracingInference.Visitor<Boolean> inferenceVisitor) {
		final Set<TracingInference> seenInferences = new HashSet<TracingInference>();
		// should be empty anyways
		innferencesToDo_.clear();
		addToQueue(conclusion, seenInferences);
		// this visitor visits all premises and putting them into the todo queue
		TracingInferencePremiseVisitor<?> premiseVisitor = new TracingInferencePremiseVisitor<Void>(
				new DummyConclusionVisitor<Void>() {
					@Override
					protected Void defaultVisit(Conclusion newConclusion) {
						addToQueue(newConclusion, seenInferences);
						return null;
					}
				}, new DummyElkAxiomVisitor<Void>());

		for (;;) {
			// take the first element
			final TracingInference next = innferencesToDo_.poll();

			if (next == null) {
				break;
			}
			// user visitor
			if (next.accept(inferenceVisitor)) {
				// visiting premises
				next.accept(premiseVisitor);
			}
		}

	}

	private void addToQueue(final Conclusion conclusion,
			final Set<TracingInference> seenInferences) {

		boolean derived = false;
		// finding all inferences that produced the given conclusion (if we are
		// here, the inference must have premises, i.e. it's not an
		// initialization inference)
		for (TracingInference inference : proof_.getInferences(conclusion)) {
			if (!seenInferences.contains(inference)) {
				seenInferences.add(inference);
				innferencesToDo_.add(inference);
			}
			derived = true;
		}

		if (!derived) {
			handleUntraced(conclusion);
		}
	}

	protected void handleUntraced(
			@SuppressWarnings("unused") Conclusion untraced) {
		// no-op
	}

}
