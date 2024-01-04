
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch1;
import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyOfDisjointSubsumers;

public class ClassInconsistencyOfDisjointSubsumersMatch1
		extends AbstractInferenceMatch<ClassInconsistencyOfDisjointSubsumers>
		implements DisjointSubsumerMatch1Watch {

	private final IndexedContextRootMatch originMatch_;

	private ClassInconsistencyOfDisjointSubsumersMatch1(
			ClassInconsistencyOfDisjointSubsumers parent,
			IndexedContextRootMatch originMatch) {
		super(parent);
		this.originMatch_ = originMatch;
	}

	ClassInconsistencyOfDisjointSubsumersMatch1(
			ClassInconsistencyOfDisjointSubsumers parent,
			ClassInconsistencyMatch1 conclusionMatch) {
		this(parent, conclusionMatch.getDestinationMatch());
	}

	IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	ClassInconsistencyMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getClassInconsistencyMatch1(
				getParent().getConclusion(factory), getOriginMatch());
	}

	public DisjointSubsumerMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getDisjointSubsumerMatch1(
				getParent().getFirstPremise(factory), getOriginMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(DisjointSubsumerMatch1Watch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> {

		O visit(ClassInconsistencyOfDisjointSubsumersMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ClassInconsistencyOfDisjointSubsumersMatch1 getClassInconsistencyOfDisjointSubsumersMatch1(
				ClassInconsistencyOfDisjointSubsumers parent,
				ClassInconsistencyMatch1 conclusionMatch);

	}

}
