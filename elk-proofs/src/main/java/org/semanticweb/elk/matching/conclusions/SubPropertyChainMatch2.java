 
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;

public class SubPropertyChainMatch2
		extends AbstractObjectPropertyConclusionMatch<SubPropertyChainMatch1> {

	private final ElkSubObjectPropertyExpression fullSubChainMatch_;

	private final int subChainStartPos_;

	SubPropertyChainMatch2(SubPropertyChainMatch1 parent,
			ElkSubObjectPropertyExpression fullSubChainMatch,
			int subChainStartPos) {
		super(parent);
		checkChainMatch(fullSubChainMatch, subChainStartPos);
		this.fullSubChainMatch_ = fullSubChainMatch;
		this.subChainStartPos_ = subChainStartPos;
	}

	public ElkSubObjectPropertyExpression getFullSubChainMatch() {
		return this.fullSubChainMatch_;
	}

	public int getSubChainStartPos() {
		return subChainStartPos_;
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

		SubPropertyChainMatch2 getSubPropertyChainMatch2(
				SubPropertyChainMatch1 parent,
				ElkSubObjectPropertyExpression fullSubChainMatch,
				int subChainStartPos);

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

		O visit(SubPropertyChainMatch2 conclusionMatch);

	}

}
