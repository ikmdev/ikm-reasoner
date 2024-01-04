
package org.semanticweb.elk.reasoner.completeness;



import java.util.Collection;

class ObjectPropertyTaxonomyIncompleteness {

	private static final Feature[] UNSUPPORTED_FEATURES_ = {
			// supported features that may cause unsatisfiability of properties
			Feature.OWL_NOTHING_POSITIVE,
			//
			Feature.DISJOINT_CLASSES,
			//
			Feature.OBJECT_COMPLEMENT_OF_POSITIVE };

	private static final Feature[][] UNSUPPORTED_COMBINATIONS_OF_FEATURES_ = {
			// incomplete for property classification
			{ Feature.REFLEXIVE_OBJECT_PROPERTY,
					//
					Feature.OBJECT_PROPERTY_CHAIN } };

	static Collection<IncompletenessMonitor> appendMonitorsTo(
			Collection<IncompletenessMonitor> monitors,
			OccurrenceManager occurrencesInOntology) {
		for (Feature feature : UNSUPPORTED_FEATURES_) {
			monitors.add(new IncompletenessDueToUnsupportedFeatures(
					occurrencesInOntology, feature));
		}
		for (Feature[] combination : UNSUPPORTED_COMBINATIONS_OF_FEATURES_) {
			monitors.add(new IncompletenessDueToUnsupportedFeatures(
					occurrencesInOntology, combination));
		}
		return monitors;
	}

}
