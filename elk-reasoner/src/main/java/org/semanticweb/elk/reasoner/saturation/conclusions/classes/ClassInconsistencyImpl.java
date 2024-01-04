
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link ClassInconsistency}
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 */
public class ClassInconsistencyImpl extends AbstractClassConclusion implements
		ClassInconsistency {

	protected ClassInconsistencyImpl(IndexedContextRoot destination) {
		super(destination);
	}

	static final Logger LOGGER_ = LoggerFactory
			.getLogger(ClassInconsistencyImpl.class);

	@Override
	public <O> O accept(ClassConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ClassInconsistency.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
