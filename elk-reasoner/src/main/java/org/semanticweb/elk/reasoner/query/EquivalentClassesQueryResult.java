
package org.semanticweb.elk.reasoner.query;



import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;

public class EquivalentClassesQueryResult {

	private final ElkClassExpression query_;

	private final Node<ElkClass> equivalentClasses_;

	private final boolean isComplete_;

	public EquivalentClassesQueryResult(ElkClassExpression query,
			Node<ElkClass> equivalentClasses, boolean isComplete) {
		this.query_ = query;
		this.equivalentClasses_ = equivalentClasses;
		this.isComplete_ = isComplete;
	}

	public ElkClassExpression getQuery() {
		return query_;
	}

	public Node<ElkClass> getEquivalentClasses() {
		return equivalentClasses_;
	}

	public boolean isComplete() {
		return isComplete_;
	}

}
