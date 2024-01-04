
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;

/**
 * An implementation of {@link BackwardLink}
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public class BackwardLinkImpl extends AbstractSubClassConclusion implements
		BackwardLink {

	/**
	 * the source {@link IndexedContextRoot} of this {@link BackwardLinkImpl};
	 * the root of the source implies this link.
	 */
	private final IndexedContextRoot source_;

	protected BackwardLinkImpl(IndexedContextRoot root,
			IndexedObjectProperty relation, IndexedContextRoot source) {
		super(root, relation);
		this.source_ = source;
	}
	
	@Override
	public IndexedContextRoot getSource() {		
		return source_;
	}
	
	@Override
	public IndexedObjectProperty getRelation() {
		return getSubDestination();
	}
	
	@Override
	public IndexedContextRoot getTraceRoot() {
		return source_;
	}

	@Override
	public IndexedObjectProperty getTraceSubRoot() {
		return null;
	}


	@Override
	public <O> O accept(ClassConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(SubClassConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}	

	@Override
	public <O> O accept(BackwardLink.Visitor<O> visitor) {
		return visitor.visit(this);
	}	

}
