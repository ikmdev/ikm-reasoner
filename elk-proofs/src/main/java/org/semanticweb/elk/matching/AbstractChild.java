
package org.semanticweb.elk.matching;

public abstract class AbstractChild<P> {

	private final P parent_;

	public AbstractChild(P parent) {
		this.parent_ = parent;
	}

	public P getParent() {
		return this.parent_;
	}

}
