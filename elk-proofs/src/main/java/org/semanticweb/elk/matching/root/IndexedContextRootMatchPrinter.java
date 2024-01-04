
package org.semanticweb.elk.matching.root;



import org.semanticweb.elk.owl.implementation.ElkObjectBaseFactory;
import org.semanticweb.elk.owl.interfaces.ElkObject;

public class IndexedContextRootMatchPrinter
		implements IndexedContextRootMatch.Visitor<String> {

	private static IndexedContextRootMatchPrinter INSTANCE_ = new IndexedContextRootMatchPrinter();

	static IndexedContextRootMatch.Visitor<String> getPrinterVisitor() {
		return INSTANCE_;
	}

	public static String toString(IndexedContextRootMatch match) {
		return match.accept(INSTANCE_);
	}

	private final ElkObject.Factory factory_ = new ElkObjectBaseFactory();

	private IndexedContextRootMatchPrinter() {

	}

	@Override
	public String visit(IndexedContextRootClassExpressionMatch match) {
		return match.toElkExpression(factory_).toString();
	}

	@Override
	public String visit(IndexedContextRootIndividualMatch match) {
		return match.toElkExpression(factory_).toString();
	}

}
