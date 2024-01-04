
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;

/**
 * A {@link SubClassConclusion.Visitor} that always returns {@code null}s. Can
 * be used as prototype to implement other visitors by overriding the
 * corresponding (default) methods.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public class DummySubClassConclusionVisitor<O>
		implements
			SubClassConclusion.Visitor<O> {

	/**
	 * The default implementation of all methods
	 * 
	 * @param subConclusion
	 * @return
	 */
	protected O defaultVisit(SubClassConclusion subConclusion) {
		return null;
	}

	@Override
	public O visit(BackwardLink subConclusion) {
		return defaultVisit(subConclusion);
	}

	@Override
	public O visit(Propagation subConclusion) {
		return defaultVisit(subConclusion);
	}

	@Override
	public O visit(SubContextInitialization subConclusion) {
		return defaultVisit(subConclusion);
	}

}
