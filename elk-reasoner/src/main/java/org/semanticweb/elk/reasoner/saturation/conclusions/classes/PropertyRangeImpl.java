
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.PropertyRange;

/**
 * An implementation of {@link PropertyRange}
 * 
 * @author "Yevgeny Kazakov"
 */
public class PropertyRangeImpl extends AbstractObjectPropertyConclusion
		implements
			PropertyRange {

	private final IndexedObjectProperty property_;

	private final IndexedClassExpression range_;

	protected PropertyRangeImpl(IndexedObjectProperty property,
			IndexedClassExpression range) {
		this.property_ = property;
		this.range_ = range;
	}

	@Override
	public IndexedObjectProperty getProperty() {
		return property_;
	}

	@Override
	public IndexedClassExpression getRange() {
		return range_;
	}

	@Override
	public <O> O accept(ObjectPropertyConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(PropertyRange.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
