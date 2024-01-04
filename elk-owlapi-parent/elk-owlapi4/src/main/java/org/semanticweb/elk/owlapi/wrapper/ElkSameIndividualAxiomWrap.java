
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkSameIndividualAxiomVisitor;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;

/**
 * Implements the {@link ElkSameIndividualAxiom} interface by wrapping instances
 * of {@link OWLSameIndividualAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkSameIndividualAxiomWrap<T extends OWLSameIndividualAxiom>
		extends ElkAssertionAxiomWrap<T> implements ElkSameIndividualAxiom {

	public ElkSameIndividualAxiomWrap(T owlSameIndividualAxiom) {
		super(owlSameIndividualAxiom);
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
		return accept((ElkSameIndividualAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSameIndividualAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
