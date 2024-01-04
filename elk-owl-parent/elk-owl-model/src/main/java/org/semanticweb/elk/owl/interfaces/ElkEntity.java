 
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.predefined.PredefinedElkEntityFactory;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Entities.2C_Literals.2C_and_Anonymous_Individuals"
 * >Entity<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkEntity extends ElkObject {

	/**
	 * @return The IRI of this entity.
	 */
	public ElkIri getIri();

	/**
	 * @return the type of this entity
	 */
	public ElkEntityType getEntityType();

	/**
	 * Accept an {@link ElkEntityVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkEntityVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkAnnotationProperty.Factory, ElkClass.Factory,
			ElkDataProperty.Factory, ElkDatatype.Factory,
			ElkNamedIndividual.Factory, ElkObjectProperty.Factory,
			PredefinedElkEntityFactory {

		// combined interface

	}

}
