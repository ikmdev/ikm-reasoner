
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.iris.ElkFullIri;


public interface ElkFullIriVisitor<O> {

	O visit(ElkFullIri iri);
}
