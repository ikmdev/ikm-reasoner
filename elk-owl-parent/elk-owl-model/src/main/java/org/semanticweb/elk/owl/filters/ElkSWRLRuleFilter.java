
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkSWRLRule;
import org.semanticweb.elk.owl.visitors.ElkSWRLRuleVisitor;

/**
 * A filter producing objects in {@link ElkSWRLRule} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkSWRLRuleFilter extends ElkSWRLRuleVisitor<ElkSWRLRule> {

	// nothing else

}