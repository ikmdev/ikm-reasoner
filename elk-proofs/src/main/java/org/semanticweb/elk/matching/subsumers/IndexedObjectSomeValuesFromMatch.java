
package org.semanticweb.elk.matching.subsumers;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;



public interface IndexedObjectSomeValuesFromMatch
		extends SubsumerElkObjectMatch {

	ElkObjectPropertyExpression getPropertyMatch();

	ElkObject getFillerMatch();
	
	IndexedContextRootMatch getFillerRootMatch(
			IndexedContextRootMatch.Factory factory);

	IndexedContextRootMatch getRangeRootMatch(
			IndexedContextRootMatch.Factory factory);

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O>
			extends SubsumerObjectSomeValuesFromMatch.Visitor<O>,
			SubsumerObjectHasValueMatch.Visitor<O> {

		// combined interface
	}

}
