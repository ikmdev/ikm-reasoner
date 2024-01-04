
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Data_Ranges">Data Range<a> in the
 * OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataRange extends ElkObject {

	/**
	 * Accept an {@link ElkDataRangeVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataRangeVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ElkDataComplementOf.Factory, ElkDataIntersectionOf.Factory,
			ElkDataOneOf.Factory, ElkDatatype.Factory,
			ElkDatatypeRestriction.Factory, ElkDataUnionOf.Factory {

		// combined interface

	}

}