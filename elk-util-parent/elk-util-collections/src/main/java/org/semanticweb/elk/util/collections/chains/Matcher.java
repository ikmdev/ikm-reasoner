
package org.semanticweb.elk.util.collections.chains;



/**
 * A class of matching an object of one type against elements of another type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <I>
 *            the input type of elements that should be matched
 * @param <O>
 *            the output type of elements that satisfies the matching conditions
 */
public interface Matcher<I, O> {

	/**
	 * Tests whether the given element satisfies the matching conditions.
	 * 
	 * @param candidate
	 *            the candidate element to test
	 * @return the element which is physically equal (according to {@code ==})
	 *         to the input element, if the input element satisfies the matching
	 *         condition, or {@code null} otherwise.
	 */
	O match(I candidate);

}
