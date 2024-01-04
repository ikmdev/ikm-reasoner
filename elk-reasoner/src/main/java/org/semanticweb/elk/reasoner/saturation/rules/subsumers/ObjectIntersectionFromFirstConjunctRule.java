
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.util.collections.chains.Chain;
import org.semanticweb.elk.util.collections.chains.Matcher;
import org.semanticweb.elk.util.collections.chains.ReferenceFactory;
import org.semanticweb.elk.util.collections.chains.SimpleTypeBasedMatcher;

/**
 * A {@link ChainableSubsumerRule} that produces {@link SubClassInclusion} for an
 * {@link IndexedObjectIntersectionOf} when processing its second conjunct
 * {@link IndexedClassExpression} and when the first conjunct is contained in
 * the {@link Context}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @see ObjectIntersectionFromSecondConjunctRule
 */
public class ObjectIntersectionFromFirstConjunctRule extends
		AbstractObjectIntersectionFromConjunctRule {

	public static final String NAME = "ObjectIntersectionOf From 1st Conjunct";

	ObjectIntersectionFromFirstConjunctRule(ChainableSubsumerRule tail) {
		super(tail);
	}

	ObjectIntersectionFromFirstConjunctRule(IndexedClassExpression conjunct,
			IndexedObjectIntersectionOf conjunction) {
		super(conjunct, conjunction);
	}

	@Override
	public String toString() {
		return NAME;
	}

	/**
	 * Add {@link ObjectIntersectionFromFirstConjunctRule}s for the given
	 * {@link ModifiableIndexedObjectIntersectionOf} in the given
	 * {@link ModifiableOntologyIndex}
	 * 
	 * @param conjunction
	 * @param index
	 * @return {@code true} if the operation is successful and {@code false} if
	 *         not; if {@code false} is return, the index remains unchanged
	 */
	public static boolean addRulesFor(
			ModifiableIndexedObjectIntersectionOf conjunction,
			ModifiableOntologyIndex index) {
		return index.add(
				conjunction.getSecondConjunct(),
				new ObjectIntersectionFromFirstConjunctRule(conjunction
						.getFirstConjunct(), conjunction));
	}

	/**
	 * Removes {@link ObjectIntersectionFromFirstConjunctRule}s for the given
	 * {@link ModifiableIndexedObjectIntersectionOf} in the given
	 * {@link ModifiableOntologyIndex}
	 * 
	 * @param conjunction
	 * @param index
	 * @return {@code true} if the operation is successful and {@code false} if
	 *         not; if {@code false} is return, the index remains unchanged
	 */
	public static boolean removeRulesFor(
			ModifiableIndexedObjectIntersectionOf conjunction,
			ModifiableOntologyIndex index) {
		return index.remove(
				conjunction.getSecondConjunct(),
				new ObjectIntersectionFromFirstConjunctRule(conjunction
						.getFirstConjunct(), conjunction));
	}

	@Override
	public boolean addTo(Chain<ChainableSubsumerRule> ruleChain) {
		if (isEmpty())
			return true;
		return addTo(ruleChain.getCreate(MATCHER_, FACTORY_));
	}

	@Override
	public boolean removeFrom(Chain<ChainableSubsumerRule> ruleChain) {
		if (isEmpty())
			return true;
		AbstractObjectIntersectionFromConjunctRule rule = ruleChain
				.find(MATCHER_);
		if (rule == null)
			return false;
		boolean success = removeFrom(rule);
		if (success && rule.isEmpty()) {
			ruleChain.remove(MATCHER_);
		}
		return success;
	}

	@Override
	public void accept(LinkedSubsumerRuleVisitor<?> visitor,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

	private static final Matcher<ChainableSubsumerRule, ObjectIntersectionFromFirstConjunctRule> MATCHER_ = new SimpleTypeBasedMatcher<ChainableSubsumerRule, ObjectIntersectionFromFirstConjunctRule>(
			ObjectIntersectionFromFirstConjunctRule.class);

	private static final ReferenceFactory<ChainableSubsumerRule, ObjectIntersectionFromFirstConjunctRule> FACTORY_ = new ReferenceFactory<ChainableSubsumerRule, ObjectIntersectionFromFirstConjunctRule>() {
		@Override
		public ObjectIntersectionFromFirstConjunctRule create(
				ChainableSubsumerRule tail) {
			return new ObjectIntersectionFromFirstConjunctRule(tail);
		}
	};

}
