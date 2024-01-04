
package org.semanticweb.elk.owl.inferences;



import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.managers.ElkObjectEntityRecyclingFactory;

public class ElkInferencePrinter extends ElkInferenceDummyVisitor<String> {

	private static ElkInferencePrinter INSTANCE_ = new ElkInferencePrinter();

	static ElkInference.Visitor<String> getVisitor() {
		return INSTANCE_;
	}

	public static String toString(ElkInference conclusion) {
		return conclusion.accept(INSTANCE_);
	}

	ElkObject.Factory factory_ = new ElkObjectEntityRecyclingFactory();

	private ElkInferencePrinter() {

	}

	@Override
	protected String defaultVisit(ElkInference inference) {
		String result = inference.getConclusion(factory_) + " -| ";
		int premiseCount = inference.getPremiseCount();
		for (int i = 0; i < premiseCount; i++) {
			result += inference.getPremise(i, factory_);
			if (i < premiseCount - 1) {
				result += "; ";
			}
		}
		return result;
	}

}
