
package org.semanticweb.elk.reasoner.saturation.context;


import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;

/**
 * An abstract interfaces for implementing processors of {@link ClassConclusion}s in
 * {@link Context}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ClassConclusionProcessor {

	/**
	 * @param context
	 *            the {@link Context} in which to processed
	 * @param conclusion
	 *            the {@link ClassConclusion} to be processed
	 */
	public void process(Context context, ClassConclusion conclusion);

}
