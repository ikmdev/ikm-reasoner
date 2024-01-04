
package org.semanticweb.elk.reasoner.saturation.rules.contextinit;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionOwlThing;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.util.collections.chains.Chain;
import org.semanticweb.elk.util.collections.chains.Matcher;
import org.semanticweb.elk.util.collections.chains.ReferenceFactory;
import org.semanticweb.elk.util.collections.chains.SimpleTypeBasedMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link ChainableContextInitRule} that produces {@link SubClassInclusion}
 * {@code owl:Thing} in a context. It should be applied only if
 * {@code owl:Thing} occurs negatively in the ontology.
 */
public class OwlThingContextInitRule extends AbstractChainableContextInitRule {

	// logger for events
	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(OwlThingContextInitRule.class);

	public static final String NAME = "owl:Thing Introduction";

	private IndexedClass owlThing_;

	private OwlThingContextInitRule(ChainableContextInitRule tail) {
		super(tail);
	}

	private OwlThingContextInitRule(IndexedClass owlThing) {
		super(null);
		this.owlThing_ = owlThing;
	}

	/**
	 * Add an {@link OwlThingContextInitRule} to the given
	 * {@link ModifiableOntologyIndex}
	 * 
	 * @param owlThing
	 * @param index
	 * @return {@code true} if the operation was successful and {@code false}
	 *         otherwise; if {@code false} is returned, the index remains
	 *         unchanged
	 */
	public static boolean addRuleFor(IndexedClass owlThing,
			ModifiableOntologyIndex index) {
		LOGGER_.trace("{}: adding {}", owlThing, NAME);
		return index.addContextInitRule(new OwlThingContextInitRule(owlThing));
	}

	/**
	 * Removes an {@link OwlThingContextInitRule} from the given
	 * {@link ModifiableOntologyIndex}
	 * 
	 * @param owlThing
	 * @param index
	 * @return {@code true} if the operation was successful and {@code false}
	 *         otherwise; if {@code false} is returned, the index remains
	 *         unchanged
	 */
	public static boolean removeRuleFor(IndexedClass owlThing,
			ModifiableOntologyIndex index) {
		LOGGER_.trace("{}: removing {}", owlThing, NAME);
		return index
				.removeContextInitRule(new OwlThingContextInitRule(owlThing));
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(ContextInitialization premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		producer.produce(
				new SubClassInclusionOwlThing(premises.getRoot(), owlThing_));
	}

	@Override
	public boolean isTracingRule() {
		return true;
	}

	@Override
	public boolean addTo(Chain<ChainableContextInitRule> ruleChain) {
		OwlThingContextInitRule rule = ruleChain.getCreate(MATCHER_, FACTORY_);
		if (rule.owlThing_ == null) {
			// new rule created
			rule.owlThing_ = owlThing_;
		}
		// owl:Thing was already registered
		return true;
	}

	@Override
	public boolean removeFrom(Chain<ChainableContextInitRule> ruleChain) {
		return ruleChain.remove(MATCHER_) != null;
	}

	@Override
	public void accept(LinkedContextInitRuleVisitor<?> visitor,
			ContextInitialization premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

	private static final Matcher<ChainableContextInitRule, OwlThingContextInitRule> MATCHER_ = new SimpleTypeBasedMatcher<ChainableContextInitRule, OwlThingContextInitRule>(
			OwlThingContextInitRule.class);

	private static final ReferenceFactory<ChainableContextInitRule, OwlThingContextInitRule> FACTORY_ = new ReferenceFactory<ChainableContextInitRule, OwlThingContextInitRule>() {
		@Override
		public OwlThingContextInitRule create(ChainableContextInitRule tail) {
			return new OwlThingContextInitRule(tail);
		}
	};

}
