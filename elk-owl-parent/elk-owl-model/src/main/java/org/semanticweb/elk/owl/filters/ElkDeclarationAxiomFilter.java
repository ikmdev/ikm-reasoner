
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;
import org.semanticweb.elk.owl.visitors.ElkDeclarationAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDeclarationAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDeclarationAxiomFilter extends
		ElkDeclarationAxiomVisitor<ElkDeclarationAxiom> {

	// nothing else

}