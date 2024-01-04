
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkEquivalentClassesAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Equivalent_Classes">Equivalent
 * Class Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkEquivalentClassesAxiom extends ElkClassAxiom {

	/**
	 * Get the list of equivalent class expressions that this axiom refers to.
	 * The order of class expressions does not affect the semantics but it is
	 * relevant to the syntax of OWL.
	 * 
	 * @return list of equivalent class expressions
	 */
	public List<? extends ElkClassExpression> getClassExpressions();

	/**
	 * Accept an {@link ElkEquivalentClassesAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkEquivalentClassesAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkEquivalentClassesAxiom}.
		 * 
		 * @param first
		 *            the first equivalent {@link ElkClassExpression} for which
		 *            the axiom should be created
		 * @param second
		 *            the second equivalent {@link ElkClassExpression} for which
		 *            the axiom should be created
		 * @param other
		 *            other equivalent {@link ElkClassExpression}s for which the
		 *            axiom should be created
		 * @return an {@link ElkEquivalentClassesAxiom} corresponding to the
		 *         input
		 */
		public ElkEquivalentClassesAxiom getEquivalentClassesAxiom(
				ElkClassExpression first,
				ElkClassExpression second,
				ElkClassExpression... other);

		/**
		 * Create an {@link ElkEquivalentClassesAxiom}.
		 * 
		 * @param equivalentClassExpressions
		 *            the equivalent {@link ElkClassExpression}s for which the
		 *            axiom should be created
		 * @return an {@link ElkEquivalentClassesAxiom} corresponding to the
		 *         input
		 */
		public ElkEquivalentClassesAxiom getEquivalentClassesAxiom(
				List<? extends ElkClassExpression> equivalentClassExpressions);

	}

}
