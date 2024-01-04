
package org.semanticweb.elk.reasoner.tracing;

import java.util.AbstractList;
import java.util.List;



/**
 * A skeleton implementation of {@link TracingInference}
 * 
 * @author Yevgeny Kazakov
 *
 */
public abstract class AbstractTracingInference implements TracingInference {

	private final static Conclusion.Factory CONCLUSION_FACTORY_ = ConclusionBaseFactory
			.getInstance();

	private final TracingInference.Visitor<Conclusion> CONCLUSION_GETTER_ = new TracingInferenceConclusionGetter(
			CONCLUSION_FACTORY_);

	public abstract int getPremiseCount();

	public abstract Conclusion getPremise(int index,
			Conclusion.Factory factory);

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */

	protected static <T> T failGetPremise(int index) {
		throw new IndexOutOfBoundsException("No premise with index: " + index);
	}

	protected void checkPremiseIndex(int index) {
		if (index < 0 || index >= getPremiseCount()) {
			failGetPremise(index);
		}
	}

	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public final Conclusion getConclusion() {
		return accept(CONCLUSION_GETTER_);
	}

	@Override
	public final List<? extends Conclusion> getPremises() {
		return new AbstractList<Conclusion>() {

			@Override
			public Conclusion get(int index) {
				return getPremise(index, CONCLUSION_FACTORY_);
			}

			@Override
			public int size() {
				return getPremiseCount();
			}

		};
	}

	@Override
	public String toString() {
		return TracingInferencePrinter.toString(this);
	}

}
