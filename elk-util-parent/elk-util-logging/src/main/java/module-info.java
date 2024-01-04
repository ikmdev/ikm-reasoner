
open module org.semanticweb.elk.util.logging {

	requires java.management;

	requires org.slf4j;

	requires org.semanticweb.elk.util.hashing;

	exports org.semanticweb.elk.util.logging;
	exports org.semanticweb.elk.util.logging.statistics;

}
