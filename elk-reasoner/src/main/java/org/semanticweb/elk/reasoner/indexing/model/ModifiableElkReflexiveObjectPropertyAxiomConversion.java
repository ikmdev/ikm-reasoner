
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;

/**
 * An {@link ElkReflexiveObjectPropertyAxiomConversion} that can be modified as
 * a result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkReflexiveObjectPropertyAxiomConversion
		extends ElkReflexiveObjectPropertyAxiomConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkReflexiveObjectPropertyAxiomConversion getElkReflexiveObjectPropertyAxiomConversion(
				ElkReflexiveObjectPropertyAxiom originalAxiom,
				ModifiableIndexedClassExpression subClass,
				ModifiableIndexedClassExpression superClass);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ModifiableElkReflexiveObjectPropertyAxiomConversion inference);

	}

}
