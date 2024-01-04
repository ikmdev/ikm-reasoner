
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.predefined.PredefinedElkObjectPropertyFactory;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Object_Properties">Object
 * Property<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectProperty
		extends ElkObjectPropertyExpression, ElkEntity {

	/**
	 * Accept an {@link ElkObjectPropertyVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectPropertyVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends PredefinedElkObjectPropertyFactory {

		/**
		 * Create an {@link ElkObjectProperty}.
		 * 
		 * @param iri
		 *            the {@link ElkIri} for which the object should be created
		 * @return an {@link ElkObjectProperty} corresponding to the input
		 */
		public ElkObjectProperty getObjectProperty(ElkIri iri);

	}
}
