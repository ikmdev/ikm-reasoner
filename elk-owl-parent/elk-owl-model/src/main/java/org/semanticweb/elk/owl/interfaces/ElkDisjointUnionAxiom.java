
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkDisjointUnionAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Disjoint_Union_of_Class_Expressions" >
 * Disjoint Union of Class Expressions Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDisjointUnionAxiom extends ElkClassAxiom {

	/**
	 * Get the class that is defined to be a disjoint union.
	 * 
	 * @return class
	 */
	public ElkClass getDefinedClass();

	/**
	 * Get the list of disjoint class expressions that this axiom refers to. The
	 * order of class expressions does not affect the semantics but it is
	 * relevant to the syntax of OWL.
	 * 
	 * @return list of disjoint class expressions
	 */
	public List<? extends ElkClassExpression> getClassExpressions();

	/**
	 * Accept an {@link ElkDisjointUnionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkDisjointUnionAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDisjointUnionAxiom}.
		 * 
		 * @param definedClass
		 *            the defined {@link ElkClassExpression} for which the axiom
		 *            should be created
		 * @param first
		 *            the first disjoint {@link ElkClassExpression} for which
		 *            the axiom should be created
		 * @param second
		 *            the second disjoint {@link ElkClassExpression} for which
		 *            the axiom should be created
		 * @param other
		 *            other disjoint {@link ElkClassExpression}s for which the
		 *            axiom should be created
		 * @return an {@link ElkDisjointUnionAxiom} corresponding to the input
		 */
		public ElkDisjointUnionAxiom getDisjointUnionAxiom(
				ElkClass definedClass, ElkClassExpression first,
				ElkClassExpression second,
				ElkClassExpression... other);

		/**
		 * Create an {@link ElkDisjointUnionAxiom}.
		 * 
		 * @param definedClass
		 *            the defined {@link ElkClassExpression} for which the axiom
		 *            should be created
		 * @param disjointClassExpressions
		 *            the disjoint {@link ElkClassExpression}s for which the
		 *            axiom should be created
		 * @return an {@link ElkDisjointUnionAxiom} corresponding to the input
		 */
		public ElkDisjointUnionAxiom getDisjointUnionAxiom(
				ElkClass definedClass,
				List<? extends ElkClassExpression> disjointClassExpressions);

	}

}
