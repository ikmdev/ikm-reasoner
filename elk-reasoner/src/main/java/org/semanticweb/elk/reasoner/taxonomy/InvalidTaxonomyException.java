
package org.semanticweb.elk.reasoner.taxonomy;


import java.util.Collection;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;


/**
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
public class InvalidTaxonomyException extends RuntimeException {

	private static final long serialVersionUID = -511222809937732780L;
	
	private Collection<ElkAxiom> axioms;
	
	InvalidTaxonomyException(String msg) {
		super(msg);
	}
	
	InvalidTaxonomyException(String msg, Collection<ElkAxiom> axioms) {
		super(msg);
		this.axioms = axioms;
	}
	
	Collection<ElkAxiom> getAxioms() {
		return axioms;
	}

}
