
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.exceptions.ElkRuntimeException;



import org.semanticweb.elk.matching.AbstractMatch;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionDelegatingFactory;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;

public abstract class AbstractInferenceMatch<P> extends AbstractMatch<P>
		implements InferenceMatch {

	private static final boolean DEBUG_ = true;

	static final ConclusionMatchExpressionFactory DEBUG_FACTORY = new ConclusionMatchExpressionDelegatingFactory();

	void checkEquals(Object first, Object second) {
		if (DEBUG_ && !first.equals(second)) {
			throw new ElkRuntimeException(
					"Equality assertion failure: " + first + " <> " + second);
		}
	}

	AbstractInferenceMatch(P parent) {
		super(parent);
	}

	@Override
	public String toString() {
		return InferenceMatchPrinter.toString(this);
	}

}
