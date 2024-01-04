
package org.semanticweb.elk.owl.iris;

/**
 * @author Frantisek Simancik
 * 
 */
public interface ElkPrefixDeclarations {
	/**
	 * Prefix declaration. Rejects if a prefix with the same name has already
	 * been declared.
	 * 
	 * @param prefix
	 * @return true is successful
	 */
	public boolean addPrefix(ElkPrefix prefix);

	/**
	 * @param prefixName
	 * @return The ElkPrefix associated with the prefixName or null if none.
	 */
	public ElkPrefix getPrefix(String prefixName);

}
