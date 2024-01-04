
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.indexing.classes.DummyIndexedContextRootVisitor;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedRangeFiller;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

// TODO: split on two inferences
/**
 * A {@link ClassInference} producing a {@link SubClassInclusionDecomposed} from
 * {@link ContextInitialization}:<br>
 * 
 * <pre>
 *   ![C]
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  [C] ⊑ -C
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()} =
 * {@link #getConclusionSubsumer()}<br>
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author Yevgeny Kazakov
 */
public class SubClassInclusionTautology
		extends AbstractSubClassInclusionDecomposedInference {

	private static IndexedContextRoot.Visitor<IndexedClassExpression> SUBSUMER_EXTRACTOR_ = new SubsumerExtractor();

	public SubClassInclusionTautology(IndexedContextRoot inferenceRoot) {
		super(inferenceRoot, inferenceRoot.accept(SUBSUMER_EXTRACTOR_));
	}

	private static class SubsumerExtractor
			extends DummyIndexedContextRootVisitor<IndexedClassExpression> {

		@Override
		protected IndexedClassExpression defaultVisit(
				IndexedClassExpression element) {
			return element;
		}

		@Override
		public IndexedClassExpression visit(IndexedRangeFiller element) {
			return element.getFiller();
		}
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getDestination();
	}

	public ContextInitialization getPremise(
			ContextInitialization.Factory factory) {
		return factory.getContextInitialization(getOrigin());
	}

	@Override
	public int getPremiseCount() {
		return 1;
	}

	@Override
	public Conclusion getPremise(int index, Factory factory) {
		switch (index) {
		case 0:
			return getPremise(factory);
		default:
			return failGetPremise(index);
		}
	}

	@Override
	public final <O> O accept(
			SubClassInclusionDecomposedInference.Visitor<O> visitor) {
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

		public O visit(SubClassInclusionTautology inference);

	}

}
