
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;

abstract class AbstractEntailmentInference implements EntailmentInference {

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public int hashCode() {
		return EntailmentInferenceHasher.hashCode(this);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		// else
		if (o instanceof EntailmentInference) {
			return hashCode() == o.hashCode() && EntailmentInferenceEquality
					.equals(this, (EntailmentInference) o);
		}
		// else
		return false;
	}

	@Override
	public String toString() {
		return EntailmentInferencePrinter.toString(this);
	}

}
