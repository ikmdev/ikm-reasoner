
package org.semanticweb.elk.reasoner.entailments.impl;



import java.util.Iterator;
import java.util.List;

import org.semanticweb.elk.reasoner.entailments.DefaultEntailmentInferenceVisitor;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.HasReason;

public class EntailmentInferencePrinter
		extends DefaultEntailmentInferenceVisitor<String> {

	public static final EntailmentInferencePrinter INSTANCE = new EntailmentInferencePrinter();

	public static String toString(
			final EntailmentInference entailmentInference) {
		return entailmentInference.accept(INSTANCE);
	}

	private EntailmentInferencePrinter() {
		// private default constructor
	}

	@Override
	protected String defaultVisit(
			final EntailmentInference entailmentInference) {
		final StringBuilder result = new StringBuilder(
				entailmentInference.getConclusion().toString());
		result.append(" -| ");
		final List<? extends Entailment> premises = entailmentInference
				.getPremises();
		final Iterator<? extends Entailment> iter = premises.iterator();
		if (iter.hasNext()) {
			result.append(iter.next().toString());
		}
		while (iter.hasNext()) {
			result.append("; ").append(iter.next().toString());
		}
		if (entailmentInference instanceof HasReason) {
			if (!premises.isEmpty()) {
				result.append("; ");
			}
			result.append(((HasReason<?>) entailmentInference).getReason()
					.toString());
		}
		return result.toString();
	}

}
