
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.reasoner.saturation.rules.backwardlinks.BackwardLinkRuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.contextinit.ContextInitRuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.contradiction.ContradictionRuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.disjointsubsumer.DisjointSubsumerRuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.forwardlink.ForwardLinkRuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.propagations.PropagationRuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.subcontextinit.SubContextInitRuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.SubsumerRuleVisitor;

/**
 * A visitor pattern for {@link Rule}s together with the parameters for which
 * these rules are applied.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public interface RuleVisitor<O> extends SubsumerRuleVisitor<O>,
		BackwardLinkRuleVisitor<O>, ContextInitRuleVisitor<O>,
		SubContextInitRuleVisitor<O>, ContradictionRuleVisitor<O>,
		DisjointSubsumerRuleVisitor<O>, ForwardLinkRuleVisitor<O>,
		PropagationRuleVisitor<O> {

	// nothing else
}
