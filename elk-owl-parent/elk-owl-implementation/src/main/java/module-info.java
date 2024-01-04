
open module org.semanticweb.elk.owl.implementation {

//	requires junit;
	
	requires org.slf4j;

	requires org.semanticweb.elk.owl.model;
	requires org.semanticweb.elk.util.common;
	requires org.semanticweb.elk.util.hashing;

	exports org.semanticweb.elk.owl.implementation;
	exports org.semanticweb.elk.owl.managers;
	exports org.semanticweb.elk.owl.parsing;

}
