
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.indexing.model.OntologyIndex;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A simple factory for creating saturation states
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class SaturationStateFactory {

	/**
	 * Creates a new {@link SaturationState}
	 * 
	 * @param ontologyIndex
	 * @return the new state
	 */
	public static SaturationState<? extends Context> createSaturationState(
			OntologyIndex ontologyIndex) {
		return new ReferenceSaturationState(ontologyIndex);
		//return new MapSaturationState<ExtendedContext>(ontologyIndex, new MainContextFactory(), ontologyIndex.getIndexedClassExpressions().size());
	}
}
