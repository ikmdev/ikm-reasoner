
package org.semanticweb.elk.reasoner.saturation;

import org.semanticweb.elk.reasoner.ReasonerJob;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.context.Context;

public class SaturationJob<I extends IndexedContextRoot> extends
		ReasonerJob<I, Context> {

	public SaturationJob(I input) {
		super(input);
	}

	@Override
	protected void setOutput(Context output) {
		super.setOutput(output);
	}

}
