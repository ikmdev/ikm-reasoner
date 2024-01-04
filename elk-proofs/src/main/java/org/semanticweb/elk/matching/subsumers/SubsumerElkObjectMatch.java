
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObject;

public interface SubsumerElkObjectMatch extends SubsumerMatch {

	ElkObject getValue();

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends IndexedClassEntityMatch.Visitor<O>,
			IndexedDataHasValueMatch.Visitor<O>,
			IndexedObjectComplementOfMatch.Visitor<O>,
			IndexedObjectHasSelfMatch.Visitor<O>,
			IndexedObjectSomeValuesFromMatch.Visitor<O>,
			IndexedObjectUnionOfMatch.Visitor<O>,
			SubsumerNonCanonicalMatch.Visitor<O> {

		// combined interface

	}

}
