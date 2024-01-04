
package org.semanticweb.elk.reasoner.tracing;

import java.util.Set;

import org.liveontologies.puli.ModifiableProof;

public interface ModifiableTracingProof<I extends TracingInference>
		extends GenericTracingProof<I>, ModifiableProof<I> {

	Set<? extends Conclusion> getAllConclusions();

}
