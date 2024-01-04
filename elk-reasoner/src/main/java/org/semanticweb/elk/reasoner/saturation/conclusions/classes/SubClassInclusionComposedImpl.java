
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

/**
 * An implementation of {@link SubClassInclusionComposed}
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 * @param <S>
 *            the type of the subsumer {@link IndexedClassExpression}
 */
public class SubClassInclusionComposedImpl<S extends IndexedClassExpression>
		extends
			AbstractSubClassInclusion<S>
		implements SubClassInclusionComposed {

	protected SubClassInclusionComposedImpl(IndexedContextRoot root,
			S subsumer) {
		super(root, subsumer);
	}

	@Override
	public <O> O accept(SubClassInclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(SubClassInclusionComposed.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}