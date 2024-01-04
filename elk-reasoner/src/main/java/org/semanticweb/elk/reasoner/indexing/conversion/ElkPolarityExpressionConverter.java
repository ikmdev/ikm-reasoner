
package org.semanticweb.elk.reasoner.indexing.conversion;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.predefined.ElkPolarity;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkIndividualVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyExpressionVisitor;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedIndividual;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;

/**
 * Converts {@link ElkObject}s that can occur with different polarities in the
 * ontology to the corresponding {@link ModifiableIndexedObject}s. Such objects
 * can be either {@link ElkClassExpression}s, {@link ElkIndividual}s, and
 * {@link ElkObjectPropertyExpression}s. Each
 * {@link ElkPolarityExpressionConverter} is associated with one type of
 * polarity. The converter for the complementary polarity type can be also
 * returned using
 * {@link ElkPolarityExpressionConverter#getComplementaryConverter()}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @see ElkPolarity
 *
 */
public interface ElkPolarityExpressionConverter extends
		ElkClassExpressionVisitor<ModifiableIndexedClassExpression>,
		ElkIndividualVisitor<ModifiableIndexedIndividual>,
		ElkObjectPropertyExpressionVisitor<ModifiableIndexedObjectProperty> {

	/**
	 * @return the polarity type of this {@link ElkPolarityExpressionConverter},
	 *         i.e., the type of occurrences for which this converter should be
	 *         used
	 */
	ElkPolarity getPolarity();

	/**
	 * @return the corresponding converter for the complementary polarity.
	 * 
	 * @see ElkPolarity#getComplementary()
	 */
	ElkPolarityExpressionConverter getComplementaryConverter();

}
