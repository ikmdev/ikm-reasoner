
package org.semanticweb.elk.reasoner.saturation.properties.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * An {@link ObjectPropertyInference} producing a tautological
 * {@link SubPropertyChain} from no premises:<br>
 * 
 * <pre>
 * ⎯⎯⎯⎯⎯⎯⎯⎯
 *  P ⊑ P
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * P = {@link #getChain()}<br>
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 */
public class SubPropertyChainTautology
		extends AbstractSubPropertyChainInference {

	public SubPropertyChainTautology(IndexedPropertyChain chain) {
		super(chain, chain);
	}

	public IndexedPropertyChain getChain() {
		return super.getSubChain();
	}

	@Override
	public int getPremiseCount() {
		return 0;
	}

	@Override
	public Conclusion getPremise(int index, Factory factory) {
		return failGetPremise(index);
	}

	@Override
	public final <O> O accept(SubPropertyChainInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O> {

		public O visit(SubPropertyChainTautology inference);

	}

}
