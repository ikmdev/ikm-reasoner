
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.reasoner.saturation.rules.subsumers.ChainableSubsumerRule;
import org.semanticweb.elk.util.collections.chains.Chain;

/**
 * An {@link IndexedClassExpression} that can be modified as a result of
 * updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedClassExpression extends
		ModifiableIndexedSubObject, IndexedClassExpression {

	/**
	 * @return the {@link Chain} view of all composition rules assigned to this
	 *         {@link IndexedClassExpression}; this is always not {@code null}.
	 *         This method can be used for convenient search and modification
	 *         (addition and deletion) of the rules using the methods of the
	 *         {@link Chain} interface without worrying about {@code null}
	 *         values.
	 */
	Chain<ChainableSubsumerRule> getCompositionRuleChain();
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				ModifiableIndexedClassEntity.Factory,
				ModifiableIndexedDataHasValue.Factory,
				ModifiableIndexedIndividual.Factory,
				ModifiableIndexedObjectComplementOf.Factory,
				ModifiableIndexedObjectHasSelf.Factory,
				ModifiableIndexedObjectIntersectionOf.Factory,
				ModifiableIndexedObjectSomeValuesFrom.Factory,
				ModifiableIndexedObjectUnionOf.Factory {

		// combined interface

	}

}
