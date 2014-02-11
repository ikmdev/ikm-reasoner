package org.semanticweb.elk.reasoner.saturation.conclusions.visitors;

/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2012 Department of Computer Science, University of Oxford
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

import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.SaturationStateWriter;
import org.semanticweb.elk.reasoner.saturation.conclusions.ComposedSubsumer;
import org.semanticweb.elk.reasoner.saturation.conclusions.Conclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.DecomposedSubsumer;
import org.semanticweb.elk.reasoner.saturation.conclusions.Subsumer;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A {@link ConclusionVisitor} that marks the source {@link Context} of the
 * {@link Conclusion} as not saturated if the {@link Conclusion} can potentially
 * be re-derived. The visit method returns {@link true} only if the source
 * {@link Context} has changed in this way.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ConclusionSourceContextUnsaturationVisitor extends
		AbstractConclusionVisitor<Context, Boolean> {

	private final SaturationState state_;

	private final SaturationStateWriter writer_;

	public ConclusionSourceContextUnsaturationVisitor(SaturationState state,
			SaturationStateWriter writer) {
		this.state_ = state;
		this.writer_ = writer;
	}

	@Override
	Boolean defaultVisit(Conclusion conclusion, Context context) {
		IndexedClassExpression root = conclusion.getSourceRoot(context
				.getRoot());
		writer_.markAsNotSaturated(state_.getContext(root));
		return true;
	}

	Boolean defaultVisit(Subsumer conclusion, Context context) {
		// if the subsumer does not occur in the ontology anymore, it cannot be
		// re-derived, and thus, the context should not be modified
		// TODO: extend this check to other types of conclusions
		if (conclusion.getExpression().occurs())
			return defaultVisit((Conclusion) conclusion, context);
		return true;
	}

	@Override
	public Boolean visit(ComposedSubsumer conclusion, Context context) {
		return defaultVisit(conclusion, context);
	}

	@Override
	public Boolean visit(DecomposedSubsumer conclusion, Context context) {
		return defaultVisit(conclusion, context);
	}
}
