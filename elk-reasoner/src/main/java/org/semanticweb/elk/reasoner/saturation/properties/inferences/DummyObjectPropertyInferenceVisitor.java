
package org.semanticweb.elk.reasoner.saturation.properties.inferences;



/**
 * An {@link ObjectPropertyInference.Visitor} that always returns {@code null}.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <O>
 *            the type of the output
 */
public class DummyObjectPropertyInferenceVisitor<O>
		implements
			ObjectPropertyInference.Visitor<O> {

	/**
	 * The default implementation of all methods
	 * 
	 * @param inference
	 * @return
	 */
	protected O defaultTracedVisit(ObjectPropertyInference inference) {
		return null;
	}

	@Override
	public O visit(PropertyRangeInherited inference) {
		return defaultTracedVisit(inference);
	}

	@Override
	public O visit(SubPropertyChainExpandedSubObjectPropertyOf inference) {
		return defaultTracedVisit(inference);

	}

	@Override
	public O visit(SubPropertyChainTautology inference) {
		return defaultTracedVisit(inference);
	}

}
