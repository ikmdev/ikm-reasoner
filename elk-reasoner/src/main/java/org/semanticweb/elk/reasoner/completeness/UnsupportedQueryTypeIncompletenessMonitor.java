
package org.semanticweb.elk.reasoner.completeness;

import java.util.ArrayList;



import java.util.Collection;

/**
 * An {@link IncompletenessMonitor} which checks for features that correspond to
 * unsupported query types
 * 
 * @author Yevgeny Kazakov
 *
 */
class UnsupportedQueryTypeIncompletenessMonitor
		extends SomeOfIncompletenessMonitor {

	private static final Feature[] UNSUPPORTED_FEATURES_ = {
			//
			Feature.QUERY_ANNOTATION_ASSERTION_AXIOM,
			//
			Feature.QUERY_ANNOTATION_PROPERTY_DOMAIN_AXIOM,
			//
			Feature.QUERY_ANNOTATION_PROPERTY_RANGE_AXIOM,
			//
			Feature.QUERY_SUB_ANNOTATION_PROPERTY_OF_AXIOM,
			//
			Feature.QUERY_DATA_PROPERTY_ASSERTION_AXIOM,
			//
			Feature.QUERY_NEGATIVE_DATA_PROPERTY_ASSERTION_AXIOM,
			//
			Feature.QUERY_NEGATIVE_OBJECT_PROPERTY_ASSERTION_AXIOM,
			//
			Feature.QUERY_DISJOINT_UNION_AXIOM,
			//
			Feature.QUERY_DATA_PROPERTY_DOMAIN_AXIOM,
			//
			Feature.QUERY_DATA_PROPERTY_RANGE_AXIOM,
			//
			Feature.QUERY_DISJOINT_DATA_PROPERTIES_AXIOM,
			//
			Feature.QUERY_EQUIVALENT_DATA_PROPERTIES_AXIOM,
			//
			Feature.QUERY_FUNCTIONAL_DATA_PROPERTY_AXIOM,
			//
			Feature.QUERY_SUB_DATA_PROPERTY_OF_AXIOM,
			//
			Feature.QUERY_DATATYPE_DEFINITION_AXIOM,
			//
			Feature.QUERY_DECLARATION_AXIOM,
			//
			Feature.QUERY_HAS_KEY_AXIOM,
			//
			Feature.QUERY_ASYMMETRIC_OBJECT_PROPERTY_AXIOM,
			//
			Feature.QUERY_DISJOINT_OBJECT_PROPERTIES_AXIOM,
			//
			Feature.QUERY_EQUIVALENT_OBJECT_PROPERTIES_AXIOM,
			//
			Feature.QUERY_FUNCTIONAL_OBJECT_PROPERTY_AXIOM,
			//
			Feature.QUERY_INVERSE_FUNCTIONAL_OBJECT_PROPERTY_AXIOM,
			//
			Feature.QUERY_INVERSE_OBJECT_PROPERTIES_AXIOM,
			//
			Feature.QUERY_IRREFLEXIVE_OBJECT_PROPERTY_AXIOM,
			//
			Feature.QUERY_OBJECT_PROPERTY_RANGE_AXIOM,
			//
			Feature.QUERY_REFLEXIVE_OBJECT_PROPERTY_AXIOM,
			//
			Feature.QUERY_SUB_OBJECT_PROPERTY_OF_AXIOM,
			//
			Feature.QUERY_SYMMETRIC_OBJECT_PROPERTY_AXIOM,
			//
			Feature.QUERY_TRANSITIVE_OBJECT_PROPERTY_AXIOM,
			//
			Feature.QUERY_SWRL_RULE };

	static Collection<IncompletenessMonitor> getMonitors(
			OccurrenceManager occurrencesInQuery) {
		Collection<IncompletenessMonitor> monitors = new ArrayList<>();
		for (Feature feature : UNSUPPORTED_FEATURES_) {
			monitors.add(new IncompletenessDueToUnsupportedFeatures(
					occurrencesInQuery, feature));
		}
		return monitors;
	}

	public UnsupportedQueryTypeIncompletenessMonitor(
			OccurrenceManager occurrencesInQuery) {
		super(getMonitors(occurrencesInQuery));
	}

}
