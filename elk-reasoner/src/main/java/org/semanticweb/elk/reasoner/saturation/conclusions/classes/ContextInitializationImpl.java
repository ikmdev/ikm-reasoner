
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.InitializationConclusion;

/**
 * An implementation of {@link ContextInitialization}.
 * 
 * @author "Yevgeny Kazakov"
 */
public class ContextInitializationImpl extends AbstractClassConclusion
		implements
			ContextInitialization {

	protected ContextInitializationImpl(IndexedContextRoot root) {
		super(root);
	}

	@Override
	public <O> O accept(ClassConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ContextInitialization.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(InitializationConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
