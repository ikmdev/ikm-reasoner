
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;

/**
 * Represents a transformation of an {@link ElkReflexiveObjectPropertyAxiom} to
 * an {@link IndexedSubClassOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkReflexiveObjectPropertyAxiomConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkReflexiveObjectPropertyAxiom getOriginalAxiom();
	
	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {
		
		O visit(ElkReflexiveObjectPropertyAxiomConversion inference);
		
	}

}
