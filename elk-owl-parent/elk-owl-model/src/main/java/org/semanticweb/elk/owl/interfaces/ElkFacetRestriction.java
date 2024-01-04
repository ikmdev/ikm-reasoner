
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkFacetRestrictionVisitor;

/**
 * Corresponds to a pair of constraining facet and restriction value as used in
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Datatype_Restrictions">OWL 2
 * Datatype Restrictions<a>.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkFacetRestriction extends ElkObject {

	/**
	 * Get the IRI of the constraining facet.
	 * 
	 * @return IRI of the facet
	 */
	public ElkIri getConstrainingFacet();

	/**
	 * Get the literal used as a restriction value.
	 * 
	 * @return the literal used as restriction value
	 */
	public ElkLiteral getRestrictionValue();

	/**
	 * Accept an {@link ElkFacetRestrictionVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkFacetRestrictionVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkFacetRestriction}
		 * 
		 * @param iri
		 *            the {@link ElkIri} for which the object should be created
		 * @param literal
		 *            the {@link ElkLiteral} for which the object should be
		 *            created
		 * @return an {@link ElkFacetRestriction} corresponding to the input
		 */
		public ElkFacetRestriction getFacetRestriction(ElkIri iri,
				ElkLiteral literal);
	}
}
