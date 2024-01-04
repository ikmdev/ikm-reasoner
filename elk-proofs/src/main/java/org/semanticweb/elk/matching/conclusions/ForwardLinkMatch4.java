
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatchChain;



public class ForwardLinkMatch4
		extends AbstractClassConclusionMatch<ForwardLinkMatch3> {

	private final IndexedContextRootMatchChain extendedDomains_;

	ForwardLinkMatch4(ForwardLinkMatch3 parent,
			IndexedContextRootMatchChain extendedDomains) {
		super(parent);
		this.extendedDomains_ = extendedDomains;
	}

	public IndexedContextRootMatchChain getExtendedDomains() {
		return extendedDomains_;
	}

	@Override
	public <O> O accept(ClassConclusionMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ForwardLinkMatch4 getForwardLinkMatch4(ForwardLinkMatch3 parent,
				IndexedContextRootMatchChain extendedDomains);

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

		O visit(ForwardLinkMatch4 conclusionMatch);

	}

}