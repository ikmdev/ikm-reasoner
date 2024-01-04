 
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;

public class SubsumerMatches {

	public static SubsumerMatch create(final ElkObject match) {
		return match.accept(SubsumerMatcherVisitor.getInstance());
	}

	public static SubsumerMatch create(final ElkObjectIntersectionOf fullMatch,
			int prefixLength) {
		return new IndexedObjectIntersectionOfMatch(fullMatch, prefixLength);
	}

}
