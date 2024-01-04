
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkSWRLRule;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSWRLRuleVisitor;

/**
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 */
public class ElkSWRLRuleImpl implements ElkSWRLRule {

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkSWRLRuleVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkSWRLRuleVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSWRLRuleVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
