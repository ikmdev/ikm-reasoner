 
package org.semanticweb.elk.owl.iris;

/**
 * An implementation of {@link ElkPrefix}
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public class ElkPrefixImpl implements ElkPrefix {

	private final String name_;
	private final ElkFullIri iri_;

	public ElkPrefixImpl(String prefixName, ElkFullIri iri) {
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
