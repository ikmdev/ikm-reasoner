 
package org.semanticweb.elk.matching.subsumers;



public class SubsumerMatchEquality extends SubsumerMatchDummyVisitor<Boolean> {

	private final SubsumerMatch other_;

	private SubsumerMatchEquality(SubsumerMatch other) {
		this.other_ = other;
	}

	public static boolean equals(SubsumerMatch first, SubsumerMatch second) {
		return first.accept(new SubsumerMatchEquality(second));
	}

	private static class DefaultVisitor
			extends SubsumerMatchDummyVisitor<Boolean> {

		@Override
		protected Boolean defaultVisit(SubsumerMatch conclusionMatch) {
			return false;
		}

		static boolean equals(Object first, Object second) {
			return first.equals(second);
		}

		static boolean equals(int first, int second) {
			return first == second;
		}
	}

	@Override
	protected Boolean defaultVisit(final SubsumerElkObjectMatch match) {
		return other_.accept(new DefaultVisitor() {
			@Override
			public Boolean defaultVisit(SubsumerElkObjectMatch other) {
				return equals(other.getValue(), match.getValue());
			}
		});
	}

	@Override
	public Boolean visit(final IndexedObjectIntersectionOfMatch match) {
		return other_.accept(new DefaultVisitor() {
			@Override
			public Boolean visit(IndexedObjectIntersectionOfMatch other) {
				return equals(other.getFullValue(), match.getFullValue())
						&& equals(other.getPrefixLength(),
								match.getPrefixLength());
			}
		});
	}

}
