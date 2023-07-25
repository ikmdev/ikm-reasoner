/*-
 * #%L
 * OWL API Proof Extension
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2014 - 2016 Live Ontologies Project
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
package org.liveontologies.owlapi.proof;

import org.liveontologies.puli.DynamicProof;
import org.liveontologies.puli.Inference;
import org.liveontologies.puli.Inferences;
import org.liveontologies.puli.Proofs;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.UnsupportedEntailmentTypeException;

/**
 * An {@link OWLReasoner} that can provide proofs for the entailed axioms.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Yevgeny Kazakov
 */
public interface OWLProver extends OWLReasoner {

	/**
	 * @param entailment
	 *            the {@link OWLAxiom} which needs to be proved
	 * 
	 * @return the {@link DynamicProof} using which the given axiom can be
	 *         derived from the axioms in the ontology, if it is entailed. If
	 *         the axiom is not entailed, any proof that does not derive the
	 *         given axiom can be returned, e.g., the empty proof.
	 * 
	 *         For deriving axioms that occur in the ontology, the proof should
	 *         use asserted inferences (for which
	 *         {@link Inferences#isAsserted(Inference)} returns {@code true}).
	 * 
	 *         If the proof for the given axiom changes (i.e., the result of
	 *         calling this method with the same axiom would be come different,
	 *         e.g., due to the changes in the ontology), the registered
	 *         listeners of the {@link DynamicProof} must be notified
	 *         accordingly.
	 * 
	 * @throws UnsupportedEntailmentTypeException
	 *             if the proof cannot be provided for the given entailment type
	 *
	 * @see Proofs#emptyProof()
	 * @see Inferences#isAsserted(Inference)
	 * @see DynamicProof.ChangeListener
	 */
	public DynamicProof<? extends Inference<OWLAxiom>> getProof(
			OWLAxiom entailment) throws UnsupportedEntailmentTypeException;

}
