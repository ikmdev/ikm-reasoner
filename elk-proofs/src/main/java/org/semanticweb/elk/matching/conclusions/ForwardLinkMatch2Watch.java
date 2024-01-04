
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch5;
import org.semanticweb.elk.matching.inferences.ForwardLinkOfObjectHasSelfMatch2;
import org.semanticweb.elk.matching.inferences.ForwardLinkOfObjectSomeValuesFromMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;



public interface ForwardLinkMatch2Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends ForwardLinkCompositionMatch5.Visitor<O>,
			ForwardLinkOfObjectHasSelfMatch2.Visitor<O>,
			ForwardLinkOfObjectSomeValuesFromMatch2.Visitor<O> {

		// combined interface

	}

}
