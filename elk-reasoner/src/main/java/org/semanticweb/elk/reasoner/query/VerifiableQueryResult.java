
package org.semanticweb.elk.reasoner.query;



import org.liveontologies.puli.Proof;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;

/**
 * A {@link QueryResult} which can be verified using a proof.
 * 
 * @author Peter Skocovsky
 * @author Yevgeny Kazakov
 */
public interface VerifiableQueryResult extends QueryResult {

	/**
	 * Explains why the queried entailment is entailed. If it is not entailed,
	 * the returned proof is empty.
	 * 
	 * @param atMostOne
	 *            Whether at most one explanation should be returned.
	 * @return An evidence that the queried entailment is entailed.
	 * @throws ElkQueryException
	 * 
	 *             TODO: change to a direct proof of the query, e.g., using
	 *             InternalProof
	 */
	Proof<EntailmentInference> getEvidence(boolean atMostOne)
			throws ElkQueryException;

	/**
	 * Returns the entailment of the query. This should be the root of
	 * {@link #getEvidence(boolean)}.
	 * 
	 * @return The entailment of the query.
	 * @throws ElkQueryException
	 * 
	 *             TODO: remove
	 */
	Entailment getEntailment() throws ElkQueryException;

}
