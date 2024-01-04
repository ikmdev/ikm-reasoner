
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

/**
 * An {@link IndexedClassExpression} constructed form an {@link ElkClass}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * A
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * A = {@link #getElkEntity()}<br>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public interface IndexedClass extends IndexedClassEntity {

	@Override
	ElkClass getElkEntity();

	/**
	 * @return The {@link IndexedClassExpression} corresponding to an
	 *         {@link ElkClassExpression} defined equivalent to the enclosed
	 *         {@link ElkClass} in the ontology. There can be several such
	 *         equivalent {@link ElkClassExpression}s in the ontology, but at
	 *         most one of them should be chosen as the definition; the value
	 *         can be {@code null} if there are no such equivalent
	 *         {@link ElkClassExpression}s.
	 */
	IndexedClassExpression getDefinition();

	/**
	 * @return the {@link ElkAxiom} from which the definition of this
	 *         {@link IndexedClass} originates or {@code null} if this
	 *         {@link IndexedClass} is not defined.
	 */
	ElkAxiom getDefinitionReason();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedClass element);

	}

}
