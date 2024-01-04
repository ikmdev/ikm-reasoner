
package org.semanticweb.elk.reasoner.incremental;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * TODO doc
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 *         
 * @param <T> 
 */
public interface AxiomLoadingListener<T> {

	public void notify(T axiom);

	public static AxiomLoadingListener<ElkAxiom> DUMMY = new AxiomLoadingListener<ElkAxiom>() {

		@Override
		public void notify(ElkAxiom axiom) {
			// does nothing
		}
	};
}
