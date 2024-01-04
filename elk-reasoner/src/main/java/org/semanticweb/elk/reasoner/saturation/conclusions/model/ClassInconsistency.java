
package org.semanticweb.elk.reasoner.saturation.conclusions.model;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;



/**
 * A {@link ClassConclusion} representing inconsistency of the class expression
 * corresponding to {@link #getDestination()}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * [C] = 0
 * </pre>
 * 
 * It is logically equivalent to axiom {@code SubClassOf(C owl:Nothing)}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * 
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ClassInconsistency extends ClassConclusion {

	public static final String NAME = "Contradiction";

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ClassInconsistency getContradiction(IndexedContextRoot destination);

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

		public O visit(ClassInconsistency conclusion);

	}

}
