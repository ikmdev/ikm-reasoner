
package org.semanticweb.elk.reasoner.indexing.conversion;



import org.semanticweb.elk.owl.interfaces.ElkAnnotationAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyDomainAxiom;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyRangeAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubAnnotationPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;

/**
 * An {@link ElkAnnotationAxiomVisitor} that does nothing.
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 */
public class NoOpElkAnnotationAxiomConverter<O> implements
		ElkAnnotationAxiomVisitor<O> {

	@Override
	public final O visit(ElkAnnotationAssertionAxiom axiom) {
		return null;
	}

	@Override
	public final O visit(ElkAnnotationPropertyDomainAxiom axiom) {
		return null;
	}

	@Override
	public final O visit(ElkAnnotationPropertyRangeAxiom axiom) {
		return null;
	}

	@Override
	public final O visit(ElkSubAnnotationPropertyOfAxiom axiom) {
		return null;
	}

}
