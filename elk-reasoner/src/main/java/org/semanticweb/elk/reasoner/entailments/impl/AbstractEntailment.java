 
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.reasoner.entailments.model.Entailment;

abstract class AbstractEntailment implements Entailment {

	@Override
	public int hashCode() {
		return EntailmentHasher.hashCode(this);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		// else
		if (o instanceof Entailment) {
			return hashCode() == o.hashCode()
					&& EntailmentEquality.equals(this, (Entailment) o);
		}
		// else
		return false;
	}

	@Override
	public String toString() {
		return EntailmentPrinter.toString(this);
	}

}
