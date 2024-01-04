
package org.semanticweb.elk.util.collections.chains;

public class SimpleTypeBasedMatcher<T, O> implements Matcher<T, O> {

	private final Class<O> class_;

	public SimpleTypeBasedMatcher(Class<O> clazz) {
		class_ = clazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O match(T candidate) {
		return candidate.getClass() == class_ ? (O) candidate : null;
	}
}
