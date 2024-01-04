
package org.semanticweb.elk.reasoner.query;



import org.semanticweb.elk.Lock;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.completeness.IncompletenessMonitor;

/**
 * Represents the result of an entailment test for {@link ElkAxiom} (the query).
 * 
 * @author Peter Skocovsky
 * @author Yevgeny Kazakov
 * 
 *         TODO: manage locks using weak references
 */
public interface QueryResult extends Lock {

	/**
	 * @return The query that is tested for the entailment.
	 */
	ElkAxiom getQuery();

	/**
	 * @return {@code true} if the reasoner has determined that the query is
	 *         entailed by the ontology and {@code false} otherwise. If
	 *         {@code false} is returned this does not necessarily mean that the
	 *         query is not entailed since the reasoning results may be
	 *         incomplete. The latter can be checked using
	 *         {@link #getIncompletenessMonitor()}.
	 * @throws ElkQueryException
	 *             if this {@link QueryResult} has not been computed yet
	 */
	boolean entailmentProved() throws ElkQueryException;

	/**
	 * @return the {@link IncompletenessMonitor} using which one can verify
	 *         completeness of the query result. If the query result is
	 *         incomplete then the query may still be entailed even though
	 *         {@link #entailmentProved()} returns {@code false}
	 */
	IncompletenessMonitor getIncompletenessMonitor();

}
