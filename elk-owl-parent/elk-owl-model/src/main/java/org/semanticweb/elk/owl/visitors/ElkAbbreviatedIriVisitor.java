
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.iris.ElkAbbreviatedIri;



public interface ElkAbbreviatedIriVisitor<O> {

	O visit(ElkAbbreviatedIri iri);
}
