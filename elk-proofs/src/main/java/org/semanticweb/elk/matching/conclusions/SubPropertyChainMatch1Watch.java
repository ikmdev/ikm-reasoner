
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch3;
import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch4;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch2;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch3;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.PropagationGeneratedMatch2;
import org.semanticweb.elk.matching.inferences.PropertyRangeInheritedMatch2;
import org.semanticweb.elk.matching.inferences.SubPropertyChainExpandedSubObjectPropertyOfMatch2;



public interface SubPropertyChainMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkCompositionMatch3.Visitor<O>,
			BackwardLinkCompositionMatch4.Visitor<O>,
			ForwardLinkCompositionMatch2.Visitor<O>,
			ForwardLinkCompositionMatch3.Visitor<O>,
			PropagationGeneratedMatch2.Visitor<O>,
			PropertyRangeInheritedMatch2.Visitor<O>,
			SubPropertyChainExpandedSubObjectPropertyOfMatch2.Visitor<O> {

		// combined interface

	}

}
