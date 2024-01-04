
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkDatatypeDefinitionAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDatatypeDefinitionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;

/**
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @param <T>
 * 
 */
public class ElkDatatypeDefinitionAxiomWrap<T extends OWLDatatypeDefinitionAxiom>
		extends ElkAxiomWrap<T> implements ElkDatatypeDefinitionAxiom {

	public ElkDatatypeDefinitionAxiomWrap(T owlAxiom) {
		super(owlAxiom);
	}

	@Override
	public ElkDatatype getDatatype() {
		return converter.convert(this.owlObject.getDatatype());
	}

	@Override
	public ElkDataRange getDataRange() {
		return converter.convert(this.owlObject.getDataRange());
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkDatatypeDefinitionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDatatypeDefinitionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDatatypeDefinitionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}