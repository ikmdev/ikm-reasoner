
package org.semanticweb.elk.reasoner.saturation.rules.disjointsubsumer;



import java.util.Set;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpressionList;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.DisjointSubsumer;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyOfDisjointSubsumers;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link DisjointSubsumerRule} applied when processing a
 * {@link DisjointSubsumer} producing {@link ClassInconsistency} caused by violation
 * of disjointness constrains of this {@link DisjointSubsumer}
 * 
 * @author "Yevgeny Kazakov"
 */
public class ContradictionCompositionRule extends AbstractDisjointSubsumerRule {

	public static final String NAME = "Contradiction by Disjointness Axiom";

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(DisjointSubsumer premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		IndexedClassExpressionList disjoint = premise.getDisjointExpressions();		
		Set<? extends Integer> disjointSubsumerPositions = premises
				.getSubsumerPositions(disjoint); // should not be null

		if (disjointSubsumerPositions.size() > 1) {
			// at least two disjoint members were derived
			int lastPos = premise.getPosition();
			for (int otherPos : disjointSubsumerPositions) {
				if (otherPos != lastPos) {
					producer.produce(new ClassInconsistencyOfDisjointSubsumers(
							premise, otherPos));
				}
			}
			
		}
	}

	@Override
	public boolean isTracingRule() {
		return true;
	}

	@Override
	public void accept(DisjointSubsumerRuleVisitor<?> visitor,
			DisjointSubsumer premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}