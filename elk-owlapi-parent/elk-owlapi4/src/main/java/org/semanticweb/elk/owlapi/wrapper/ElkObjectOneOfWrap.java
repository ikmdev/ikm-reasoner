
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectOneOfVisitor;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectOneOf;

/**
 * Implements the {@link ElkObjectOneOf} interface by wrapping instances of
 * {@link OWLObjectOneOf}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectOneOfWrap<T extends OWLObjectOneOf> extends
		ElkClassExpressionWrap<T> implements ElkObjectOneOf {

	ElkObjectOneOfWrap(T owlObjectOneOf) {
		super(owlObjectOneOf);
	}

	@Override
	public List<? extends ElkIndividual> getIndividuals() {
		List<ElkIndividual> result = new ArrayList<ElkIndividual>();
		for (OWLIndividual ce : this.owlObject.getIndividuals()) {
			result.add(converter.convert(ce));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectOneOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectOneOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
