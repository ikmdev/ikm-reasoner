
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.Reference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.SaturationStateWriter;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.context.SubContext;
import org.semanticweb.elk.reasoner.saturation.inferences.ContextInitializationNoPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.SubContextInitializationNoPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link ClassConclusionInsertionVisitor} that initializes the
 * {@link Context} provided in the {@link Reference} and its corresponding
 * {@link SubContext} if the visited {@link ClassConclusion} is the first one
 * inserted there.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ContextInitializingClassConclusionInsertionVisitor
		extends
			ClassConclusionInsertionVisitor {

	/**
	 * The producer for {@link ContextInitialization}s and
	 * {@link SubContextInitialization}s
	 */
	private final ClassInferenceProducer producer_;

	public ContextInitializingClassConclusionInsertionVisitor(
			Reference<Context> contextRef, SaturationStateWriter<?> writer) {
		super(contextRef, writer);
		this.producer_ = writer;
	}

	@Override
	protected Boolean defaultVisit(ClassConclusion conclusion) {
		Context context = get();
		IndexedContextRoot root = context.getRoot();
		if (context.isEmpty()) {
			producer_.produce(new ContextInitializationNoPremises(root));
		}
		if (conclusion instanceof SubClassConclusion) {
			IndexedObjectProperty subRoot = ((SubClassConclusion) conclusion)
					.getSubDestination();
			if (context.isEmpty(subRoot)) {
				producer_.produce(
						new SubContextInitializationNoPremises(root, subRoot));
			}
		}
		return super.defaultVisit(conclusion);
	}
}
