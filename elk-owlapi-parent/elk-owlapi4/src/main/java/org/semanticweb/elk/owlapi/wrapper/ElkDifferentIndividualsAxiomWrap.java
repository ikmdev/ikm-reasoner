
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDifferentIndividualsAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;

/**
 * Implements the {@link ElkDifferentIndividualsAxiom} interface by wrapping
 * instances of {@link OWLDifferentIndividualsAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDifferentIndividualsAxiomWrap<T extends OWLDifferentIndividualsAxiom>
		extends ElkAssertionAxiomWrap<T> implements
		ElkDifferentIndividualsAxiom {

	public ElkDifferentIndividualsAxiomWrap(T owlDifferentIndividualsAxiom) {
		super(owlDifferentIndividualsAxiom);
	}

	@Override
	public List<? extends ElkIndividual> getIndividuals() {
		List<ElkIndividual> result = new ArrayList<ElkIndividual>();
		for (OWLIndividual ind : this.owlObject.getIndividuals()) {
			result.add(converter.convert(ind));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return accept((ElkDifferentIndividualsAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDifferentIndividualsAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
