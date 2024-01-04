
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkLiteral;

/**
 * Implementation for ElkObjects that maintain a list of literals.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkLiteralListObject extends
		ElkObjectListObject<ElkLiteral> {

	ElkLiteralListObject(List<? extends ElkLiteral> literals) {
		super(literals);
	}

	public List<? extends ElkLiteral> getIndividuals() {
		return getObjects();
	}

}
