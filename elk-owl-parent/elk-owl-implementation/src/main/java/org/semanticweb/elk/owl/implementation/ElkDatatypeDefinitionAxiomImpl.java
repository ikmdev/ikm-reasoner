
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkDatatypeDefinitionAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDatatypeDefinitionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Implementation of <@link ElkDatatypeDefinitionAxiom>
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 */
public class ElkDatatypeDefinitionAxiomImpl implements
		ElkDatatypeDefinitionAxiom {

	private final ElkDatatype datatype_;
	private final ElkDataRange dataRange_;

	ElkDatatypeDefinitionAxiomImpl(ElkDatatype datatype, ElkDataRange dataRange) {
		datatype_ = datatype;
		dataRange_ = dataRange;
	}

	@Override
	public ElkDatatype getDatatype() {
		return datatype_;
	}

	@Override
	public ElkDataRange getDataRange() {
		return dataRange_;
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {

		return null;
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
