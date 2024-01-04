 
package org.semanticweb.elk.reasoner.tracing;

import org.liveontologies.puli.Proof;

/**
 * An object which can be used to retrieve {@link TracingInference}s producing a
 * given {@link Conclusion}.
 * 
 * @author Peter Skocovsky
 *
 * @param <I>
 *            The type of the inferences.
 */
public interface GenericTracingProof<I extends TracingInference>
		extends Proof<I> {
	// Restriction to tracing inferences.
}
