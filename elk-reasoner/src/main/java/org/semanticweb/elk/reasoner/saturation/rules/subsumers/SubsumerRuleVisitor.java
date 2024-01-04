
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



/**
 * A visitor pattern for {@link SubsumerRule}s
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public interface SubsumerRuleVisitor<O> extends LinkedSubsumerRuleVisitor<O>,
		SubsumerDecompositionRuleVisitor<O> {

	// nothing else

}
