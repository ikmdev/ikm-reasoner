
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.iris.ElkIri;

/**
 * Implementation for ElkObjects that maintain an IRI.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkIriObject extends ElkObjectImpl {

	private final ElkIri iri_;

	ElkIriObject(ElkIri iri) {
		this.iri_ = iri;
	}

	public ElkIri getIri() {
		return iri_;
	}

	@Override
	public String toString() {
		return iri_.toString();
	}
}