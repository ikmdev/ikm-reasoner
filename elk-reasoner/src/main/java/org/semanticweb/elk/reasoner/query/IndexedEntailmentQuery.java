
package org.semanticweb.elk.reasoner.query;

import java.util.Collection;

import org.liveontologies.puli.Proof;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SaturationConclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * @author Peter Skocovsky
 *
 * @param <E>
 *            Type of the entailment that is queried.
 */
public interface IndexedEntailmentQuery<E extends Entailment> {

	/**
	 * @return The entailment that is queried.
	 */
	E getQuery();

	/**
	 * @return negatively indexed {@link IndexedContextRoot}s obtained by
	 *         indexing this query.
	 */
	Collection<? extends IndexedContextRoot> getPositivelyIndexed();

	/**
	 * Explains why the queried entailment is entailed. If it is not entailed,
	 * it is not provable by the inferences from the returned proof.
	 * 
	 * @param atMostOne
	 *            Whether at most one explanation should be returned.
	 * @param saturationState
	 * @param conclusionFactory
	 * @return An evidence that the queried entailment is entailed.
	 * @throws ElkQueryException
	 */
	<C extends Context> Proof<EntailmentInference> getEvidence(
			boolean atMostOne, SaturationState<C> saturationState,
			SaturationConclusion.Factory conclusionFactory)
			throws ElkQueryException;

}
