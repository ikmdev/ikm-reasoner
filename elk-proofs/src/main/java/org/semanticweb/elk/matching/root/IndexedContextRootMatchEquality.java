
package org.semanticweb.elk.matching.root;



public class IndexedContextRootMatchEquality
		implements IndexedContextRootMatch.Visitor<Boolean> {

	private static class DefaultVisitor
			extends IndexedContextRootMatchDummyVisitor<Boolean> {

		@Override
		protected Boolean defaultVisit(IndexedContextRootMatch match) {
			return false;
		}

		boolean equals(Object first, Object second) {
			return first.equals(second);
		}

	}

	private final IndexedContextRootMatch other_;

	public IndexedContextRootMatchEquality(IndexedContextRootMatch other) {
		this.other_ = other;
	}

	@Override
	public Boolean visit(final IndexedContextRootClassExpressionMatch match) {
		return other_.accept(new DefaultVisitor() {
			@Override
			public Boolean visit(IndexedContextRootClassExpressionMatch other) {
				return equals(other.getValue(), match.getValue()) && equals(
						other.getRangeMatches(), match.getRangeMatches());
			}
		});
	}

	@Override
	public Boolean visit(final IndexedContextRootIndividualMatch match) {
		return other_.accept(new DefaultVisitor() {
			@Override
			public Boolean visit(IndexedContextRootIndividualMatch other) {
				return equals(other.getValue(), match.getValue()) && equals(
						other.getRangeMatches(), match.getRangeMatches());
			}
		});
	}

}
