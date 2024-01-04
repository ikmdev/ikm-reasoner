
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnonymousIndividual;
import org.semanticweb.elk.owl.visitors.ElkAnnotationSubjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnnotationValueVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnonymousIndividualVisitor;
import org.semanticweb.elk.owl.visitors.ElkIndividualVisitor;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;

/**
 * Implements the {@link ElkAnonymousIndividual} interface by wrapping instances
 * of {@link OWLAnonymousIndividual}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkAnonymousIndividualWrap<T extends OWLAnonymousIndividual>
		extends ElkIndividualWrap<T> implements ElkAnonymousIndividual {

	ElkAnonymousIndividualWrap(T owlAnonymousIndividual) {
		super(owlAnonymousIndividual);
	}

	@Override
	public String getNodeId() {
		return this.owlObject.getID().toString();
	}

	@Override
	public <O> O accept(ElkAnnotationValueVisitor<O> visitor) {
		return accept((ElkAnonymousIndividualVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationSubjectVisitor<O> visitor) {
		return accept((ElkAnonymousIndividualVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkIndividualVisitor<O> visitor) {
		return accept((ElkAnonymousIndividualVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnonymousIndividualVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
