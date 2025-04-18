/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2013 Department of Computer Science, University of Oxford
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
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Collections;
import java.util.Set;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.TypeNode;

/**
 * An {@link OrphanTaxonomyNode} with instances.
 * 
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 * 
 * @param <T>
 *            the type of objects in this node
 * @param <I>
 *            the type of instances of this node
 */
public class OrphanTypeNode<T extends ElkEntity, I extends ElkEntity>
		extends OrphanTaxonomyNode<T> implements TypeNode<T, I> {

	final Set<OrphanInstanceNode<T, I>> instanceNodes;

	public OrphanTypeNode(final Iterable<? extends T> members, final int size,
			final T canonical, final Taxonomy<T> taxonomy,
			final int estimatedInstanceNodes) {
		super(members, size, canonical, taxonomy);
		this.instanceNodes = new UnifiedSet<OrphanInstanceNode<T, I>>(
				estimatedInstanceNodes);
	}

	public void addInstanceNode(OrphanInstanceNode<T, I> instaneNode) {
		instanceNodes.add(instaneNode);
	}

	@Override
	public Set<? extends TypeNode<T, I>> getDirectSuperNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends TypeNode<T, I>> getAllSuperNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends TypeNode<T, I>> getDirectSubNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends TypeNode<T, I>> getAllSubNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends OrphanInstanceNode<T, I>> getDirectInstanceNodes() {
		return Collections.unmodifiableSet(instanceNodes);
	}

	@Override
	public Set<? extends OrphanInstanceNode<T, I>> getAllInstanceNodes() {
		return getDirectInstanceNodes();
	}

}
