
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.conversion.ElkUnexpectedIndexingException;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObjectPropertyOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;

/**
 * Implements {@link ModifiableIndexedSubObjectPropertyOfAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <A>
 *            the type of the {@link ElkAxiom} from which this axiom originates
 */
class ModifiableIndexedSubObjectPropertyOfAxiomImpl<A extends ElkAxiom> extends
		IndexedSubObjectPropertyOfAxiomImpl<A, ModifiableIndexedPropertyChain, ModifiableIndexedObjectProperty>
		implements ModifiableIndexedSubObjectPropertyOfAxiom {

	ModifiableIndexedSubObjectPropertyOfAxiomImpl(A originalAxiom,
			ModifiableIndexedPropertyChain subPropertyChain,
			ModifiableIndexedObjectProperty superProperty) {
		super(originalAxiom, subPropertyChain, superProperty);
	}

	@Override
	public boolean addOccurrence(ModifiableOntologyIndex index) {
		ElkAxiom reason = getOriginalAxiom();
		ModifiableIndexedPropertyChain subPropertyChain = getSubPropertyChain();
		ModifiableIndexedObjectProperty superProperty = getSuperProperty();
		if (!subPropertyChain.addToldSuperObjectProperty(superProperty, reason))
			return false;
		if (!superProperty.addToldSubPropertyChain(subPropertyChain, reason)) {
			// revert the changes
			if (!subPropertyChain.removeToldSuperObjectProperty(superProperty,
					reason))
				throw new ElkUnexpectedIndexingException(this);
			return false;
		}
		// success
		return true;
	}

	@Override
	public boolean removeOccurrence(ModifiableOntologyIndex index) {
		ElkAxiom reason = getOriginalAxiom();
		ModifiableIndexedPropertyChain subPropertyChain = getSubPropertyChain();
		ModifiableIndexedObjectProperty superProperty = getSuperProperty();
		if (!subPropertyChain.removeToldSuperObjectProperty(superProperty,
				reason))
			return false;
		if (!superProperty.removeToldSubPropertyChain(subPropertyChain,
				reason)) {
			// revert the changes
			if (!subPropertyChain.addToldSuperObjectProperty(superProperty,
					reason))
				throw new ElkUnexpectedIndexingException(this);
			return false;
		}
		// success
		return true;
	}

	@Override
	public <O> O accept(ModifiableIndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
