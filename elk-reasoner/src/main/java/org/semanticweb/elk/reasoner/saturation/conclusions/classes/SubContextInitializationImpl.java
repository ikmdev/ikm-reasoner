
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.InitializationConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;

/**
 * An implementation of {@link SubContextInitialization}
 * 
 * @author "Yevgeny Kazakov"
 */
public class SubContextInitializationImpl extends AbstractSubClassConclusion
		implements
			SubContextInitialization {

	protected SubContextInitializationImpl(IndexedContextRoot root,
			IndexedObjectProperty subRoot) {
		super(root, subRoot);
	}

	@Override
	public <O> O accept(SubClassConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(SubContextInitialization.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(InitializationConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
