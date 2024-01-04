
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectOneOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Enumeration_of_Individuals">Enumeration of
 * Individuals<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public class ElkObjectOneOfImpl extends ElkObjectListObject<ElkIndividual>
		implements ElkObjectOneOf {

	ElkObjectOneOfImpl(List<? extends ElkIndividual> members) {
		super(members);
	}

	@Override
	public List<? extends ElkIndividual> getIndividuals() {
		return getObjects();
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectOneOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkObjectOneOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectOneOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
