
package org.semanticweb.elk.owl.predefined;



import org.semanticweb.elk.owl.interfaces.ElkClass;

/**
 * Factory for creating
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Classes">built-in classes<a> in
 * the OWL 2 specification, such as {@code owl:Thing} and {@code owl:Nothing}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface PredefinedElkClassFactory {

	/**
	 * @return the {@link ElkClass} corresponding to {@code owl:Thing}
	 */
	ElkClass getOwlThing();

	/**
	 * @return the {@link ElkClass} corresponding to {@code owl:Nothing}
	 */
	ElkClass getOwlNothing();

}
