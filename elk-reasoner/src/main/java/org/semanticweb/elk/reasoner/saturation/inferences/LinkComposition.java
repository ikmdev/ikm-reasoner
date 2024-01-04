
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedComplexPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

/**
 * A common interface for inferences with premises {@link BackwardLink},
 * {@link SubPropertyChain}, {@link ForwardLink} and {@link SubPropertyChain}:<br>
 * 
 * <pre>
 *   (1)             (2)       (3)           (4)
 *  C ⊑ <∃R1>.[D]  R1 ⊑ R2  [D] ⊑ <∃P1>.E  P1 ⊑ P2  ...
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *                      ...
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * R1 = {@link #getPremiseBackwardRelation()}<br>
 * D = {@link #getOrigin()}<br>
 * P1 = {@link #getPremiseForwardChain()}<br>
 * E = {@link #getPremiseTarget()}<br>
 * R2P2 = {@link #getComposition()} (from which R2 and P2 can be obtained)<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface LinkComposition extends ClassInference {

	/**
	 * @return the BackwardLink#getSource() of the first premise
	 */
	public IndexedContextRoot getPremiseSource();

	/**
	 * @return the {@link BackwardLink#getRelation()} of the first premise
	 */
	public IndexedObjectProperty getPremiseBackwardRelation();

	/**
	 * @return the {@link ForwardLink#getChain()} of the third premise
	 */
	public IndexedPropertyChain getPremiseForwardChain();

	/**
	 * @return the {@link ForwardLink#getTarget()} of the third premise
	 */
	public IndexedContextRoot getPremiseTarget();

	/**
	 * @return the {@link IndexedComplexPropertyChain} that is produced as the
	 *         result of the composition
	 */
	public IndexedComplexPropertyChain getComposition();

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the {@link BackwardLink} used as premise in this
	 *         {@link LinkComposition}; its {@link BackwardLink#getRelation()}
	 *         equals to {@link #getPremiseBackwardRelation()}
	 */
	public BackwardLink getFirstPremise(BackwardLink.Factory factory);

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the {@link SubPropertyChain} used as premise of this
	 *         {@link LinkComposition}; its
	 *         {@link SubPropertyChain#getSubChain()} equals to
	 *         {@link #getPremiseBackwardRelation()}
	 */
	public SubPropertyChain getSecondPremise(SubPropertyChain.Factory factory);

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the {@link ForwardLink} used as premise of this
	 *         {@link LinkComposition}; its {@link ForwardLink#getChain()}
	 *         equals to {@link #getPremiseForwardChain()}
	 */
	public ForwardLink getThirdPremise(ForwardLink.Factory factory);

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 *            
	 * @return the {@link SubPropertyChain} used as premise of this
	 *         {@link LinkComposition}; its
	 *         {@link SubPropertyChain#getSubChain()} equals to
	 *         {@link #getPremiseForwardChain()}
	 */
	public SubPropertyChain getFourthPremise(SubPropertyChain.Factory factory);

	public <O> O accept(Visitor<O> visitor);

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O>
			extends
				BackwardLinkComposition.Visitor<O>,
				ForwardLinkComposition.Visitor<O> {

		// combined interface

	}

}
