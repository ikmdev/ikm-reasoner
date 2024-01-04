
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDeclarationAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEntity;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObjectPropertyOfAxiom;

/**
 * A {@link ModifiableIndexedObject.Factory}, the methods of which can accept
 * {@code null} values for {@link IndexedObject} arguments, in which case it
 * returns {@code null} as the result
 * 
 * @author Yevgeny Kazakov
 *
 */
class NullableModifiableIndexedObjectFactory
		extends NullableCachedIndexedObjectFactory
		implements ModifiableIndexedObject.Factory {

	private final ModifiableIndexedObject.Factory delegate_;

	NullableModifiableIndexedObjectFactory() {
		this(new ModifiableIndexedObjectBaseFactory());
	}

	<F extends CachedIndexedObject.Factory & ModifiableIndexedObject.Factory> NullableModifiableIndexedObjectFactory(
			F delegate) {
		super(delegate);
		this.delegate_ = delegate;
	}

	@Override
	public ModifiableIndexedDeclarationAxiom getIndexedDeclarationAxiom(
			ElkAxiom originalAxiom, ModifiableIndexedEntity entity) {
		if (originalAxiom == null || entity == null) {
			return null;
		}
		// else
		return delegate_.getIndexedDeclarationAxiom(originalAxiom, entity);
	}

	@Override
	public ModifiableIndexedDisjointClassesAxiom getIndexedDisjointClassesAxiom(
			ElkAxiom originalAxiom,
			ModifiableIndexedClassExpressionList members) {
		if (originalAxiom == null || members == null) {
			return null;
		}
		// else
		return delegate_.getIndexedDisjointClassesAxiom(originalAxiom, members);
	}

	@Override
	public ModifiableIndexedEquivalentClassesAxiom getIndexedEquivalentClassesAxiom(
			ElkAxiom originalAxiom,
			ModifiableIndexedClassExpression firstMember,
			ModifiableIndexedClassExpression secondMember) {
		if (firstMember == null || secondMember == null) {
			return null;
		}
		// else
		return delegate_.getIndexedEquivalentClassesAxiom(originalAxiom,
				firstMember, secondMember);
	}

	@Override
	public ModifiableIndexedObjectPropertyRangeAxiom getIndexedObjectPropertyRangeAxiom(
			ElkAxiom originalAxiom, ModifiableIndexedObjectProperty property,
			ModifiableIndexedClassExpression range) {
		if (originalAxiom == null || property == null || range == null) {
			return null;
		}
		// else
		return delegate_.getIndexedObjectPropertyRangeAxiom(originalAxiom,
				property, range);
	}

	@Override
	public ModifiableIndexedSubClassOfAxiom getIndexedSubClassOfAxiom(
			ElkAxiom originalAxiom, ModifiableIndexedClassExpression subClass,
			ModifiableIndexedClassExpression superClass) {
		if (originalAxiom == null || subClass == null) {
			return null;
		}
		// else
		return delegate_.getIndexedSubClassOfAxiom(originalAxiom, subClass,
				superClass);
	}

	@Override
	public ModifiableIndexedSubObjectPropertyOfAxiom getIndexedSubObjectPropertyOfAxiom(
			ElkAxiom originalAxiom,
			ModifiableIndexedPropertyChain subPropertyChain,
			ModifiableIndexedObjectProperty superProperty) {
		if (originalAxiom == null || subPropertyChain == null
				|| superProperty == null) {
			return null;
		}
		// else
		return delegate_.getIndexedSubObjectPropertyOfAxiom(originalAxiom,
				subPropertyChain, superProperty);
	}

}
