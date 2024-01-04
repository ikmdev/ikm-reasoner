
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;

/**
 * Implements {@link ModifiableIndexedObjectPropertyRangeAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <A>
 *            the type of the {@link ElkAxiom} from which this axiom originates
 */
class ModifiableIndexedObjectPropertyRangeAxiomImpl<A extends ElkAxiom> extends
		IndexedObjectPropertyRangeAxiomImpl<A, ModifiableIndexedObjectProperty, ModifiableIndexedClassExpression>
		implements ModifiableIndexedObjectPropertyRangeAxiom {

	ModifiableIndexedObjectPropertyRangeAxiomImpl(A originalAxiom,
			ModifiableIndexedObjectProperty property,
			ModifiableIndexedClassExpression range) {
		super(originalAxiom, property, range);
	}

	@Override
	public boolean addOccurrence(ModifiableOntologyIndex index) {
		return getProperty().addToldRange(getRange(), getOriginalAxiom());
	}

	@Override
	public boolean removeOccurrence(ModifiableOntologyIndex index) {
		return getProperty().removeToldRange(getRange(), getOriginalAxiom());
	}

	@Override
	public <O> O accept(ModifiableIndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}