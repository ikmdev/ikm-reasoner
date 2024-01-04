
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Processes axioms with the provided processor and visits them before and after
 * the processing by the provided visitors.
 * 
 * @author Peter Skocovsky
 */
public class DecoratingElkAxiomProcessor implements ElkAxiomProcessor {

	private final ElkAxiomVisitor<?> pre_;
	private final ElkAxiomProcessor processor_;
	private final ElkAxiomVisitor<?> post_;

	public DecoratingElkAxiomProcessor(final ElkAxiomVisitor<?> pre,
			final ElkAxiomProcessor processor, final ElkAxiomVisitor<?> post) {
		this.pre_ = pre;
		this.processor_ = processor;
		this.post_ = post;
	}

	@Override
	public void visit(final ElkAxiom elkAxiom) {
		elkAxiom.accept(pre_);
		processor_.visit(elkAxiom);
		elkAxiom.accept(post_);
	}

}
