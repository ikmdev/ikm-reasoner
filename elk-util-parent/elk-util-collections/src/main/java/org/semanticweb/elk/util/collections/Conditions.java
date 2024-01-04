
package org.semanticweb.elk.util.collections;


/**
 * Some utility methods for creating {@link Condition}s.
 * 
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
public class Conditions {

	/**
	 * 
	 * @return a condition that always holds
	 */
	public static <T> Condition<T> trueCondition() {
		return new Condition<T>() {

			@Override
			public boolean holds(T element) {
				return true;
			}};
	}
}
