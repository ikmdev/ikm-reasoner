
package org.semanticweb.elk.loading;

import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.query.IndexedEntailmentQuery;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * An object through which entailment queries can be added to or removed from
 * the index.
 * 
 * @author Peter Skocovsky
 */
public interface EntailmentQueryLoader {

	/**
	 * Loads pending entailment queries using the provided; if called twice, the
	 * already loaded queries will not be processed again.
	 * 
	 * @param inserter
	 *                     an {@link ElkAxiomVisitor} that inserts the queries
	 * @param deleter
	 *                     an {@link ElkAxiomVisitor} that deletes the queries
	 * @throws ElkLoadingException
	 *                                 if loading cannot be completed
	 *                                 successfully
	 */
	void load(
			ElkAxiomVisitor<IndexedEntailmentQuery<? extends Entailment>> inserter,
			ElkAxiomVisitor<IndexedEntailmentQuery<? extends Entailment>> deleter)
			throws ElkLoadingException;

	/**
	 * @return {@code true} if the loading is finished, i.e., calling
	 *         {@link EntailmentQueryLoader#load(ElkAxiomVisitor, ElkAxiomVisitor)}
	 *         will have no effect
	 */
	public boolean isLoadingFinished();

	/**
	 * Close resources used by this {@link EntailmentQueryLoader}
	 */
	public void dispose();

	public static interface Factory {

		EntailmentQueryLoader getQueryLoader(InterruptMonitor interrupter);

	}

}
