
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkSWRLRule;

/**
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @param <O>
 *                the type of the output of this visitor
 */
public interface ElkSWRLRuleVisitor<O> {

	O visit(ElkSWRLRule axiom);
}
