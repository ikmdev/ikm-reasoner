 
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.printers.OwlFunctionalStylePrinter;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionProcessor;
import org.semanticweb.elk.reasoner.completeness.OccurrenceListener;
import org.semanticweb.elk.reasoner.indexing.conversion.ElkIndexingUnsupportedFeature;
import org.semanticweb.elk.reasoner.indexing.conversion.ElkPolarityExpressionConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Peter Skocovsky
 */
public class ClassQueryIndexingProcessor
		implements ElkClassExpressionProcessor {

	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(ClassQueryIndexingProcessor.class);

	private final ElkPolarityExpressionConverter indexer_;

	private final int increment_; // deletion < 0, addition > 0

	private final OccurrenceListener occurrenceTracker_;

	public ClassQueryIndexingProcessor(
			final ElkPolarityExpressionConverter indexer, final int increment,
			final OccurrenceListener indexingListener) {
		this.indexer_ = indexer;
		this.increment_ = increment;
		this.occurrenceTracker_ = indexingListener;
	}

	@Override
	public void visit(ElkClassExpression elkClassExpression) {
		try {
			if (LOGGER_.isTraceEnabled()) {
				LOGGER_.trace("$$ indexing {} for {}",
						OwlFunctionalStylePrinter.toString(elkClassExpression),
						(increment_ > 0 ? "addition" : "deletion"));
			}
			elkClassExpression.accept(indexer_);
		} catch (final ElkIndexingUnsupportedFeature e) {
			occurrenceTracker_.occurrenceChanged(e.getFeature(), increment_);
		}
	}

}
