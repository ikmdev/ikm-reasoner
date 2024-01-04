
open module org.semanticweb.elk.reasoner {

	requires com.google.common;

	requires org.slf4j;

	requires org.liveontologies.puli;

	requires org.semanticweb.elk.owl.implementation;
	requires org.semanticweb.elk.owl.model;

	requires org.semanticweb.elk.util.collections;
	requires org.semanticweb.elk.util.common;
	requires org.semanticweb.elk.util.concurrent;
	requires org.semanticweb.elk.util.hashing;
	requires org.semanticweb.elk.util.io;
	requires org.semanticweb.elk.util.logging;

	exports org.semanticweb.elk.loading;
	exports org.semanticweb.elk.reasoner;
	exports org.semanticweb.elk.reasoner.completeness;
	exports org.semanticweb.elk.reasoner.config;
	exports org.semanticweb.elk.reasoner.entailments.model;
	exports org.semanticweb.elk.reasoner.indexing.classes;
	exports org.semanticweb.elk.reasoner.indexing.model;
	exports org.semanticweb.elk.reasoner.query;
	exports org.semanticweb.elk.reasoner.saturation.conclusions.model;
	exports org.semanticweb.elk.reasoner.saturation.inferences;
	exports org.semanticweb.elk.reasoner.saturation.properties.inferences;
	exports org.semanticweb.elk.reasoner.stages;
	exports org.semanticweb.elk.reasoner.taxonomy.model;
	exports org.semanticweb.elk.reasoner.tracing;

}
