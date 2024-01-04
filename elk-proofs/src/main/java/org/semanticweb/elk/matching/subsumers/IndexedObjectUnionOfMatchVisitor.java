
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;

public interface IndexedObjectUnionOfMatchVisitor<O> {
	
	O visit(ElkObjectUnionOf match);
	
	O visit(ElkObjectOneOf match);

}
