package dev.ikm.elk.snomed.reasoner;
/*
 * #%L
 * ELK OWL API Binding
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

import org.semanticweb.elk.loading.ElkLoadingException;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.owlapix.model.OWLOntologyChange;

/**
 * Changed from visitor pattern, since this implementation doesn't use AddAxiom
 * and RemoveAxiom classes.
 */
public class OwlOntologyChangeProcessorVisitor {

	private static final Logger LOGGER_ = LoggerFactory.getLogger(OwlOntologyChangeProcessorVisitor.class);

	private final ElkAxiomProcessor axiomInserter_, axiomDeleter_;

	private ElkLoadingException error_ = null;

	OwlOntologyChangeProcessorVisitor(ElkAxiomProcessor axiomInserter, ElkAxiomProcessor axiomDeleter) {
		this.axiomInserter_ = axiomInserter;
		this.axiomDeleter_ = axiomDeleter;
	}

	public void process(OWLOntologyChange change) {
		ElkAxiom axiom = change.getAxiom();
		if (change.isAddAxiom()) {
			axiomInserter_.visit(axiom);
			if (LOGGER_.isTraceEnabled())
				LOGGER_.trace("adding " + axiom);
			return;
		}
		if (change.isRemoveAxiom()) {
			axiomDeleter_.visit(axiom);
			if (LOGGER_.isTraceEnabled())
				LOGGER_.trace("removing " + axiom);
			return;
		}
		error_ = new ElkLoadingException("Ontology change " + change.toString() + " is not supported");
	}

	public ElkLoadingException getError() {
		return error_;
	}

}
