
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionDecomposed} from
 * a {@link SubClassInclusionComposed} and
 * {@link IndexedEquivalentClassesAxiom}:<br>
 * 
 * <pre>
 *     (1)      (2)
 *  [C] ⊑ +D  [E = D]
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *      [C] ⊑ -E
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()}<br>
 * D = {@link #getPremiseSubsumer()}<br>
 * E = {@link #getConclusionSubsumer()}<br>
 * 
 * @author Yevgeny Kazakov
 */
public class SubClassInclusionExpandedSecondEquivalentClass
		extends AbstractSubClassInclusionExpansionInference {

	public SubClassInclusionExpandedSecondEquivalentClass(
			IndexedContextRoot inferenceRoot,
			IndexedClassExpression premiseSubsumer,
			IndexedClassExpression conclusionSubsumer, ElkAxiom reason) {
		super(inferenceRoot, premiseSubsumer, conclusionSubsumer, reason);
	}

	public IndexedEquivalentClassesAxiom getSecondPremise(
			IndexedEquivalentClassesAxiom.Factory factory) {
		return factory.getIndexedEquivalentClassesAxiom(getReason(),
				getConclusionSubsumer(), getPremiseSubsumer());
	}

	@Override
	public int getPremiseCount() {
		return 2;
	}

	@Override
	public Conclusion getPremise(int index, Factory factory) {
		switch (index) {
		case 0:
			return getFirstPremise(factory);
		case 1:
			return getSecondPremise(factory);
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

		public O visit(
				SubClassInclusionExpandedSecondEquivalentClass inference);

	}

}
