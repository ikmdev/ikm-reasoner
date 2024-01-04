
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedRangeFiller;
import org.semanticweb.elk.reasoner.saturation.ExtendedContext;

/**
 * Implements {@link ModifiableIndexedRangeFiller}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableIndexedRangeFillerImpl extends IndexedObjectImpl
		implements ModifiableIndexedRangeFiller {

	private final ModifiableIndexedObjectProperty property_;

	private final ModifiableIndexedClassExpression filler_;

	private volatile ExtendedContext context_ = null;

	ModifiableIndexedRangeFillerImpl(ModifiableIndexedObjectProperty property,
			ModifiableIndexedClassExpression fillerConcept) {
		this.property_ = property;
		this.filler_ = fillerConcept;
	}

	@Override
	public ModifiableIndexedObjectProperty getProperty() {
		return this.property_;
	}

	@Override
	public ModifiableIndexedClassExpression getFiller() {
		return this.filler_;
	}

	@Override
	public final ExtendedContext getContext() {
		return this.context_;
	}

	@Override
	public final synchronized ExtendedContext setContextIfAbsent(
			ExtendedContext context) {
		if (context_ != null)
			return context_;
		// else
		context_ = context;
		return null;
	}

	@Override
	public final synchronized void resetContext() {
		context_ = null;
	}

	@Override
	public final <O> O accept(IndexedContextRoot.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(IndexedObject.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}