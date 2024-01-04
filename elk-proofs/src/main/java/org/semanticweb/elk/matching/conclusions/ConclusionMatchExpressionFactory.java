
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.reasoner.tracing.Conclusion;



/**
 * A factory for creating conclusions and their matches
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ConclusionMatchExpressionFactory
		extends ElkObject.Factory, Conclusion.Factory, ConclusionMatch.Factory,
		IndexedContextRootMatch.Factory {

	// combined interface

}
