
package org.semanticweb.elk.owl.inferences;

import java.util.AbstractList;
import java.util.List;

import org.semanticweb.elk.owl.implementation.ElkObjectBaseFactory;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;



public abstract class AbstractElkInference implements ElkInference {

	private final static ElkObject.Factory ELK_FACTORY_ = new ElkObjectBaseFactory();

	/**
	 * hash code, computed on demand
	 */
	private int hashCode_ = 0;

	static <T> T failGetPremise(int index) {
		throw new IndexOutOfBoundsException("No premise with index: " + index);
	}

	void checkPremiseIndex(int index) {
		if (index < 0 || index >= getPremiseCount()) {
			failGetPremise(index);
		}
	}

	@Override
	public ElkAxiom getConclusion() {
		return getConclusion(ELK_FACTORY_);
	}

	@Override
	public List<? extends ElkAxiom> getPremises() {
		return new AbstractList<ElkAxiom>() {

			@Override
			public ElkAxiom get(int index) {
				return getPremise(index, ELK_FACTORY_);
			}

			@Override
			public int size() {
				return getPremiseCount();
			}

		};
	}

	@Override
	public int hashCode() {
		if (hashCode_ == 0) {
			hashCode_ = accept(ElkInferenceHash.getHashVisitor());
		}
		// else
		return hashCode_;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		// else
		if (o instanceof ElkInference) {
			return hashCode() == o.hashCode()
					&& ElkInferenceEquality.equals(this, (ElkInference) o);
		}
		// else
		return false;
	}

	@Override
	public String toString() {
		return ElkInferencePrinter.toString(this);
	}

}
