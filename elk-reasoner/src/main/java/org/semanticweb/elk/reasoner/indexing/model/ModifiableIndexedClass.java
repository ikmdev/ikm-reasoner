
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;

/**
 * An {@link IndexedClass} that can be modified as a result of updating the
 * {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedClass
		extends
			ModifiableIndexedClassEntity,
			IndexedClass {

	/**
	 * Set the given {@link ModifiableIndexedClassExpression} as a definition of
	 * this {@link IndexedClass} if is not defined
	 * 
	 * @param definition
	 *            the new definition for this {@link IndexedClass}
	 * @param reason
	 *            the {@link ElkAxiom} from which the definition originates
	 * @return {@code true} if this operation is successful or {@code false}
	 *         otherwise; the letter is returned if this {@link IndexedClass}
	 *         was already defined ({@link #getDefinition()} returns
	 *         {@code null})
	 */
	boolean setDefinition(ModifiableIndexedClassExpression definition,
			ElkAxiom reason);

	/**
	 * Removes the definition for this {@link IndexedClass}; after calling this
	 * method, {@link #getDefinition()} returns {@code null}
	 */
	void removeDefinition();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedClass getIndexedClass(ElkClass elkClass);

	}

}
