/* This file is part of the OWL API.
 * The contents of this file are subject to the LGPL License, Version 3.0.
 * Copyright 2014, The University of Manchester
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0 in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License. */
package dev.ikm.elk.snomed.owlapix.model;

/*-
 * #%L
 * ELK Reasoner for SNOMED
 * %%
 * Copyright (C) 2023 Integrated Knowledge Management
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

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Copied from OWLOntologyChange in the OWL API.
 * 
 * Now an implementation for the AddAxiom and RemoveAxiom classes in the OWL
 * API.
 * 
 * Added the createAdded and createRemoved static factory methods, and changed
 * constructor to private.
 * 
 * Removed methods not used in Elk that would have required implementation.
 * 
 * @author Matthew Horridge, The University Of Manchester, Bio-Health
 *         Informatics Group
 * @since 2.0.0
 */
public class OWLOntologyChange implements Serializable {

	private static final long serialVersionUID = 40000L;

	private final OWLOntology ontology;

	private ElkAxiom addedAxiom;
	private ElkAxiom removedAxiom;

	/**
	 * @param ontonogy the ontology to which the change is to be applied
	 */
	// changed from public to private
	private OWLOntologyChange(OWLOntology ontology) {
		Objects.requireNonNull(ontology);
		this.ontology = ontology;
	}

	public static OWLOntologyChange createAdded(OWLOntology ont, ElkAxiom axiom) {
		OWLOntologyChange change = new OWLOntologyChange(ont);
		Objects.requireNonNull(axiom);
		change.addedAxiom = axiom;
		return change;
	}

	public static OWLOntologyChange createRemoved(OWLOntology ont, ElkAxiom axiom) {
		OWLOntologyChange change = new OWLOntologyChange(ont);
		Objects.requireNonNull(axiom);
		change.removedAxiom = axiom;
		return change;
	}

	/**
	 * @return for add axiom changes, the axiom added; empty optional otherwise
	 */
	public Optional<ElkAxiom> getAddedAxiom() {
		return Optional.ofNullable(addedAxiom);
	}

	/**
	 * @return for remove axiom changes, the axiom removed; empty optional otherwise
	 */
	public Optional<ElkAxiom> getRemovedAxiom() {
		return Optional.ofNullable(removedAxiom);
	}

	/**
	 * Determines if the change will cause the addition or removal of an axiom from
	 * an ontology.
	 * 
	 * In the OWL API returns true if the change is an AddAxiom or RemoveAxiom,
	 * otherwise false.
	 * 
	 * @return {@code true} since this implementation is only add and remove
	 */
	public boolean isAxiomChange() {
		return true;
	}

	/**
	 * Determines if the change will add an axiom to an ontology.
	 * 
	 * @return {@code true} if the change is an AddAxiom change and it will add an
	 *         axiom to an ontology, {@code false} otherwise.
	 */
	public boolean isAddAxiom() {
		return addedAxiom != null;
	}

	/**
	 * Determines if the change will remove an axiom from an ontology.
	 * 
	 * @return {@code true} if the change is a RemoveAxiom change and it will remove
	 *         an axiom from an ontology, {@code false} otherwise.
	 */
	public boolean isRemoveAxiom() {
		return removedAxiom != null;
	}

	/**
	 * If the change is an axiom change (i.e. AddAxiom or RemoveAxiom) this method
	 * obtains the axiom.
	 * 
	 * @return The Axiom if this change is an axiom change
	 * @throws IllegalStateException if the change has no axiom;
	 *                               UnsupportedOperationException If the change is
	 *                               not an axiom change (check with the
	 *                               {@code isAxiomChange} method first).
	 */
	public ElkAxiom getAxiom() {
		if (addedAxiom != null)
			return addedAxiom;
		if (removedAxiom != null)
			return removedAxiom;
		throw new IllegalStateException();
	}

	/**
	 * Gets the ontology that the change is/was applied to.
	 * 
	 * @return The ontology that the change is applicable to
	 */
	public OWLOntology getOntology() {
		return ontology;
	}

}
