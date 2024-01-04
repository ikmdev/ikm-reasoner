
package org.semanticweb.elk.proofs;

import java.util.List;

import org.liveontologies.puli.Delegator;
import org.liveontologies.puli.Inference;
import org.semanticweb.elk.reasoner.tracing.TracingInference;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;

class TracingInferenceWrap extends Delegator<TracingInference>
		implements Inference<Object> {

	public TracingInferenceWrap(final TracingInference inference) {
		super(inference);
	}

	@Override
	public String getName() {
		return getDelegate().getName();
	}

	@Override
	public Object getConclusion() {
		return getDelegate().getConclusion();
	}

	@Override
	public List<? extends Object> getPremises() {
		return Lists.transform(getDelegate().getPremises(),
				Functions.identity());
	}

}
