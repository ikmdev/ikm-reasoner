
package org.semanticweb.elk.reasoner.tracing;

import java.util.Collection;
import java.util.Set;

/**
 * A {@link ModifiableTracingProof} in which the access methods are
 * synchronized.
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <I>
 *            the type of inferences stored in this
 *            {@link ModifiableTracingProof}
 */
public class SynchronizedModifiableTracingProof<I extends TracingInference>
		extends ModifiableTracingProofImpl<I> {

	@Override
	public synchronized Collection<? extends I> getInferences(
			Object conclusion) {
		return super.getInferences(conclusion);
	}

	@Override
	public synchronized void produce(I inference) {
		super.produce(inference);
	}

	@Override
	public synchronized void clear() {
		super.clear();
	}

	@Override
	public synchronized Set<? extends Conclusion> getAllConclusions() {
		return super.getAllConclusions();
	}

}
