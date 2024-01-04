 
package org.semanticweb.elk.owl.inferences;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  ObjectOneOf(a0 ... an) ≡ ObjectUnionOf({a0} ... {an})
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkEquivalentClassesObjectOneOf extends AbstractElkInference {

	public final static String NAME = "ObjectOneOf Translation";

	private final List<? extends ElkIndividual> members_;

	ElkEquivalentClassesObjectOneOf(List<? extends ElkIndividual> members) {
		this.members_ = members;
	}

	public List<? extends ElkIndividual> getMembers() {
		return members_;
	}

	public static List<? extends ElkObjectOneOf> toDisjuncts(
			List<? extends ElkIndividual> members, ElkObject.Factory factory) {
		List<ElkObjectOneOf> result = new ArrayList<ElkObjectOneOf>(
				members.size());
		for (ElkIndividual member : members) {
			result.add(
					factory.getObjectOneOf(Collections.singletonList(member)));
		}
		return result;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return 0;
	}

	@Override
	public ElkSubClassOfAxiom getPremise(int index, ElkObject.Factory factory) {
		return failGetPremise(index);
	}

	@Override
	public ElkEquivalentClassesAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getEquivalentClassesAxiom(
				factory.getObjectOneOf(getMembers()),
				factory.getObjectUnionOf(toDisjuncts(getMembers(), factory)));
	}

	@Override
	public <O> O accept(ElkInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkEquivalentClassesObjectOneOf getElkEquivalentClassesObjectOneOf(
				List<? extends ElkIndividual> members);

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

		O visit(ElkEquivalentClassesObjectOneOf inference);

	}

}
