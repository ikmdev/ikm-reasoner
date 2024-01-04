
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkAnonymousIndividual;
import org.semanticweb.elk.owl.visitors.ElkAnnotationSubjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnnotationValueVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnonymousIndividualVisitor;
import org.semanticweb.elk.owl.visitors.ElkIndividualVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Anonymous_Individuals">Anonymous
 * Individuals<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public class ElkAnonymousIndividualImpl extends ElkObjectImpl implements
		ElkAnonymousIndividual {

	private final String nodeId_;

	ElkAnonymousIndividualImpl(String nodeId) {
		this.nodeId_ = nodeId;
	}

	@Override
	public String getNodeId() {
		return nodeId_;
	}

	@Override
	public String toString() {
		return nodeId_;
	}

	@Override
	public <O> O accept(ElkIndividualVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return visitor.visit(this);
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
	public <O> O accept(ElkAnonymousIndividualVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
