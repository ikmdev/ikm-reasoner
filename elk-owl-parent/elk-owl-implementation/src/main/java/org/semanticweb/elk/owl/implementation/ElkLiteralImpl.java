
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.visitors.ElkAnnotationValueVisitor;
import org.semanticweb.elk.owl.visitors.ElkLiteralVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkLiteral.
 * 
 * @author Markus Kroetzsch
 */
public class ElkLiteralImpl extends ElkObjectImpl implements ElkLiteral {

	protected final String lexicalForm;
	protected final ElkDatatype datatype;

	ElkLiteralImpl(String lexicalForm, ElkDatatype datatype) {
		this.lexicalForm = lexicalForm;
		this.datatype = datatype;
	}

	@Override
	public String getLexicalForm() {
		return lexicalForm;
	}

	@Override
	public ElkDatatype getDatatype() {
		return datatype;
	}

	@Override
	public <O> O accept(ElkLiteralVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkLiteralVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationValueVisitor<O> visitor) {
		return accept((ElkLiteralVisitor<O>) visitor);
	}

}
