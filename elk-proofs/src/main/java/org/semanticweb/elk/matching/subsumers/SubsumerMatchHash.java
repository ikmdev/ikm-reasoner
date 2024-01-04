 
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.util.hashing.HashGenerator;
import org.semanticweb.elk.util.hashing.Hasher;

public class SubsumerMatchHash extends SubsumerMatchDummyVisitor<Integer>
		implements Hasher<SubsumerMatch> {

	private static final SubsumerMatchHash INSTANCE_ = new SubsumerMatchHash();

	// forbid construction; only static methods should be used
	private SubsumerMatchHash() {

	}

	public static int hashCode(SubsumerMatch conclusion) {
		return conclusion == null ? 0 : conclusion.accept(INSTANCE_);
	}

	public static SubsumerMatch.Visitor<Integer> getHashVisitor() {
		return INSTANCE_;
	}

	private static int combinedHashCode(int... hashes) {
		return HashGenerator.combineListHash(hashes);
	}

	private static int hashCode(Class<?> c) {
		return c.hashCode();
	}

	private static int hashCode(ElkObject elkObject) {
		return elkObject.hashCode();
	}

	private static int hashCode(int n) {
		return n;
	}

	@Override
	public int hash(SubsumerMatch object) {
		return hashCode(object);
	}

	@Override
	protected Integer defaultVisit(final SubsumerElkObjectMatch match) {
		return combinedHashCode(hashCode(SubsumerElkObjectMatch.class),
				hashCode(match.getValue()));
	}

	@Override
	public Integer visit(final IndexedObjectIntersectionOfMatch match) {
		return combinedHashCode(
				hashCode(IndexedObjectIntersectionOfMatch.class),
				hashCode(match.getFullValue()),
				hashCode(match.getPrefixLength()));
	}

}
