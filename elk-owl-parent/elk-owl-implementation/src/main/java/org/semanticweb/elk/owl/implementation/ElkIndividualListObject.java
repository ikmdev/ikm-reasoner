
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;

/**
 * Implementation for ElkObjects that maintain a list of individuals.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkIndividualListObject extends
		ElkObjectListObject<ElkIndividual> {

	ElkIndividualListObject(List<? extends ElkIndividual> individuals) {
		super(individuals);
	}

	public List<? extends ElkIndividual> getIndividuals() {
		return getObjects();
	}

}
