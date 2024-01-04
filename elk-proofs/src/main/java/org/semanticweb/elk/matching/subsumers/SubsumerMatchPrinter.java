
package org.semanticweb.elk.matching.subsumers;



public class SubsumerMatchPrinter extends SubsumerMatchDummyVisitor<String> {

	private static SubsumerMatchPrinter INSTANCE_ = new SubsumerMatchPrinter();

	private SubsumerMatchPrinter() {

	}

	public static String toString(SubsumerMatch match) {
		return match.accept(INSTANCE_);
	}

	@Override
	protected String defaultVisit(final SubsumerElkObjectMatch match) {
		return match.getValue().toString();
	}

	@Override
	public String visit(final IndexedObjectIntersectionOfMatch match) {
		return match.getFullValue() + "[-" + match.getPrefixLength() + "]";
	}

}
