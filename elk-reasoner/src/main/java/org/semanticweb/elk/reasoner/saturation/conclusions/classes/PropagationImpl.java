
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link Propagation}
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 */
public class PropagationImpl extends AbstractSubClassConclusion
		implements
			Propagation {

	// logger for this class
	static final Logger LOGGER_ = LoggerFactory
			.getLogger(PropagationImpl.class);

	private final IndexedObjectSomeValuesFrom carry_;

	protected PropagationImpl(IndexedContextRoot root,
			IndexedObjectProperty relation, IndexedObjectSomeValuesFrom carry) {
		super(root, relation);
		carry_ = carry;
	}

	@Override
	public IndexedObjectProperty getRelation() {
		return getSubDestination();
	}

	@Override
	public IndexedObjectSomeValuesFrom getCarry() {
		return this.carry_;
	}	

	@Override
	public <O> O accept(SubClassConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(Propagation.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
