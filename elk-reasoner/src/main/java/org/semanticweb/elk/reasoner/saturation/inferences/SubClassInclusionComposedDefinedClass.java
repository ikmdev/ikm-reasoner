
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionComposed} from a
 * {@link SubClassInclusionComposed} and
 * {@link IndexedEquivalentClassesAxiom}:<br>
 * 
 * <pre>
 *     (1)      (2)
 *  [C] ⊑ +D  [A = D]
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *      [C] ⊑ +A
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()}<br>
 * D = {@link #getDefinition()}<br>
 * A = {@link #getConclusionSubsumer()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public class SubClassInclusionComposedDefinedClass
		extends AbstractSubClassInclusionComposedInference<IndexedClass> {

	private final IndexedClassExpression definition_;

	private final ElkAxiom reason_;

	public SubClassInclusionComposedDefinedClass(
			IndexedContextRoot inferenceRoot, IndexedClass defined,
			IndexedClassExpression definition, ElkAxiom reason) {
		super(inferenceRoot, defined);
		this.definition_ = definition;
		this.reason_ = reason;
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getDestination();
	}

	public IndexedClassExpression getDefinition() {
		return this.definition_;
	}

	public ElkAxiom getReason() {
		return this.reason_;
	}

	public SubClassInclusionComposed getFirstPremise(
			SubClassInclusionComposed.Factory factory) {
		return factory.getSubClassInclusionComposed(getOrigin(), definition_);
	}

	public IndexedEquivalentClassesAxiom getSecondPremise(
			IndexedEquivalentClassesAxiom.Factory factory) {
		return factory.getIndexedEquivalentClassesAxiom(reason_, getSubsumer(),
				definition_);
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

		public O visit(SubClassInclusionComposedDefinedClass inference);

	}

}
