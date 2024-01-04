
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;



/**
 * A {@link SubClassInclusionInference} that produces
 * {@link SubClassInclusionComposed} conclusions
 * 
 * @author Yevgeny Kazakov
 */
public interface SubClassInclusionComposedInference
		extends SubClassInclusionInference {

	public <O> O accept(Visitor<O> visitor);

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O>
			extends SubClassInclusionComposedDefinedClass.Visitor<O>,
			SubClassInclusionComposedEntity.Visitor<O>,
			SubClassInclusionComposedObjectIntersectionOf.Visitor<O>,
			SubClassInclusionComposedObjectSomeValuesFrom.Visitor<O>,
			SubClassInclusionComposedObjectUnionOf.Visitor<O>,
			SubClassInclusionOwlThing.Visitor<O> {

		// combined interface

	}

}
