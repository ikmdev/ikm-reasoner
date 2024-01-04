
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkSWRLRuleVisitor;



/**
 * A dummy interface for SWRL rules. We don't have a proper object
 * representation of those (with atoms, predicates, etc.), since SWRL isn't
 * really a part of the OWL 2 spec, but use this dummy interface just to parse
 * rules in a reasonable way (since SWRL rules do occur in some OWL ontologies).
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public interface ElkSWRLRule extends ElkAxiom {

	/**
	 * Accept an {@link ElkSWRLRuleVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkSWRLRuleVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * No arguments since we don't have a full representation of SWRL rules
		 * 
		 * @return a dummy object
		 */
		public ElkSWRLRule getSWRLRule();
	}

}
