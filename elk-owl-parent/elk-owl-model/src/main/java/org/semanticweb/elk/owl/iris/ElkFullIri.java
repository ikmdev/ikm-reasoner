
package org.semanticweb.elk.owl.iris;

import org.semanticweb.elk.owl.visitors.ElkFullIriVisitor;
import org.semanticweb.elk.owl.visitors.ElkIriVisitor;

/**
 * Represents a fully expanded IRI. This class is just a String wrapper.
 * 
 * @author Frantisek Simancik
 * 
 */
public class ElkFullIri extends ElkIri {

	protected final String iri;

	public ElkFullIri(String iri) {
		super(iri.hashCode());
		this.iri = iri;
	}

	public ElkFullIri(ElkPrefix prefix, String localName) {
		this(prefix.getIri().getFullIriAsString() + localName);
	}

	@Override
	public String getFullIriAsString() {
		return iri;
	}

	@Override
	public String toString() {
		return "<" + getFullIriAsString() + ">";
	}

	/**
	 * Accept an {@link ElkFullIriVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkFullIriVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkIriVisitor<O> visitor) {
		return accept((ElkFullIriVisitor<O>) visitor);
	}

}