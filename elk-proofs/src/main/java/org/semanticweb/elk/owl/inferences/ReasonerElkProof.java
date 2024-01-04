
package org.semanticweb.elk.owl.inferences;

import org.liveontologies.puli.ChronologicalProof;
import org.liveontologies.puli.DynamicProof;
import org.liveontologies.puli.Proof;
import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.reasoner.Reasoner;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.query.VerifiableQueryResult;

/**
 * A set of inferences necessary to derive a given {@link ElkAxiom}s provided by
 * {@link Reasoner}.
 * 
 * @author Yevgeny Kazakov
 * @author Peter Skocovsky
 */
public class ReasonerElkProof extends ChronologicalProof<ElkInference>
		implements ModifiableElkProof {

	private final Reasoner reasoner_;

	private final ElkInference.Factory inferenceFactory_;

	private ReasonerElkProof(Reasoner reasoner, ElkAxiom goal,
			ElkObject.Factory elkFactory) throws ElkException {
		this.reasoner_ = reasoner;
		this.inferenceFactory_ = new ElkInferenceOptimizedProducingFactory(this,
				elkFactory);
	}

	public static DynamicProof<ElkInference> create(
			final Reasoner reasoner, final ElkAxiom goal,
			final ElkObject.Factory elkFactory) throws ElkException {
		final ReasonerElkProof proof = new ReasonerElkProof(reasoner, goal,
				elkFactory);
		synchronized (proof) {
			proof.generateInferences(goal);
		}
		return proof;
	}

	private void generateInferences(final ElkAxiom goal) throws ElkException {

		final VerifiableQueryResult result = reasoner_.checkEntailment(goal);
		try {
			final Proof<EntailmentInference> evidence = result.getEvidence(false);
			new ElkProofGenerator(evidence, reasoner_,
					inferenceFactory_).generate(result.getEntailment());
		} finally {
			result.unlock();
		}

	}

}
