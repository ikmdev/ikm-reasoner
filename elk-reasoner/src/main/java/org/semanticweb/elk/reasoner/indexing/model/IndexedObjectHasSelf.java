 
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.reasoner.saturation.inferences.BackwardLinkOfObjectHasSelf;
import org.semanticweb.elk.reasoner.saturation.inferences.ForwardLinkOfObjectHasSelf;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * An {@link IndexedClassExpression} constructed from an
 * {@link IndexedObjectProperty}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * ∃R.Self
 * </pre>
 * 
 * It is logically equivalent to OWL class expression {@code ObjectHasSelf(R)}
 * <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * R = {@link #getProperty()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedObjectHasSelf extends IndexedClassExpression {

	/**
	 * @return The representation of the {@link ElkObjectProperty} that is a
	 *         property of the {@link ElkObjectHasSelf} represented by this
	 *         {@link IndexedObjectHasSelf}.
	 * 
	 * @see ElkObjectHasSelf#getProperty()
	 */
	IndexedObjectProperty getProperty();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedObjectHasSelf element);

	}

	class Helper {

		public static void produceDecomposedExistentialLink(
				ClassInferenceProducer producer, IndexedContextRoot root,
				IndexedObjectHasSelf subsumer) {
			producer.produce(
					new BackwardLinkOfObjectHasSelf(root, subsumer));
			if (!subsumer.getProperty().getSaturated()
					.getNonRedundantCompositionsByLeftSubProperty().isEmpty()) {
				producer.produce(
						new ForwardLinkOfObjectHasSelf(root, subsumer));
			}
		}

	}

}
