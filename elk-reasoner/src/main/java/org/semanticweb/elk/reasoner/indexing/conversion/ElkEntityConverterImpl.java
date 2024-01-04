
package org.semanticweb.elk.reasoner.indexing.conversion;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;



import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.reasoner.completeness.Feature;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEntity;

/**
 * An implementation of {@link ElkEntityConverter} that uses a given
 * {@link ModifiableIndexedEntity.Factory} for creating instances of
 * {@link ModifiableIndexedEntity}
 * 
 * @author "Yevgeny Kazakov"
 *
 */
class ElkEntityConverterImpl implements ElkEntityConverter {

	private final ModifiableIndexedEntity.Factory factory_;

	public ElkEntityConverterImpl(ModifiableIndexedEntity.Factory factory) {
		this.factory_ = factory;
	}

	private ElkIndexingUnsupportedFeature unsupported(Feature feature) {
		return new ElkIndexingUnsupportedFeature(feature);
	}

	@Override
	public ModifiableIndexedEntity visit(ElkAnnotationProperty expression) {
		// no effect on reasoning
		return null;
	}

	@Override
	public ModifiableIndexedEntity visit(ElkClass expression) {
		return factory_.getIndexedClass(expression);
	}

	@Override
	public ModifiableIndexedEntity visit(ElkDataProperty expression) {
		throw unsupported(Feature.DATA_PROPERTY);
	}

	@Override
	public ModifiableIndexedEntity visit(ElkDatatype expression) {
		throw unsupported(Feature.DATATYPE);
	}

	@Override
	public ModifiableIndexedEntity visit(ElkNamedIndividual expression) {
		return factory_.getIndexedIndividual(expression);
	}

	@Override
	public ModifiableIndexedEntity visit(ElkObjectProperty expression) {
		return factory_.getIndexedObjectProperty(expression);
	}
	
	

}
