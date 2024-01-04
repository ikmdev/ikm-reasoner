
package org.semanticweb.elk.owl.predefined;

import org.semanticweb.elk.owl.iris.ElkFullIri;
import org.semanticweb.elk.owl.iris.ElkPrefix;

/**
 * Corresponds to <a href= "http://www.w3.org/TR/owl2-syntax/#IRIs" >Standard
 * prefix names<a> in OWL 2 (see Table 2 in the link).
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public enum PredefinedElkPrefix implements ElkPrefix {

	RDF("rdf:", new ElkFullIri("http://www.w3.org/1999/02/22-rdf-syntax-ns#")),

	RDFS("rdfs:", new ElkFullIri("http://www.w3.org/2000/01/rdf-schema#")),

	XSD("xsd:", new ElkFullIri("http://www.w3.org/2001/XMLSchema#")),

	OWL("owl:", new ElkFullIri("http://www.w3.org/2002/07/owl#")),

	;

	private final String name_;
	private final ElkFullIri iri_;

	PredefinedElkPrefix(String prefixName, ElkFullIri iri) {
		this.name_ = prefixName;
		this.iri_ = iri;
	}

	@Override
	public String getName() {
		return name_;
	}

	@Override
	public ElkFullIri getIri() {
		return iri_;
	}

}
