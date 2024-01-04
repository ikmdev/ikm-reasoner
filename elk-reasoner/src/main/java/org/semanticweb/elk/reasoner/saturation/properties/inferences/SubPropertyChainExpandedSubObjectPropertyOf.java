
package org.semanticweb.elk.reasoner.saturation.properties.inferences;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * An {@link ObjectPropertyInference} producing a {@link SubPropertyChain} from
 * a {@link SubPropertyChain} and {@link IndexedSubObjectPropertyOfAxiom}:<br>
 * 
 * <pre>
 *   (1)     (2)
 * [P ⊑ R]  R ⊑ S
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *      P ⊑ S
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * P = {@link #getSubChain()}<br>
 * R = {@link #getInterProperty()}<br>
 * S = {@link #getSuperProperty()}<br>
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 */
public class SubPropertyChainExpandedSubObjectPropertyOf
		extends AbstractSubPropertyChainInference {

	/**
	 * The inferred sub-property of the super-chain for which the inference is
	 * performed by unfolding under told sub-chain of this property
	 */
	private final IndexedObjectProperty interProperty_;

	/**
	 * The {@link ElkAxiom} responsible for the told sub-chain of the premise
	 */
	private final ElkAxiom reason_;

	public SubPropertyChainExpandedSubObjectPropertyOf(
			IndexedPropertyChain firstChain,
			IndexedObjectProperty secondProperty,
			IndexedObjectProperty thirdProperty, ElkAxiom reason) {
		super(firstChain, thirdProperty);
		this.interProperty_ = secondProperty;
		this.reason_ = reason;
	}

	public IndexedObjectProperty getInterProperty() {
		return interProperty_;
	}

	public IndexedObjectProperty getSuperProperty() {
		return (IndexedObjectProperty) getSuperChain();
	}

	public ElkAxiom getReason() {
		return this.reason_;
	}

	public IndexedSubObjectPropertyOfAxiom getFirstPremise(
			IndexedSubObjectPropertyOfAxiom.Factory factory) {
		return factory.getIndexedSubObjectPropertyOfAxiom(reason_,
				getSubChain(), interProperty_);
	}

	public SubPropertyChain getSecondPremise(SubPropertyChain.Factory factory) {
		return factory.getSubPropertyChain(interProperty_, getSuperChain());
	}

	@Override
	public int getPremiseCount() {
		return 2;
	}

	@Override
	public Conclusion getPremise(int index, Factory factory) {
		switch (index) {
		case 0:
			return getFirstPremise(factory);
		case 1:
			return getSecondPremise(factory);
		default:
			return failGetPremise(index);
		}
	}

	@Override
	public final <O> O accept(SubPropertyChainInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O> {

		public O visit(SubPropertyChainExpandedSubObjectPropertyOf inference);

	}

}
