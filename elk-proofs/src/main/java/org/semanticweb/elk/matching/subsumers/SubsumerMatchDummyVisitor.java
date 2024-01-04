
package org.semanticweb.elk.matching.subsumers;



public class SubsumerMatchDummyVisitor<O> implements SubsumerMatch.Visitor<O> {

	protected O defaultVisit(IndexedClassEntityMatch match) {
		return defaultVisit((SubsumerElkObjectMatch) match);
	}

	protected O defaultVisit(IndexedObjectSomeValuesFromMatch match) {
		return defaultVisit((SubsumerElkObjectMatch) match);
	}

	protected O defaultVisit(IndexedObjectUnionOfMatch match) {
		return defaultVisit((SubsumerElkObjectMatch) match);
	}

	protected O defaultVisit(SubsumerElkObjectMatch match) {
		return defaultVisit((SubsumerMatch) match);
	}

	protected O defaultVisit(SubsumerMatch subsumerMatch) {
		// can be overridden in sub-classes
		return null;
	}

	protected O defaultVisit(SubsumerNonCanonicalMatch match) {
		return defaultVisit((SubsumerElkObjectMatch) match);
	}

	@Override
	public O visit(IndexedClassMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(IndexedDataHasValueMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(IndexedIndividualMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(IndexedObjectComplementOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(IndexedObjectHasSelfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(IndexedObjectIntersectionOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerEmptyObjectIntersectionOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerEmptyObjectOneOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerEmptyObjectUnionOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerObjectHasValueMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerObjectOneOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerObjectSomeValuesFromMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerObjectUnionOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerSingletonObjectIntersectionOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerSingletonObjectOneOfMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(SubsumerSingletonObjectUnionOfMatch match) {
		return defaultVisit(match);
	}

}
