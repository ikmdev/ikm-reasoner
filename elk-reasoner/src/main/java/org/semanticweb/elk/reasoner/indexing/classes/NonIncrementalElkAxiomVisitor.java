
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.reasoner.incremental.AxiomLoadingListener;
import org.semanticweb.elk.reasoner.indexing.conversion.ElkAxiomConverter;

/**
 * A delegating visitor which notifies the provided
 * {@link AxiomLoadingListener} that some axiom represents a change
 * which cannot be processed incrementally by the reasoner
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class NonIncrementalElkAxiomVisitor extends DelegatingElkAxiomVisitor<Void>
		implements ElkAxiomConverter {

	private final AxiomLoadingListener<ElkAxiom> listener_;

	public NonIncrementalElkAxiomVisitor(ElkAxiomConverter visitor,
			AxiomLoadingListener<ElkAxiom> listener) {
		super(visitor);
		listener_ = listener;
	}

	@Override
	public Void visit(ElkEquivalentObjectPropertiesAxiom axiom) {
		listener_.notify(axiom);
		return super.visit(axiom);
	}

	@Override
	public Void visit(ElkReflexiveObjectPropertyAxiom axiom) {
		listener_.notify(axiom);
		return super.visit(axiom);
	}

	@Override
	public Void visit(ElkSubObjectPropertyOfAxiom axiom) {
		listener_.notify(axiom);
		return super.visit(axiom);
	}

	@Override
	public Void visit(ElkTransitiveObjectPropertyAxiom axiom) {
		listener_.notify(axiom);
		return super.visit(axiom);
	}

	@Override
	public Void visit(ElkObjectPropertyRangeAxiom axiom) {
		listener_.notify(axiom);
		return super.visit(axiom);
	}

}
