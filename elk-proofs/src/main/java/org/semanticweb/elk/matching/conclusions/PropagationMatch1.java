
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.subsumers.IndexedObjectSomeValuesFromMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;

public class PropagationMatch1
		extends AbstractClassConclusionMatch<Propagation> {

	private final IndexedContextRootMatch destinationMatch_;

	private final ElkObjectProperty subDestinationMatch_;

	private final IndexedObjectSomeValuesFromMatch carryMatch_;

	PropagationMatch1(Propagation parent,
			IndexedContextRootMatch destinationMatch,
			ElkObjectProperty subDestinationMatch,
			IndexedObjectSomeValuesFromMatch carryMatch) {
		super(parent);
		this.destinationMatch_ = destinationMatch;
		this.subDestinationMatch_ = subDestinationMatch;
		this.carryMatch_ = carryMatch;
	}

	public IndexedContextRootMatch getDestinationMatch() {
		return destinationMatch_;
	}

	public ElkObjectProperty getSubDestinationMatch() {
		return subDestinationMatch_;
	}

	public IndexedObjectSomeValuesFromMatch getCarryMatch() {
		return carryMatch_;
	}

	@Override
	public <O> O accept(ClassConclusionMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		PropagationMatch1 getPropagationMatch1(Propagation parent,
				IndexedContextRootMatch destinationMatch,
				ElkObjectProperty subDestinationMatch,
				IndexedObjectSomeValuesFromMatch carryMatch);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(PropagationMatch1 conclusionMatch);

	}

}
