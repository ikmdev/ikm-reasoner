
package org.semanticweb.elk.matching.root;



import org.semanticweb.elk.util.hashing.HashGenerator;
import org.semanticweb.elk.util.hashing.Hasher;

public class IndexedContextRootMatchHash
		implements IndexedContextRootMatch.Visitor<Integer>,
		Hasher<IndexedContextRootMatch> {

	private static final IndexedContextRootMatchHash INSTANCE_ = new IndexedContextRootMatchHash();

	private static int combinedHashCode(int... hashes) {
		return HashGenerator.combineListHash(hashes);
	}

	public static IndexedContextRootMatch.Visitor<Integer> getHashVisitor() {
		return INSTANCE_;
	}

	public static int hashCode(IndexedContextRootMatch match) {
		return match == null ? 0 : match.accept(INSTANCE_);
	}

	private static int hashCode(Object o) {
		return o.hashCode();
	}

	// forbid construction; only static methods should be used
	private IndexedContextRootMatchHash() {

	}

	@Override
	public int hash(IndexedContextRootMatch match) {
		return hashCode(match);
	}

	@Override
	public Integer visit(IndexedContextRootClassExpressionMatch match) {
		return combinedHashCode(
				hashCode(IndexedContextRootClassExpressionMatch.class),
				hashCode(match.getValue()), hashCode(match.getRangeMatches()));
	}

	@Override
	public Integer visit(IndexedContextRootIndividualMatch match) {
		return combinedHashCode(
				hashCode(IndexedContextRootIndividualMatch.class),
				hashCode(match.getValue()), hashCode(match.getRangeMatches()));
	}

}
