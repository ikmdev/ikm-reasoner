
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.visitors.ElkDatatypeVisitor;

/**
 * A filter producing objects in {@link ElkDatatype} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDatatypeFilter extends ElkDatatypeVisitor<ElkDatatype> {

	// nothing else

}