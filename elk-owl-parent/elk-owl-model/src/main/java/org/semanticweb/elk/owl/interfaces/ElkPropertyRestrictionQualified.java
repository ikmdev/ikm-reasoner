
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;

/**
 * A generic interface for restrictions on properties with fillers (qualifiers).
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this restriction
 * @param <F>
 *            the type of the filler of this restriction
 */
public interface ElkPropertyRestrictionQualified<P, F>
		extends ElkPropertyRestriction<P> {

	/**
	 * Get the filler of this restriction.
	 * 
	 * @return the filer of this restriction
	 */
	F getFiller();

	/**
	 * Accept an {@link ElkPropertyRestrictionQualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkCardinalityRestrictionQualified.Factory,
			ElkDataHasValue.Factory, ElkObjectAllValuesFrom.Factory,
			ElkObjectHasValue.Factory, ElkObjectSomeValuesFrom.Factory {

		// combined interface

	}

}
