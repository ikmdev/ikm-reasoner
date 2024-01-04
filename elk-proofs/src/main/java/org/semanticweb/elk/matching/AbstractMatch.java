
package org.semanticweb.elk.matching;



import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectInverseOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;

public class AbstractMatch<P> extends AbstractChild<P> {

	public AbstractMatch(P parent) {
		super(parent);
	}

	// some methods for checking correctness of arguments

	protected static void checkChainMatch(
			final ElkSubObjectPropertyExpression fullChain,
			final int startPos) {
		// verifies that start position exists in full chain
		fullChain.accept(new ElkSubObjectPropertyExpressionVisitor<Void>() {

			void fail() {
				throw new IllegalArgumentException(fullChain + ", " + startPos);
			}

			Void defaultVisit(ElkObjectPropertyExpression expression) {
				if (startPos != 0) {
					fail();
				}
				return null;
			}

			@Override
			public Void visit(ElkObjectPropertyChain expression) {
				if (startPos < 0 || startPos >= expression
						.getObjectPropertyExpressions().size())
					fail();
				return null;
			}

			@Override
			public Void visit(ElkObjectInverseOf expression) {
				return defaultVisit(expression);
			}

			@Override
			public Void visit(ElkObjectProperty expression) {
				return defaultVisit(expression);
			}
		});

	}

	protected static void checkConjunctionMatch(
			ElkObjectIntersectionOf conjunction, int prefixLength) {
		if (prefixLength < 1
				|| prefixLength >= conjunction.getClassExpressions().size()) {
			throw new IllegalArgumentException(
					conjunction + ", " + prefixLength);
		}

	}

}
