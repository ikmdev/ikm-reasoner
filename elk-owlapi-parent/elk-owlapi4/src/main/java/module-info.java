
open module org.semanticweb.elk.owlapi {

//	requires junit;
	
	requires com.google.common;

	requires org.slf4j;

	requires org.liveontologies.puli;

	requires org.semanticweb.owlapi;
	requires org.semanticweb.owlapi.impl;
	requires org.semanticweb.owlapi.apibinding;

	requires org.semanticweb.elk.owl.model;
	requires org.semanticweb.elk.proofs;
	requires org.semanticweb.elk.util.common;
	requires org.semanticweb.elk.util.concurrent;
	requires org.semanticweb.elk.util.io;
	requires org.semanticweb.elk.util.logging;

	exports org.semanticweb.elk.owlapi;
	exports org.semanticweb.elk.owlapi.proofs;

}
