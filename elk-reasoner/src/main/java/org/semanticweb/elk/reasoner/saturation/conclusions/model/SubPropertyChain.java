
package org.semanticweb.elk.reasoner.saturation.conclusions.model;

import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;



import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;

/**
 * A {@link ObjectPropertyConclusion} representing an inclusion between two
 * object property (chains).
 * 
 * Notation:
 * 
 * <pre>
 * P ⊑ Q
 * </pre>
 * 
 * It is logically equivalent to axiom {@code SubObjectPropertyInclusion(P Q)}
 * <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getSubChain()}<br>
 * D = {@link #getSuperChain()}<br>
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Yevgeny Kazakov
 */
public interface SubPropertyChain extends ObjectPropertyConclusion {

	/**
	 * @return the {@code IndexedPropertyChain} corresponding to the
	 *         sub-property of the {@link ElkSubObjectPropertyOfAxiom}
	 *         represented by this {@link SubPropertyChain}
	 * 
	 * @see ElkSubObjectPropertyOfAxiom#getSubObjectPropertyExpression()
	 */
	public IndexedPropertyChain getSubChain();

	/**
	 * @return the {@code IndexedPropertyChain} corresponding to the
	 *         super-property of the {@link ElkSubObjectPropertyOfAxiom}
	 *         represented by this {@link SubPropertyChain}
	 * 
	 * @see ElkSubObjectPropertyOfAxiom#getSuperObjectPropertyExpression()
	 */
	public IndexedPropertyChain getSuperChain();

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		SubPropertyChain getSubPropertyChain(IndexedPropertyChain subChain,
				IndexedPropertyChain superChain);

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

		public O visit(SubPropertyChain conclusion);

	}

}
