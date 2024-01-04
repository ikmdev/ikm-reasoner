
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.iris.ElkFullIri;
import org.semanticweb.elk.owl.visitors.ElkFullIriVisitor;

/**
 * A filter producing objects in {@link ElkFullIri} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkFullIriFilter extends ElkFullIriVisitor<ElkFullIri> {

	// nothing else

}