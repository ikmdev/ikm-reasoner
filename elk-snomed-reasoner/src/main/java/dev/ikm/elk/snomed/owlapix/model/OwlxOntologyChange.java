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
 * @author Matthew Horridge, The University Of Manchester, Bio-Health
 *         Informatics Group
 * @since 2.0.0
 */
//public abstract class OWLOntologyChange implements // HasSignature, Serializable {
public class OwlxOntologyChange implements Serializable {

	private static final long serialVersionUID = 40000L;

	private final OwlxOntology ontology;

	private ElkAxiom addedAxiom;
	private ElkAxiom removedAxiom;

	/**
	 * @param ontonogy the ontology to which the change is to be applied
	 */
//    public OWLOntologyChange(@Nonnull OWLOntology ont) {
	private OwlxOntologyChange(OwlxOntology ontology) {
		Objects.requireNonNull(ontology);
		this.ontology = ontology;
	}

	public static OwlxOntologyChange createAdded(OwlxOntology ont, ElkAxiom axiom) {
		OwlxOntologyChange change = new OwlxOntologyChange(ont);
		Objects.requireNonNull(axiom);
		change.addedAxiom = axiom;
		return change;
	}

	public static OwlxOntologyChange createRemoved(OwlxOntology ont, ElkAxiom axiom) {
		OwlxOntologyChange change = new OwlxOntologyChange(ont);
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
	 * @param type axiom type to check
	 * @return true if this is an axiom change and the axiom type is the specified
	 *         type
	 */
//    public boolean isAxiomChange(AxiomType<?> type) {
//        return getAddedOrRemovedAxiom().filter(ax -> ax.getAxiomType().equals(type)).isPresent();
//    }

	/**
	 * Determines if the change will cause the addition or removal of an axiom from
	 * an ontology.
	 * 
	 * @return {@code true} if the change is an {@code OWLAddAxiomChange} or
	 *         {@code OWLRemoveAxiomChange} otherwise {@code false}.
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
	 * Determines if this change is an import change and hence causes a change to
	 * the imports closure of an ontology.
	 * 
	 * @return {@code true} if this change is an import change, otherwise
	 *         {@code false}.
	 */
//	public abstract boolean isImportChange();

	/**
	 * Gets the ontology that the change is/was applied to.
	 * 
	 * @return The ontology that the change is applicable to
	 */
	public OwlxOntology getOntology() {
		return ontology;
	}

	/**
	 * Gets the data (independent of the ontology) associated with this specific
	 * change.
	 * 
	 * @return The {@link OWLOntologyChangeData} associated with this
	 *         {@code OWLOntologyChange}.
	 */
//	@Nonnull
//	public abstract OWLOntologyChangeData getChangeData();

	/**
	 * Gets a {@link OWLOntologyChangeRecord} that is derived from this
	 * {@code OWLOntologyChange}'s {@link OWLOntologyID} and it's
	 * {@link OWLOntologyChangeData}.
	 * 
	 * @return An {@link OWLOntologyChangeRecord} containing an
	 *         {@link OWLOntologyID} equal to the {@link OWLOntologyID} of this
	 *         {@code OWLOntologyChange}'s {@link OWLOntology}. Not {@code null} .
	 */
//	@Nonnull
//	public OWLOntologyChangeRecord getChangeRecord() {
//		return new OWLOntologyChangeRecord(ontology.getOntologyID(), getChangeData());
//	}

	/**
	 * Gets the signature of this ontology change. That is, the set of entities
	 * appearing in objects in this change.
	 * 
	 * @return A set of entities that correspond to the signature of this object.
	 *         The set is a copy, changes are not reflected back.
	 */
//	@Override
//	@Nonnull
//	public abstract Set<OWLEntity> getSignature();

	/**
	 * Accepts a visitor
	 * 
	 * @param visitor The visitor
	 */
//	public abstract void accept(@Nonnull OWLOntologyChangeVisitor visitor);

	/**
	 * Accepts a visitor
	 * 
	 * @param visitor The visitor
	 * @param <O>     visitor return type
	 * @return visitor value
	 */
//	@Nonnull
//	public abstract <O> O accept(@Nonnull OWLOntologyChangeVisitorEx<O> visitor);

	/**
	 * @return the reverse of this change; can be used to create undo changes.
	 */
//	public abstract OWLOntologyChange reverseChange();
}
