
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * 
 * @author Frantisek Simancik
 *
 */
public interface ElkObject {

	public <O> O accept(ElkObjectVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ElkAnnotation.Factory, ElkAnnotationSubject.Factory,
			ElkAnnotationValue.Factory, ElkAxiom.Factory,
			ElkClassExpression.Factory, ElkDataPropertyExpression.Factory,
			ElkDataRange.Factory, ElkEntity.Factory,
			ElkFacetRestriction.Factory, ElkIndividual.Factory,
			ElkLiteral.Factory, ElkObjectPropertyExpression.Factory,
			ElkSubObjectPropertyExpression.Factory {

		// combined interface

	}

}
