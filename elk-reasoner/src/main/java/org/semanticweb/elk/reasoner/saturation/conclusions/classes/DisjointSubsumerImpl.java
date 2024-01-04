
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.DisjointSubsumer;

/**
 * An implementation of {@link DisjointSubsumer}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class DisjointSubsumerImpl extends AbstractClassConclusion implements
		DisjointSubsumer {

	private final IndexedClassExpressionList disjointExpressions_;
	
	private final int position_;

	protected DisjointSubsumerImpl(IndexedContextRoot root,
			IndexedClassExpressionList disjointExpressions, int position) {
		super(root);
		this.disjointExpressions_ = disjointExpressions;
		this.position_ = position;
	}

	@Override
	public IndexedClassExpressionList getDisjointExpressions() {
		return disjointExpressions_;
	}
	
	@Override
	public int getPosition() {
		return position_;
	}

	@Override
	public <O> O accept(ClassConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(DisjointSubsumer.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}