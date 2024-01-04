
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;

/**
 * Represents a transformation of an {@link ElkEquivalentObjectPropertiesAxiom}
 * to an {@link IndexedSubObjectPropertyOfAxiom} representing the inclusion
 * between two selected members of the {@link ElkEquivalentClassesAxiom}.
 * 
 * @see ElkEquivalentObjectPropertiesAxiom#getObjectPropertyExpressions()
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkEquivalentObjectPropertiesAxiomConversion
		extends
			IndexedSubObjectPropertyOfAxiomInference {

	@Override
	ElkEquivalentObjectPropertiesAxiom getOriginalAxiom();

	/**
	 * @return the position of an {@link ElkDataPropertyExpression} in the
	 *         member list of the {@link ElkEquivalentObjectPropertiesAxiom}
	 *         that is converted to the sub-property chain of the
	 *         {@link IndexedSubObjectPropertyOfAxiom}.
	 * 
	 * @see ElkEquivalentObjectPropertiesAxiom#getObjectPropertyExpressions()
	 * @see IndexedSubObjectPropertyOfAxiom#getSubPropertyChain()
	 */
	int getSubPropertyPosition();

	/**
	 * @return the position of an {@link ElkDataPropertyExpression} in the
	 *         member list of the {@link ElkEquivalentObjectPropertiesAxiom}
	 *         that is converted to the super-property of the
	 *         {@link IndexedSubObjectPropertyOfAxiom}.
	 * 
	 * @see ElkEquivalentObjectPropertiesAxiom#getObjectPropertyExpressions()
	 * @see IndexedSubObjectPropertyOfAxiom#getSuperProperty()
	 */
	int getSuperPropertyPosition();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkEquivalentObjectPropertiesAxiomConversion inference);

	}

}
