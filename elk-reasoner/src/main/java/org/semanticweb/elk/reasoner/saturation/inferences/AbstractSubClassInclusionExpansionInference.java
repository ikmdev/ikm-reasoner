
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionDecomposed} from
 * a {@link SubClassInclusionComposed} and other premises:<br>
 * 
 * <pre>
 *     (1)    
 *  [C] ⊑ +D  ...
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
 *
 */
public abstract class AbstractSubClassInclusionExpansionInference
		extends AbstractSubClassInclusionDecomposedInference {

	private final IndexedClassExpression premiseSubsumer_;

	private final ElkAxiom reason_;
	
	public AbstractSubClassInclusionExpansionInference(
			IndexedContextRoot inferenceRoot,
			IndexedClassExpression premiseSubsumer,
			IndexedClassExpression conclusionSubsumer, ElkAxiom reason) {
		super(inferenceRoot, conclusionSubsumer);
		this.premiseSubsumer_ = premiseSubsumer;
		this.reason_ = reason;
	}
	
	public final IndexedClassExpression getPremiseSubsumer() {
		return this.premiseSubsumer_;
	}

	public final ElkAxiom getReason() {
		return reason_;
	}

	@Override
	public final IndexedContextRoot getOrigin() {
		return getDestination();
	}

	public final SubClassInclusionComposed getFirstPremise(
			SubClassInclusionComposed.Factory factory) {
		return factory.getSubClassInclusionComposed(getOrigin(),
				premiseSubsumer_);
	}


}
