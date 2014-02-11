package org.semanticweb.elk.reasoner.saturation;

/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2014 Department of Computer Science, University of Oxford
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

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.semanticweb.elk.reasoner.indexing.OntologyIndex;
import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A {@link SaturationState} backed by a map from {@link IndexedClassExpression}
 * s to {@link ExtendedContext}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class MapSaturationState extends AbstractSaturationState {

	private final ConcurrentHashMap<IndexedClassExpression, ExtendedContext> contextAssignment_;

	public MapSaturationState(OntologyIndex index, int expectedSize) {
		super(index);
		this.contextAssignment_ = new ConcurrentHashMap<IndexedClassExpression, ExtendedContext>(
				expectedSize);
	}

	public MapSaturationState(OntologyIndex index) {
		super(index);
		this.contextAssignment_ = new ConcurrentHashMap<IndexedClassExpression, ExtendedContext>(
				index.getIndexedClassExpressions().size());
	}

	@Override
	public Collection<? extends Context> getContexts() {
		return contextAssignment_.values();
	}

	@Override
	public ExtendedContext getContext(IndexedClassExpression ice) {
		return contextAssignment_.get(ice);
	}

	@Override
	void resetContexts() {
		contextAssignment_.clear();
	}

	@Override
	ExtendedContext setIfAbsent(ExtendedContext context) {
		return contextAssignment_.putIfAbsent(context.getRoot(), context);
	}

}
