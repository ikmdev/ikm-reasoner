
package org.semanticweb.elk.owl.predefined;



import org.semanticweb.elk.owl.interfaces.ElkDataProperty;

/**
 * Factory for creating
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Data_Properties">built-in data
 * properties<a> in the OWL 2 specification, such as {@code owl:topDataProperty}
 * and {@code owl:bottomDataProperty} .
 * 
 * @author "Yevgeny Kazakov"
 */
public interface PredefinedElkDataPropertyFactory {

	/**
	 * @return the {@link ElkDataProperty} corresponding to
	 *         {@code owl:topDataProperty}
	 */
	ElkDataProperty getOwlTopDataProperty();

	/**
	 * @return the {@link ElkDataProperty} corresponding to
	 *         {@code owl:bottomDataProperty}
	 */
	ElkDataProperty getOwlBottomDataProperty();

}
