
package org.semanticweb.elk.reasoner.completeness;

import java.util.ArrayList;
import java.util.Collection;



/**
 * The base {@link IncompletenessMonitor} for checking potential
 * incompleteness based on occurrences of {@link Feature}s.
 * 
 * @author Yevgeny Kazakov
 */
public class TopIncompletenessMonitor extends SomeOfIncompletenessMonitor {

	private static final Feature[][] UNSUPPORTED_COMBINATIONS_OF_FEATURES_ = {
			{ Feature.OBJECT_PROPERTY_RANGE,
					//
					Feature.OBJECT_PROPERTY_ASSERTION } };

	private static final Feature[] UNSUPPORTED_FEATURES_ = {
			Feature.ANONYMOUS_INDIVIDUAL,
			//
			Feature.ASYMMETRIC_OBJECT_PROPERTY,
			//
			Feature.BOTTOM_OBJECT_PROPERTY_POSITIVE,
			//
			Feature.DATA_ALL_VALUES_FROM,
			//
			Feature.DATA_EXACT_CARDINALITY,
			//
			Feature.DATA_HAS_VALUE,
			//
			Feature.DATA_MAX_CARDINALITY,
			//
			Feature.DATA_MIN_CARDINALITY,
			//
			Feature.DATA_PROPERTY,
			//
			Feature.DATA_PROPERTY_ASSERTION,
			//
			Feature.DATA_PROPERTY_DOMAIN,
			//
			Feature.DATA_PROPERTY_RANGE,
			//
			Feature.DATA_SOME_VALUES_FROM,
			//
			Feature.DATATYPE,
			//
			Feature.DATATYPE_DEFINITION,
			//
			Feature.DISJOINT_DATA_PROPERTIES,
			//
			Feature.DISJOINT_OBJECT_PROPERTIES,
			//
			Feature.DISJOINT_UNION,
			//
			Feature.EQUIVALENT_DATA_PROPERTIES,
			//
			Feature.FUNCTIONAL_DATA_PROPERTY,
			//
			Feature.FUNCTIONAL_OBJECT_PROPERTY,
			//
			Feature.HAS_KEY,
			//
			Feature.INVERSE_FUNCTIONAL_OBJECT_PROPERTY,
			//
			Feature.INVERSE_OBJECT_PROPERTIES,
			//
			Feature.IRREFLEXIVE_OBJECT_PROPERTY,
			//
			Feature.NEGATIVE_DATA_PROPERTY_ASSERTION,
			//
			Feature.NEGATIVE_OBJECT_PROPERTY_ASSERTION,
			//
			Feature.OBJECT_ALL_VALUES_FROM,
			//
			Feature.OBJECT_COMPLEMENT_OF_NEGATIVE,
			//
			Feature.OBJECT_EXACT_CARDINALITY,
			//
			Feature.OBJECT_HAS_SELF,
			//
			Feature.OBJECT_INVERSE_OF,
			//
			Feature.OBJECT_MAX_CARDINALITY,
			//
			Feature.OBJECT_MIN_CARDINALITY,
			//
			Feature.OBJECT_ONE_OF,
			//
			Feature.OBJECT_UNION_OF_POSITIVE,
			//
			Feature.SUB_DATA_PROPERTY_OF,
			//
			Feature.SWRL_RULE,
			//
			Feature.SYMMETRIC_OBJECT_PROPERTY,
			//
			Feature.TOP_OBJECT_PROPERTY_NEGATIVE };

	public static Collection<IncompletenessMonitor> getMonitors(
			OccurrenceManager occurrences) {
		Collection<IncompletenessMonitor> result = new ArrayList<>();
		for (Feature feature : UNSUPPORTED_FEATURES_) {
			result.add(new IncompletenessDueToUnsupportedFeatures(occurrences,
					feature));
		}
		for (Feature[] combination : UNSUPPORTED_COMBINATIONS_OF_FEATURES_) {
			result.add(new IncompletenessDueToUnsupportedFeatures(occurrences,
					combination));
		}
		return result;
	}

	TopIncompletenessMonitor(OccurrenceManager occurences) {
		super(getMonitors(occurences));
	}

}
