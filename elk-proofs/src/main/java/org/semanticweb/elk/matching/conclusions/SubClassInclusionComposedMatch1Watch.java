
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.ClassInconsistencyOfObjectComplementOfMatch2;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyOfOwlNothingMatch1;
import org.semanticweb.elk.matching.inferences.DisjointSubsumerFromSubsumerMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.PropagationGeneratedMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedDefinedClassMatch2;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedEmptyObjectIntersectionOfMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedEmptyObjectOneOfMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedEmptyObjectUnionOfMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectHasValueMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectIntersectionOfMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectIntersectionOfMatch2;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectUnionOfMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedSingletonObjectIntersectionOfMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedSingletonObjectOneOfMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedSingletonObjectUnionOfMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedFirstEquivalentClassMatch2;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedSecondEquivalentClassMatch2;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedSubClassOfMatch2;



public interface SubClassInclusionComposedMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends ClassInconsistencyOfObjectComplementOfMatch2.Visitor<O>,
			ClassInconsistencyOfOwlNothingMatch1.Visitor<O>,
			DisjointSubsumerFromSubsumerMatch2.Visitor<O>,
			PropagationGeneratedMatch1.Visitor<O>,
			SubClassInclusionComposedDefinedClassMatch2.Visitor<O>,
			SubClassInclusionComposedEmptyObjectIntersectionOfMatch1.Visitor<O>,
			SubClassInclusionComposedEmptyObjectOneOfMatch1.Visitor<O>,
			SubClassInclusionComposedEmptyObjectUnionOfMatch1.Visitor<O>,
			SubClassInclusionComposedObjectHasValueMatch1.Visitor<O>,
			SubClassInclusionComposedObjectIntersectionOfMatch1.Visitor<O>,
			SubClassInclusionComposedObjectIntersectionOfMatch2.Visitor<O>,
			SubClassInclusionComposedObjectUnionOfMatch1.Visitor<O>,
			SubClassInclusionComposedSingletonObjectIntersectionOfMatch1.Visitor<O>,
			SubClassInclusionComposedSingletonObjectOneOfMatch1.Visitor<O>,
			SubClassInclusionComposedSingletonObjectUnionOfMatch1.Visitor<O>,
			SubClassInclusionExpandedFirstEquivalentClassMatch2.Visitor<O>,
			SubClassInclusionExpandedSecondEquivalentClassMatch2.Visitor<O>,
			SubClassInclusionExpandedSubClassOfMatch2.Visitor<O> {

		// combined interface

	}

}
