
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch4;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatchChain;

public class ForwardLinkOfObjectSomeValuesFromMatch3 extends
		AbstractInferenceMatch<ForwardLinkOfObjectSomeValuesFromMatch2> {

	private final IndexedContextRootMatch extendedTargetMatch_;

	ForwardLinkOfObjectSomeValuesFromMatch3(
			ForwardLinkOfObjectSomeValuesFromMatch2 parent,
			ForwardLinkMatch3 conclusionMatch) {
		super(parent);
		this.extendedTargetMatch_ = conclusionMatch.getExtendedTargetMatch();
		checkEquals(conclusionMatch, getParentConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedTargetMatch() {
		return extendedTargetMatch_;
	}

	ForwardLinkMatch3 getParentConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch3(
				getParent().getConclusionMatch(factory),
				getExtendedTargetMatch());
	}

	public ForwardLinkMatch4 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch4(getParentConclusionMatch(factory),
				new IndexedContextRootMatchChain(
						getParent().getExtendedOriginMatch()));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> {

		O visit(ForwardLinkOfObjectSomeValuesFromMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ForwardLinkOfObjectSomeValuesFromMatch3 getForwardLinkOfObjectSomeValuesFromMatch3(
				ForwardLinkOfObjectSomeValuesFromMatch2 parent,
				ForwardLinkMatch3 conclusionMatch);

	}

}
