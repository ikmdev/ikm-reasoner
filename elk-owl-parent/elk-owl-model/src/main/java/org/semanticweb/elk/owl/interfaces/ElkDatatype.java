
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.predefined.PredefinedElkDatatypeFactory;
import org.semanticweb.elk.owl.visitors.ElkDatatypeVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Datatypes">Datatype<a> in the OWL
 * 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDatatype extends ElkDataRange, ElkEntity {

	/**
	 * Accept an {@link ElkDatatypeVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDatatypeVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends PredefinedElkDatatypeFactory {

		/**
		 * Create an {@link ElkDatatype}.
		 * 
		 * @param iri
		 *            the {@link ElkIri} for which the object should be created
		 * @return an {@link ElkDatatype} corresponding to the input
		 */
		public ElkDatatype getDatatype(ElkIri iri);

		/**
		 * Create the ElkDatatype for rdf:PlainLiteral}.
		 * 
		 * @return an {@link ElkDatatype} corresponding to the input
		 */
		public ElkDatatype getDatatypeRdfPlainLiteral();

	}

}
