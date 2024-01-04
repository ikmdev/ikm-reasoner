
package org.semanticweb.elk.owl.predefined;



import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

/**
 * Factory for creating
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Object_Properties">built-in
 * object properties<a> in the OWL 2 specification, such as
 * {@code owl:topObjectProperty} and {@code owl:bottomObjectProperty} .
 * 
 * @author "Yevgeny Kazakov"
 */
public interface PredefinedElkObjectPropertyFactory {

	/**
	 * @return the {@link ElkObjectProperty} corresponding to
	 *         {@code owl:topObjectProperty}
	 */
	ElkObjectProperty getOwlTopObjectProperty();

	/**
	 * @return the {@link ElkObjectProperty} corresponding to
	 *         {@code owl:bottomObjectProperty}
	 */
	ElkObjectProperty getOwlBottomObjectProperty();

}
