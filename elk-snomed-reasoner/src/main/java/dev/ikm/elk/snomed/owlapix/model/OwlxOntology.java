package dev.ikm.elk.snomed.owlapix.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.iris.ElkAbbreviatedIri;
import org.semanticweb.elk.owl.iris.ElkFullIri;
import org.semanticweb.elk.owl.iris.ElkPrefixImpl;
import org.semanticweb.elk.owl.managers.ElkObjectEntityRecyclingFactory;

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

public class OwlxOntology implements OwlxOntologyManager {

	private ElkObjectEntityRecyclingFactory objectFactory;

	private ElkPrefixImpl prefix;

	private HashMap<String, ElkClass> elkClasses = new HashMap<>();

	private HashMap<String, ElkObjectProperty> elkObjectProperties = new HashMap<>();

	private HashSet<ElkAxiom> axioms = new HashSet<>();

	public ElkObject.Factory getObjectFactory() {
		return objectFactory;
	}

	public Set<ElkAxiom> getAxioms() {
		return axioms;
	}

	public boolean addAxiom(ElkAxiom axiom) {
		boolean ret = axioms.add(axiom);
		OwlxOntologyChange change = OwlxOntologyChange.createAdded(this, axiom);
		for (OwlxOntologyChangeListener listener : change_listeners) {
			try {
				listener.ontologiesChanged(List.of(change));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return ret;
	}

	public boolean removeAxiom(ElkAxiom axiom) {
		boolean ret = axioms.remove(axiom);
		OwlxOntologyChange change = OwlxOntologyChange.createRemoved(this, axiom);
		for (OwlxOntologyChangeListener listener : change_listeners) {
			try {
				listener.ontologiesChanged(List.of(change));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return ret;
	}

	public OwlxOntology() {
		super();
		objectFactory = new ElkObjectEntityRecyclingFactory();
		prefix = new ElkPrefixImpl(":", new ElkFullIri(""));
	}

	public ElkClass getOwlThing() {
		return objectFactory.getOwlThing();
	}

	public ElkClass getOwlNothing() {
		return objectFactory.getOwlNothing();
	}

	private ElkAbbreviatedIri getIri(String name) {
		return new ElkAbbreviatedIri(prefix, name);
	}

	public ElkClass getElkClass(String name) {
		return elkClasses.computeIfAbsent(name, k -> objectFactory.getClass(getIri(name)));
	}

	public ElkObjectProperty getElkObjectProperty(String name) {
		return elkObjectProperties.computeIfAbsent(name, key -> objectFactory.getObjectProperty(getIri(name)));
	}

	public ElkAxiom getElkSubObjectPropertyOfAxiom(String sub, String sup) {
		return objectFactory.getSubObjectPropertyOfAxiom(getElkObjectProperty(sub), getElkObjectProperty(sup));
	}

	public ElkTransitiveObjectPropertyAxiom getElkTransitiveObjectPropertyAxiom(String name) {
		return objectFactory.getTransitiveObjectPropertyAxiom(getElkObjectProperty(name));
	}

	public ElkAxiom getElkSubObjectPropertyChainOfAxiom(String name, String chain) {
		ElkObjectPropertyChain ch = objectFactory
				.getObjectPropertyChain(List.of(getElkObjectProperty(name), getElkObjectProperty(chain)));
		return objectFactory.getSubObjectPropertyOfAxiom(ch, getElkObjectProperty(name));
	}

	public ElkReflexiveObjectPropertyAxiom getElkReflexiveObjectPropertyAxiom(String name) {
		return objectFactory.getReflexiveObjectPropertyAxiom(getElkObjectProperty(name));
	}

	public ElkObjectSomeValuesFrom getElkObjectSomeValuesFrom(String prop, String filler) {
		return getElkObjectSomeValuesFrom(prop, getElkClass(filler));
	}

	public ElkObjectSomeValuesFrom getElkObjectSomeValuesFrom(String prop, ElkClassExpression expr) {
		return objectFactory.getObjectSomeValuesFrom(getElkObjectProperty(prop), expr);
	}

	public ElkEquivalentClassesAxiom getEquivalentClassesAxiom(String name, ElkClassExpression expr) {
		return objectFactory.getEquivalentClassesAxiom(getElkClass(name), expr);
	}

	public ElkSubClassOfAxiom getSubClassOfAxiom(String name, ElkClassExpression expr) {
		return objectFactory.getSubClassOfAxiom(getElkClass(name), expr);
	}

	public ElkAxiom getSubClassOfAxiom(ElkClassExpression expr, String name) {
		return objectFactory.getSubClassOfAxiom(expr, getElkClass(name));
	}

	public OwlxOntologyManager getOWLOntologyManager() {
		return this;
	}

	public Set<OwlxOntology> getImportsClosure() {
		return Set.of(this);
	}

	// OwlxOntologyManager

	private HashSet<OwlxOntologyChangeListener> change_listeners = new HashSet<>();

	public void addOntologyChangeListener(OwlxOntologyChangeListener ontologyChangeListener_) {
		change_listeners.add(ontologyChangeListener_);
	}

	public void addOntologyChangeProgessListener(OwlxOntologyChangeProgressListener ontologyChangeProgressListener_) {
		// TODO Auto-generated method stub

	}

	public void removeOntologyChangeListener(OwlxOntologyChangeListener ontologyChangeListener_) {
		change_listeners.remove(ontologyChangeListener_);
	}

	public void removeOntologyChangeProgessListener(
			OwlxOntologyChangeProgressListener ontologyChangeProgressListener_) {
		// TODO Auto-generated method stub

	}

}