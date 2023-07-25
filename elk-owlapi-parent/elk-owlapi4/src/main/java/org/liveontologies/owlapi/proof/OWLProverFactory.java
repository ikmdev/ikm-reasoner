package org.liveontologies.owlapi.proof;

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

//import javax.annotation.Nonnull;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

/**
 * An {@link OWLReasonerFactory} that creates {@link OWLProver}s
 * 
 * @author Yevgeny Kazakov
 */
public interface OWLProverFactory extends OWLReasonerFactory {

//	@Override
//	OWLProver createNonBufferingReasoner(@Nonnull OWLOntology ontology);
//
//	@Override
//	OWLProver createReasoner(@Nonnull OWLOntology ontology);
//
//	@Override
//	OWLProver createNonBufferingReasoner(@Nonnull OWLOntology ontology,
//			@Nonnull OWLReasonerConfiguration config);
//
//	@Override
//	OWLProver createReasoner(@Nonnull OWLOntology ontology,
//			@Nonnull OWLReasonerConfiguration config);

	@Override
	OWLProver createNonBufferingReasoner(OWLOntology ontology);

	@Override
	OWLProver createReasoner(OWLOntology ontology);

	@Override
	OWLProver createNonBufferingReasoner(OWLOntology ontology, OWLReasonerConfiguration config);

	@Override
	OWLProver createReasoner(OWLOntology ontology, OWLReasonerConfiguration config);

}
