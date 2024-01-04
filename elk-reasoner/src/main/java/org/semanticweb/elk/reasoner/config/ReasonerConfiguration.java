
package org.semanticweb.elk.reasoner.config;

import org.semanticweb.elk.config.BaseConfiguration;
import org.semanticweb.elk.config.ConfigurationFactory;

/**
 * Configuration for the reasoner
 * 
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class ReasonerConfiguration extends BaseConfiguration {

	// the only reason we don't use java.lang.Integer here is because the
	// default value is not a constant
	@Parameter(type = "org.semanticweb.elk.reasoner.config.NumberOfWorkers")
	public static final String NUM_OF_WORKING_THREADS = "elk.reasoner.number_of_workers";

	@Parameter(type = "org.semanticweb.elk.reasoner.config.UnsupportedFeatureTreatment", value = "IGNORE")
	public static final String UNSUPPORTED_FEATURE_TREATMENT = "elk.reasoner.unsupported_feature_treatment";

	@Parameter(type = "java.lang.Boolean", value = "true")
	public static final String INCREMENTAL_MODE_ALLOWED = "elk.reasoner.incremental.allowed";

	@Parameter(type = "org.semanticweb.elk.reasoner.config.EvictorBuilder", value = "RecencyEvictor(16896,0.75)")
	public static final String TRACING_EVICTOR = "elk.reasoner.tracing.evictor";

	@Parameter(type = "org.semanticweb.elk.reasoner.config.EvictorBuilder", value = "RecencyEvictor(512,0.75)")
	public static final String CLASS_EXPRESSION_QUERY_EVICTOR = "elk.reasoner.classexpressionquery.evictor";

	@Parameter(type = "org.semanticweb.elk.reasoner.config.EvictorBuilder", value = "RecencyEvictor(512,0.75)")
	public static final String ENTAILMENT_QUERY_EVICTOR = "elk.reasoner.entailmentquery.evictor";

	public final static String REASONER_CONFIG_PREFIX = "elk.reasoner";

	public static ReasonerConfiguration getConfiguration() {
		return (ReasonerConfiguration) new ConfigurationFactory()
				.getConfiguration(REASONER_CONFIG_PREFIX,
						ReasonerConfiguration.class);
	}
}