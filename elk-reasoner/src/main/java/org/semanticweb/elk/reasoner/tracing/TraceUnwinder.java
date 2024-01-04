
package org.semanticweb.elk.reasoner.tracing;



/**
 * A generic interface for objects which recursively unwind previously stored
 * inferences and let the calling code visit inferences.
 * 
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <O>
 *                the type of the inference visitor output
 */
public interface TraceUnwinder<O> {

	public void accept(Conclusion conclusion,
			TracingInference.Visitor<O> inferenceVisitor);

}
