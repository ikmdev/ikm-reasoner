
package org.semanticweb.elk.reasoner.saturation.rules.contextinit;



/**
 * A visitor pattern for {@link ContextInitRule}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public interface ContextInitRuleVisitor<O> extends
		LinkedContextInitRuleVisitor<O> {

	// nothing else

}
