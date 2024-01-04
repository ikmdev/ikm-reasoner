
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;

/**
 * An implementation of {@link SubClassInclusionDecomposed}.
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 */
public class SubClassInclusionDecomposedImpl
		extends
			AbstractSubClassInclusion<IndexedClassExpression>
		implements
			SubClassInclusionDecomposed {

	protected SubClassInclusionDecomposedImpl(IndexedContextRoot subExpression,
			IndexedClassExpression superExpression) {
		super(subExpression, superExpression);
	}

	@Override
	public <O> O accept(SubClassInclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(SubClassInclusionDecomposed.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}