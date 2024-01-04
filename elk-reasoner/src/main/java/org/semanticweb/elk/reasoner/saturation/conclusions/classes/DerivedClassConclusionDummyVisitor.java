
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.DerivedClassConclusionVisitor;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

/**
 * A {@link DerivedClassConclusionVisitor} that stops at the first conclusion
 * and does nothing. Can be used as a prototype for other visitors by overriding
 * the default visit method.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class DerivedClassConclusionDummyVisitor
		implements DerivedClassConclusionVisitor {

	/**
	 * The default visitor method for conclusions explaining a subsumption
	 * 
	 * @param conclusion
	 * @return {@code true} if other conclusions should be visited and
	 *         {@code false} otherwise
	 * 
	 */
	@SuppressWarnings("static-method")
	protected boolean defaultVisit(ClassConclusion conclusion) {
		return false;
	}

	@Override
	public boolean inconsistentOwlThing(ClassInconsistency conclusion)
			throws ElkException {
		return defaultVisit(conclusion);
	}

	@Override
	public boolean inconsistentIndividual(ClassInconsistency conclusion,
			ElkIndividual inconsistent) throws ElkException {
		return defaultVisit(conclusion);
	}

	@Override
	public boolean inconsistentSubClass(ClassInconsistency conclusion)
			throws ElkException {
		return defaultVisit(conclusion);
	}

	@Override
	public boolean derivedClassInclusion(SubClassInclusionComposed conclusion)
			throws ElkException {
		return defaultVisit(conclusion);
	}

}
