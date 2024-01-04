
package org.semanticweb.elk.matching.conclusions;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;



import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

public class SubPropertyChainMatch1
		extends AbstractObjectPropertyConclusionMatch<SubPropertyChain> {

	private final ElkSubObjectPropertyExpression fullSuperChainMatch_;

	private final int superChainStartPos_;

	SubPropertyChainMatch1(SubPropertyChain parent,
			ElkSubObjectPropertyExpression fullSuperChainMatch,
			int superChainStartPos) {
		super(parent);
		checkChainMatch(fullSuperChainMatch, superChainStartPos);
		if (fullSuperChainMatch instanceof ElkObjectPropertyChain) {
			List<? extends ElkObjectPropertyExpression> expressions = ((ElkObjectPropertyChain) fullSuperChainMatch)
					.getObjectPropertyExpressions();
			if (superChainStartPos == expressions.size() - 1) {
				// only the last property is matched
				this.fullSuperChainMatch_ = expressions.get(superChainStartPos);
				this.superChainStartPos_ = 0;
				return;
			}
		}
		// else
		this.fullSuperChainMatch_ = fullSuperChainMatch;
		this.superChainStartPos_ = superChainStartPos;
	}

	public ElkSubObjectPropertyExpression getFullSuperChainMatch() {
		return fullSuperChainMatch_;
	}

	public int getSuperChainStartPos() {
		return superChainStartPos_;
	}

	@Override
	public <O> O accept(ObjectPropertyConclusionMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubPropertyChainMatch1 getSubPropertyChainMatch1(
				SubPropertyChain parent,
				ElkSubObjectPropertyExpression fullSuperChainMatch,
				int superChainStartPos);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(SubPropertyChainMatch1 conclusionMatch);

	}

}
