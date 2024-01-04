
package org.semanticweb.elk.owl.inferences;

import org.liveontologies.puli.ModifiableProof;

public interface ModifiableElkProof extends ElkProof,
		ModifiableProof<ElkInference>, ElkInferenceProducer {

}
