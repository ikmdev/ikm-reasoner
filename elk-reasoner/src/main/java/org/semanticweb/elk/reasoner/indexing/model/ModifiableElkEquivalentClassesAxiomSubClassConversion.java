
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;

/**
 * An {@link ElkEquivalentClassesAxiomSubClassConversion} that can be modified
 * as a result of updating the {@link ModifiableOntologyIndex} where this object
 * is stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkEquivalentClassesAxiomSubClassConversion
		extends
			ElkEquivalentClassesAxiomSubClassConversion,
			ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {
		
		ModifiableElkEquivalentClassesAxiomSubClassConversion getElkEquivalentClassesAxiomSubClassConversion(
				ElkEquivalentClassesAxiom originalAxiom, int subClassPosition,
				int superClassPosition,
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

		O visit(ModifiableElkEquivalentClassesAxiomSubClassConversion inference);

	}


}
