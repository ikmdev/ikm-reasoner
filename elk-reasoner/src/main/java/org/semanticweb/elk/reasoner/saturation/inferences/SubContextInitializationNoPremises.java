
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link SubContextInitializationInference} that produces a
 * {@link SubContextInitialization} from no premises:<br>
 * 
 * <pre>
 * 
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  ![C:R]
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * R = {@link #getSubDestination()}
 * 
 * @author "Yevgeny Kazakov"
 */
public class SubContextInitializationNoPremises
		extends AbstractSubContextInitializationInference {

	public SubContextInitializationNoPremises(IndexedContextRoot root,
			IndexedObjectProperty subRoot) {
		super(root, subRoot);
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getDestination();
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
			SubContextInitializationInference.Visitor<O> visitor) {
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

		public O visit(SubContextInitializationNoPremises inference);

	}

}
