
package org.semanticweb.elk.matching;



import java.util.Collection;
import java.util.Queue;

import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.InferenceMatchBaseFactory;
import org.semanticweb.elk.matching.inferences.InferenceMatchDelegatingFactory;
import org.semanticweb.elk.matching.inferences.InferenceMatch.Factory;

class InferenceMatchBufferringFactory extends InferenceMatchDelegatingFactory {

	private final Collection<InferenceMatch> newInferences_;

	InferenceMatchBufferringFactory(Factory mainFactory,
			Collection<InferenceMatch> newInferences) {
		super(mainFactory);
		this.newInferences_ = newInferences;
	}

	InferenceMatchBufferringFactory(Queue<InferenceMatch> toDo) {
		this(new InferenceMatchBaseFactory(), toDo);
	}

	@Override
	protected <T extends InferenceMatch> T filter(T match) {
		newInferences_.add(match);
		return match;
	}

}
