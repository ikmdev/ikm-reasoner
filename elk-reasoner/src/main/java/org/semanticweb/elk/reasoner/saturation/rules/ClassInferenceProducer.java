
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;

/**
 * An object using which {@link ClassInference}s can be produced
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ClassInferenceProducer {

	/**
	 * Reports a new {@link ClassInference}.
	 * 
	 * @param inference
	 */
	public void produce(ClassInference inference);

}
