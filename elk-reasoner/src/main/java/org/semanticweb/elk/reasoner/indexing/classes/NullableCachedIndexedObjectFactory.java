
package org.semanticweb.elk.reasoner.indexing.classes;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedComplexPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedDataHasValue;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedIndividual;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectComplementOf;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectHasSelf;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectUnionOf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedPropertyChain;

/**
 * A {@link CachedIndexedObject.Factory}, the methods of which can accept
 * {@code null} values for {@link IndexedObject} arguments, in which case it
 * returns {@code null} as the result
 * 
 * @author Yevgeny Kazakov
 */
class NullableCachedIndexedObjectFactory
		implements CachedIndexedObject.Factory {

	private final CachedIndexedObject.Factory delegate_;

	NullableCachedIndexedObjectFactory(CachedIndexedObject.Factory delegate) {
		this.delegate_ = delegate;
	}

	@Override
	public CachedIndexedObjectHasSelf getIndexedObjectHasSelf(
			ModifiableIndexedObjectProperty property) {
		if (property == null) {
			return null;
		}
		// else
		return delegate_.getIndexedObjectHasSelf(property);
	}

	@Override
	public CachedIndexedClass getIndexedClass(ElkClass elkClass) {
		return delegate_.getIndexedClass(elkClass);
	}

	@Override
	public CachedIndexedObjectComplementOf getIndexedObjectComplementOf(
			ModifiableIndexedClassExpression negated) {
		if (negated == null) {
			return null;
		}
		// else
		return delegate_.getIndexedObjectComplementOf(negated);
	}

	@Override
	public CachedIndexedObjectUnionOf getIndexedObjectUnionOf(
			List<? extends ModifiableIndexedClassExpression> disjuncts) {
		for (ModifiableIndexedClassExpression disjunct : disjuncts) {
			if (disjunct == null) {
				return null;
			}
		}
		// else
		return delegate_.getIndexedObjectUnionOf(disjuncts);
	}

	@Override
	public CachedIndexedObjectIntersectionOf getIndexedObjectIntersectionOf(
			ModifiableIndexedClassExpression conjunctA,
			ModifiableIndexedClassExpression conjunctB) {
		if (conjunctA == null || conjunctB == null) {
			return null;
		}
		// else
		return delegate_.getIndexedObjectIntersectionOf(conjunctA, conjunctB);
	}

	@Override
	public CachedIndexedObjectSomeValuesFrom getIndexedObjectSomeValuesFrom(
			ModifiableIndexedObjectProperty property,
			ModifiableIndexedClassExpression filler) {
		if (property == null || filler == null) {
			return null;
		}
		// else
		return delegate_.getIndexedObjectSomeValuesFrom(property, filler);
	}

	@Override
	public CachedIndexedIndividual getIndexedIndividual(
			ElkNamedIndividual elkNamedIndividual) {
		return delegate_.getIndexedIndividual(elkNamedIndividual);
	}

	@Override
	public CachedIndexedObjectProperty getIndexedObjectProperty(
			ElkObjectProperty elkObjectProperty) {
		return delegate_.getIndexedObjectProperty(elkObjectProperty);
	}

	@Override
	public CachedIndexedComplexPropertyChain getIndexedComplexPropertyChain(
			ModifiableIndexedObjectProperty leftProperty,
			ModifiableIndexedPropertyChain rightProperty) {
		if (leftProperty == null || rightProperty == null) {
			return null;
		}
		return delegate_.getIndexedComplexPropertyChain(leftProperty,
				rightProperty);
	}

	@Override
	public CachedIndexedDataHasValue getIndexedDataHasValue(
			ElkDataHasValue elkDataHasValue) {
		return delegate_.getIndexedDataHasValue(elkDataHasValue);
	}

	@Override
	public CachedIndexedClassExpressionList getIndexedClassExpressionList(
			List<? extends ModifiableIndexedClassExpression> members) {
		for (ModifiableIndexedClassExpression member : members) {
			if (member == null) {
				return null;
			}
		}
		// else
		return delegate_.getIndexedClassExpressionList(members);
	}

}
