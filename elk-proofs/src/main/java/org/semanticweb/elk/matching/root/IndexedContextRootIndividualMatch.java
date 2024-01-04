
package org.semanticweb.elk.matching.root;

import java.util.Collections;
import java.util.List;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;

public class IndexedContextRootIndividualMatch
		extends AbstractIndexedContextRootMatch<ElkIndividual> {

	private IndexedContextRootIndividualMatch(ElkIndividual value,
			List<? extends ElkClassExpression> rangeMatches) {
		super(value, rangeMatches);
	}

	IndexedContextRootIndividualMatch(ElkIndividual value) {
		super(value);
	}

	@Override
	public ElkClassExpression getMainFillerMatch(ElkObject.Factory factory) {
		return factory.getObjectOneOf(Collections.singletonList(getValue()));
	}

	@Override
	public IndexedContextRootIndividualMatch extend(
			ElkClassExpression rangeMatch) {
		if (rangeMatch instanceof ElkObjectOneOf) {
			List<? extends ElkIndividual> individuals = ((ElkObjectOneOf) rangeMatch)
					.getIndividuals();
			if (individuals.size() == 1
					&& getValue().equals(individuals.get(0))) {
				return this;
			}
		}
		return new IndexedContextRootIndividualMatch(getValue(),
				extendRangeMatches(rangeMatch));
	}

	@Override
	public <O> O accept(IndexedContextRootMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedContextRootIndividualMatch match);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		IndexedContextRootIndividualMatch getIndexedContextRootIndividualMatch(
				ElkIndividual value);

	}

}
