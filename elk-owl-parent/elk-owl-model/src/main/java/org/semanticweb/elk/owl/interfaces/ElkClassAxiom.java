
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Class_Expression_Axioms">Class
 * Expression Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkClassAxiom extends ElkAxiom {

	/**
	 * Accept an {@link ElkClassAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkClassAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDisjointClassesAxiom.Factory,
			ElkDisjointUnionAxiom.Factory, ElkEquivalentClassesAxiom.Factory,
			ElkSubClassOfAxiom.Factory {

		// combined interface

	}

}
