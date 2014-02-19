/**
 * 
 */
package org.semanticweb.elk.reasoner.saturation.tracing;

import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.Conclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.visitors.ConclusionVisitor;
import org.semanticweb.elk.reasoner.saturation.tracing.inferences.visitors.InferenceVisitor;

/**
 * A generic interfaces for objects which recursively unwind previously stored
 * inferences and let the calling code visit conclusions and inferences.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public interface TraceUnwinder {

	public void accept(IndexedClassExpression root, Conclusion conclusion,
			ConclusionVisitor<IndexedClassExpression, ?> conclusionVisitor,
			InferenceVisitor<IndexedClassExpression, ?> inferenceVisitor);
}
