
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link ContextInitialization} conclusion
 * from no premises:<br>
 * 
 * <pre>
 * 
 * ⎯⎯⎯⎯⎯⎯⎯⎯
 *  ![C]
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public class ContextInitializationNoPremises
		extends AbstractContextInitializationInference {

	public ContextInitializationNoPremises(IndexedContextRoot root) {
		super(root);
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return null;
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
	public final <O> O accept(
			ContextInitializationInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(InitializationInference.Visitor<O> visitor) {
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

		public O visit(ContextInitializationNoPremises inference);

	}

}
