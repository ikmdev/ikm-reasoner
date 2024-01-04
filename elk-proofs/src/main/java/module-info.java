
open module org.semanticweb.elk.proofs {

	requires com.google.common;

	requires org.slf4j;

	requires org.liveontologies.puli;

	requires transitive org.semanticweb.elk.owl.implementation;
	requires transitive org.semanticweb.elk.owl.model;
	requires transitive org.semanticweb.elk.reasoner;

	requires org.semanticweb.elk.util.collections;
	requires transitive org.semanticweb.elk.util.common;
	requires org.semanticweb.elk.util.hashing;
	requires org.semanticweb.elk.util.io;

	exports org.semanticweb.elk.proofs;
	exports org.semanticweb.elk.owl.inferences;

}
