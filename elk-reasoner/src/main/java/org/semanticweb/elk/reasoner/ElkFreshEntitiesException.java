
package org.semanticweb.elk.reasoner;

import java.util.HashSet;
import java.util.Set;

import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * Exception that is thrown when a query that is asked to the reasoner refers to
 * vocabulary symbols that do not occur in the ontology yet.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkFreshEntitiesException extends ElkException {

	private static final long serialVersionUID = -4462031988813386808L;

	protected final Set<ElkEntity> entities;

	public ElkFreshEntitiesException(ElkEntity entity) {
		super();
		entities = new HashSet<ElkEntity>();
		entities.add(entity);
	}

	public ElkFreshEntitiesException(Set<ElkEntity> entities) {
		super();
		this.entities = entities;
	}

	public Set<ElkEntity> getEntities() {
		return entities;
	}

}
