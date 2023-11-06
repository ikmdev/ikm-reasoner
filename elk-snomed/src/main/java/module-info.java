open module dev.ikm.elk.snomed {

	requires org.slf4j;

	requires org.semanticweb.owlapi;
	requires org.semanticweb.owlapi.impl;
	requires org.semanticweb.owlapi.apibinding;
	requires org.semanticweb.owlapi.parsers;

	requires org.semanticweb.elk.owlapi;
	requires org.semanticweb.elk.reasoner;

	exports dev.ikm.elk.snomed;

}
