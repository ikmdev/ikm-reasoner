
package org.semanticweb.elk.matching.root;



public class IndexedContextRootMatchChain {

	private final IndexedContextRootMatch head_;

	private final IndexedContextRootMatchChain tail_;

	public IndexedContextRootMatchChain(IndexedContextRootMatch head,
			IndexedContextRootMatchChain tail) {
		this.head_ = head;
		this.tail_ = tail;
	}

	public IndexedContextRootMatchChain(IndexedContextRootMatch head) {
		this(head, null);
	}

	public IndexedContextRootMatch getHead() {
		return head_;
	}

	public IndexedContextRootMatchChain getTail() {
		return tail_;
	}

}
