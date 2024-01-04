
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;

/**
 * An {@link ElkEquivalentClassesAxiomEquivalenceConversion} that can be
 * modified as a result of updating the {@link ModifiableOntologyIndex} where
 * this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkEquivalentClassesAxiomEquivalenceConversion
		extends ElkEquivalentClassesAxiomEquivalenceConversion,
		ModifiableIndexedEquivalentClassesAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkEquivalentClassesAxiomEquivalenceConversion getElkEquivalentClassesAxiomEquivalenceConversion(
				ElkEquivalentClassesAxiom originalAxiom,
				int firstMemberPosition, int secondMemberPosition,
				ModifiableIndexedClassExpression firstMember,
				ModifiableIndexedClassExpression secondMember);

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

		O visit(ModifiableElkEquivalentClassesAxiomEquivalenceConversion inference);

	}

}
