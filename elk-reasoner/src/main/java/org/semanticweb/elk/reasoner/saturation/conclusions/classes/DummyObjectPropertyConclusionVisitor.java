
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.PropertyRange;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

/**
 * An {@link ObjectPropertyConclusion.Visitor} that always returns {@code null}
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Yevgeny Kazakov
 * @param <O> 
 */
public class DummyObjectPropertyConclusionVisitor<O>
		implements
			ObjectPropertyConclusion.Visitor<O> {

	/**
	 * The default implementation of all methods
	 * 
	 * @param conclusion
	 * @return
	 */
	protected O defaultVisit(ObjectPropertyConclusion conclusion) {
		return null;
	}

	@Override
	public O visit(PropertyRange conclusion) {
		return defaultVisit(conclusion);
	}

	@Override
	public O visit(SubPropertyChain conclusion) {
		return defaultVisit(conclusion);
	}

}
