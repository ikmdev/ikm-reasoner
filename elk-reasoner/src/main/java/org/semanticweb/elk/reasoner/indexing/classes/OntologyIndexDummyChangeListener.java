
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.OntologyIndex;
import org.semanticweb.elk.reasoner.saturation.rules.contextinit.LinkedContextInitRule;

/**
 * An {@link OntologyIndex.ChangeListener} that does nothing; can be used as a
 * prototype to implement other listeners
 * 
 * @author Yevgeny Kazakov
 *
 */
public class OntologyIndexDummyChangeListener
		extends IndexedObjectCacheDummyChangeListener
		implements OntologyIndex.ChangeListener {

	@Override
	public void reflexiveObjectPropertyAddition(IndexedObjectProperty property,
			ElkAxiom reason) {
		// does nothing by default
	}

	@Override
	public void reflexiveObjectPropertyRemoval(IndexedObjectProperty property,
			ElkAxiom reason) {
		// does nothing by default
	}

	@Override
	public void contextInitRuleHeadSet(LinkedContextInitRule rule) {
		// does nothing by default
	}

	@Override
	public void negativeOwlThingAppeared() {
		// does nothing by default
	}

	@Override
	public void negativeOwlThingDisappeared() {
		// does nothing by default
	}

	@Override
	public void positiveOwlNothingAppeared() {
		// does nothing by default
	}

	@Override
	public void positiveOwlNothingDisappeared() {
		// does nothing by default
	}

}
