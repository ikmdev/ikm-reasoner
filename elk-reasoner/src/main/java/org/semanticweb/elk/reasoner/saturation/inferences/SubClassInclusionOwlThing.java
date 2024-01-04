
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionComposed} from
 * {@link ContextInitialization}:<br>
 * 
 * <pre>
 *    ![C]
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  [C] ⊑ +⊤
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()}<br>
 * 
 * @author Yevgeny Kazakov
 */
public class SubClassInclusionOwlThing
		extends AbstractSubClassInclusionComposedInference<IndexedClass> {

	public SubClassInclusionOwlThing(IndexedContextRoot inferenceRoot,
			IndexedClass owlThingSubsumer) {
		super(inferenceRoot, owlThingSubsumer);
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
			SubClassInclusionComposedInference.Visitor<O> visitor) {
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

		public O visit(SubClassInclusionOwlThing inference);

	}

}
