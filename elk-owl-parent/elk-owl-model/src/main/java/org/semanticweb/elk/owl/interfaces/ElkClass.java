
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.predefined.PredefinedElkClassFactory;
import org.semanticweb.elk.owl.visitors.ElkClassVisitor;

/**
 * Corresponds to a <a href= "http://www.w3.org/TR/owl2-syntax/#Classes">Class
 * <a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkClass extends ElkClassExpression, ElkEntity {

	/**
	 * Accept an {@link ElkClassVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkClassVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends PredefinedElkClassFactory {

		/**
		 * Create an {@link ElkClass}.
		 * 
		 * @param iri
		 *            the {@link ElkIri} for which the object should be created
		 * @return an {@link ElkClass} corresponding to the input
		 */
		public ElkClass getClass(ElkIri iri);

	}

}
