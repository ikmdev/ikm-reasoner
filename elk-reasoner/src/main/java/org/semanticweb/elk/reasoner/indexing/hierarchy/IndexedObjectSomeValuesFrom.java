/*
 * #%L
 * elk-reasoner
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 Department of Computer Science, University of Oxford
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.semanticweb.elk.reasoner.indexing.hierarchy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;
import org.semanticweb.elk.reasoner.indexing.visitors.IndexedClassExpressionVisitor;
import org.semanticweb.elk.reasoner.indexing.visitors.IndexedObjectSomeValuesFromVisitor;
import org.semanticweb.elk.reasoner.saturation.conclusions.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.Conclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.NegativeSuperClassExpression;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.properties.SaturatedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.rules.BackwardLinkRules;
import org.semanticweb.elk.reasoner.saturation.rules.ContextRules;
import org.semanticweb.elk.reasoner.saturation.rules.RuleEngine;
import org.semanticweb.elk.util.collections.HashSetMultimap;
import org.semanticweb.elk.util.collections.LazySetIntersection;
import org.semanticweb.elk.util.collections.Multimap;
import org.semanticweb.elk.util.collections.chains.Chain;
import org.semanticweb.elk.util.collections.chains.Matcher;
import org.semanticweb.elk.util.collections.chains.ReferenceFactory;

/**
 * Represents all occurrences of an ElkObjectSomeValuesFrom in an ontology.
 * 
 * @author Frantisek Simancik
 * 
 */
public class IndexedObjectSomeValuesFrom extends IndexedClassExpression {

	// logger for this class
	private static final Logger LOGGER_ = Logger
			.getLogger(IndexedObjectSomeValuesFrom.class);

	protected final IndexedObjectProperty property;

	protected final IndexedClassExpression filler;

	IndexedObjectSomeValuesFrom(IndexedObjectProperty indexedObjectProperty,
			IndexedClassExpression filler) {
		this.property = indexedObjectProperty;
		this.filler = filler;
	}

	/**
	 * @return The indexed object property comprising this ObjectSomeValuesFrom.
	 */
	public IndexedObjectProperty getRelation() {
		return property;
	}

	/**
	 * @return The indexed class expression comprising this
	 *         ObjectSomeValuesFrom.
	 */
	public IndexedClassExpression getFiller() {
		return filler;
	}

	public <O> O accept(IndexedObjectSomeValuesFromVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(IndexedClassExpressionVisitor<O> visitor) {
		return accept((IndexedObjectSomeValuesFromVisitor<O>) visitor);
	}

	@Override
	protected void updateOccurrenceNumbers(int increment,
			int positiveIncrement, int negativeIncrement) {

		if (negativeOccurrenceNo == 0 && negativeIncrement > 0) {
			// first negative occurrence of this expression
			registerCompositionRule();
		}

		if (positiveOccurrenceNo == 0 && positiveIncrement > 0) {
			// first positive occurrence of this expression
			filler.addPosPropertyInExistential(property);
		}

		positiveOccurrenceNo += positiveIncrement;
		negativeOccurrenceNo += negativeIncrement;

		if (negativeOccurrenceNo == 0 && negativeIncrement < 0) {
			// no negative occurrences of this expression left
			deregisterCompositionRule();
		}

		if (positiveOccurrenceNo == 0 && positiveIncrement < 0) {
			// no positive occurrences of this expression left
			filler.removePosPropertyInExistential(property);
		}
	}

	public void registerCompositionRule() {
		filler.getChainCompositionRules()
				.getCreate(ThisCompositionRule.MATCHER_,
						ThisCompositionRule.FACTORY_).addNegExistential(this);
	}

	public void deregisterCompositionRule() {
		Chain<ContextRules> rules = filler.getChainCompositionRules();
		ThisCompositionRule rule = rules.find(ThisCompositionRule.MATCHER_);
		if (rule != null) {
			rule.removeNegExistential(this);
			if (rule.isEmpty())
				rules.remove(ThisCompositionRule.MATCHER_);
		} else {
			// TODO: throw/log something, this should never happen
		}
	}

	@Override
	public String toString() {
		return "ObjectSomeValuesFrom(" + this.property + ' ' + this.filler
				+ ')';
	}

	@Override
	public void applyDecompositionRule(RuleEngine ruleEngine, Context context) {
		ruleEngine.produce(ruleEngine.getCreateContext(filler),
				new BackwardLink(property, context));
	}

	private static class ThisCompositionRule extends ContextRules {

		private final Collection<IndexedObjectSomeValuesFrom> negExistentials_;

		ThisCompositionRule(ContextRules tail) {
			super(tail);
			this.negExistentials_ = new ArrayList<IndexedObjectSomeValuesFrom>(
					1);
		}

		private void addNegExistential(IndexedObjectSomeValuesFrom existential) {
			negExistentials_.add(existential);
		}

		private void removeNegExistential(
				IndexedObjectSomeValuesFrom existential) {
			negExistentials_.remove(existential);
		}

		/**
		 * @return {@code true} if this rule never does anything
		 */
		private boolean isEmpty() {
			return negExistentials_.isEmpty();
		}

		@Override
		public void apply(RuleEngine ruleEngine, Context context) {

			final Set<IndexedPropertyChain> candidatePropagationProperties = context
					.getRoot().getPosPropertiesInExistentials();

			if (candidatePropagationProperties == null)
				return;

			for (IndexedObjectSomeValuesFrom e : negExistentials_) {
				IndexedPropertyChain relation = e.getRelation();
				// creating propagations for relevant sub-properties of the
				// relation
				for (IndexedPropertyChain property : new LazySetIntersection<IndexedPropertyChain>(
						candidatePropagationProperties, relation.getSaturated()
								.getSubProperties())) {
					addPropagation(property,
							new NegativeSuperClassExpression(e), context,
							ruleEngine);
				}

				// creating propagations for relevant sub-compositions of the
				// relation
				for (IndexedPropertyChain property : relation.getSaturated()
						.getSubCompositions()) {
					SaturatedPropertyChain propertySaturation = property
							.getSaturated();
					if (!new LazySetIntersection<IndexedPropertyChain>(
							candidatePropagationProperties,
							propertySaturation.getRightSubProperties())
							.isEmpty()
							|| propertySaturation
									.hasReflexiveRightSubProperty()) {
						addPropagation(property,
								new NegativeSuperClassExpression(e), context,
								ruleEngine);
					}
				}

				// propagating to the this context if relation is relflexive
				if (relation.getSaturated().isReflexive())
					ruleEngine.produce(context,
							new NegativeSuperClassExpression(e));
			}

		}

		private void addPropagation(IndexedPropertyChain propRelation,
				Conclusion carry, Context context, RuleEngine ruleEngine) {

			if (LOGGER_.isTraceEnabled())
				LOGGER_.trace(context.getRoot() + ": new propagation "
						+ propRelation + "->" + carry);

			if (context
					.getChainBackwardLinkRules()
					.getCreate(ThisBackwardLinkRule.MATCHER_,
							ThisBackwardLinkRule.FACTORY_)
					.addPropagationByObjectProperty(propRelation, carry)) {
				// propagate over all backward links
				final Multimap<IndexedPropertyChain, Context> backLinks = context
						.getBackwardLinksByObjectProperty();

				Collection<Context> targets = backLinks.get(propRelation);

				for (Context target : targets)
					ruleEngine.produce(target, carry);
			}

		}

		private static Matcher<ContextRules, ThisCompositionRule> MATCHER_ = new Matcher<ContextRules, ThisCompositionRule>() {
			@Override
			public ThisCompositionRule match(ContextRules chain) {
				if (chain instanceof ThisCompositionRule)
					return (ThisCompositionRule) chain;
				else
					return null;
			}
		};

		private static ReferenceFactory<ContextRules, ThisCompositionRule> FACTORY_ = new ReferenceFactory<ContextRules, ThisCompositionRule>() {
			@Override
			public ThisCompositionRule create(ContextRules tail) {
				return new ThisCompositionRule(tail);
			}
		};

	}

	private static class ThisBackwardLinkRule extends BackwardLinkRules {

		private final Multimap<IndexedPropertyChain, Conclusion> propagationsByObjectProperty_;

		ThisBackwardLinkRule(BackwardLinkRules tail) {
			super(tail);
			this.propagationsByObjectProperty_ = new HashSetMultimap<IndexedPropertyChain, Conclusion>(
					1);
		}

		private boolean addPropagationByObjectProperty(
				IndexedPropertyChain propRelation, Conclusion conclusion) {
			return propagationsByObjectProperty_.add(propRelation, conclusion);
		}

		@Override
		public void apply(RuleEngine ruleEngine, BackwardLink link) {
			for (Conclusion carry : propagationsByObjectProperty_.get(link
					.getReltaion()))
				ruleEngine.produce(link.getTarget(), carry);
		}

		private static Matcher<BackwardLinkRules, ThisBackwardLinkRule> MATCHER_ = new Matcher<BackwardLinkRules, ThisBackwardLinkRule>() {

			@Override
			public ThisBackwardLinkRule match(BackwardLinkRules chain) {
				if (chain instanceof ThisBackwardLinkRule)
					return (ThisBackwardLinkRule) chain;
				else
					return null;
			}

		};

		private static ReferenceFactory<BackwardLinkRules, ThisBackwardLinkRule> FACTORY_ = new ReferenceFactory<BackwardLinkRules, ThisBackwardLinkRule>() {

			@Override
			public ThisBackwardLinkRule create(BackwardLinkRules tail) {
				return new ThisBackwardLinkRule(tail);
			}

		};

	}

}