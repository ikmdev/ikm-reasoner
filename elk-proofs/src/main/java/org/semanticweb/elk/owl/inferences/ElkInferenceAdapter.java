 
package org.semanticweb.elk.owl.inferences;



import java.util.ArrayList;
import java.util.List;

import org.liveontologies.puli.Inference;
import org.semanticweb.elk.owl.implementation.ElkObjectBaseFactory;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;

public class ElkInferenceAdapter implements Inference<ElkAxiom> {

	private static final ElkObject.Factory CONCLUSION_FACTORY_ = new ElkObjectBaseFactory();

	private final ElkInference elkInference_;

	public ElkInferenceAdapter(ElkInference elkInference) {
		this.elkInference_ = elkInference;
	}

	public ElkInference getElkInference() {
		return elkInference_;
	}

	@Override
	public String getName() {
		return elkInference_.getName();
	}

	@Override
	public ElkAxiom getConclusion() {
		return elkInference_.getConclusion(CONCLUSION_FACTORY_);
	}

	@Override
	public List<? extends ElkAxiom> getPremises() {
		int premiseCount = elkInference_.getPremiseCount();
		List<ElkAxiom> result = new ArrayList<ElkAxiom>(premiseCount);
		for (int i = 0; i < premiseCount; i++) {
			result.add(elkInference_.getPremise(i, CONCLUSION_FACTORY_));
		}
		return result;
	}

}
