
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkDisjointClassesAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Disjoint_Classes">Disjoint
 * Classes Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDisjointClassesAxiom extends ElkClassAxiom {

	/**
	 * Get the list of disjoint class expressions that this axiom refers to. The
	 * order of class expressions does not affect the semantics but it is
	 * relevant to the syntax of OWL.
	 * 
	 * @return list of disjoint class expressions
	 */
	public List<? extends ElkClassExpression> getClassExpressions();

	/**
	 * Accept an {@link ElkDisjointClassesAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkDisjointClassesAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDisjointClassesAxiom}.
		 * 
		 * @param first
		 *            the first {@link ElkClassExpression} for which the axiom
		 *            should be created
		 * @param second
		 *            the second {@link ElkClassExpression} for which the axiom
		 *            should be created
		 * @param other
		 *            other {@link ElkClassExpression} for which the axiom
		 *            should be created
		 * @return an {@link ElkDisjointClassesAxiom} corresponding to the input
		 */
		public ElkDisjointClassesAxiom getDisjointClassesAxiom(
				ElkClassExpression first,
				ElkClassExpression second,
				ElkClassExpression... other);

		/**
		 * Create an {@link ElkDisjointClassesAxiom}.
		 * 
		 * @param disjointClassExpressions
		 *            the {@link ElkClassExpression}s for which the axiom should
		 *            be created
		 * @return an {@link ElkDisjointClassesAxiom} corresponding to the input
		 */
		public ElkDisjointClassesAxiom getDisjointClassesAxiom(
				List<? extends ElkClassExpression> disjointClassExpressions);
	}

}
