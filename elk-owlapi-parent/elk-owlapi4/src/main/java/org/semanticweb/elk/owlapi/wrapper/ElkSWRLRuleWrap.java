
package org.semanticweb.elk.owlapi.wrapper;



import org.semanticweb.elk.owl.interfaces.ElkSWRLRule;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSWRLRuleVisitor;
import org.semanticweb.owlapi.model.SWRLRule;

/**
 * Just as dummy as {@link ElkSWRLRule}
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 *         
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkSWRLRuleWrap<T extends SWRLRule> extends ElkObjectWrap<T>
		implements ElkSWRLRule {

	public ElkSWRLRuleWrap(T rule) {
		super(rule);
	}

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
