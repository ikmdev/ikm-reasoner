
package org.semanticweb.elk.owl.iris;



/**
 * An object representing a prefix declaration. It holds both the prefix name
 * and the corresponding full IRI.
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public interface ElkPrefix {

	public String getName();

	public ElkFullIri getIri();
}
