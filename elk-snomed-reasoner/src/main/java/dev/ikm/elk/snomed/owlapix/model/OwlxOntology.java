package dev.ikm.elk.snomed.owlapix.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;
import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
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

public class OwlxOntology implements OWLOntology, OWLOntologyManager {

	private ElkObjectEntityRecyclingFactory objectFactory;

	private ElkPrefixImpl prefix;

	private HashMap<String, ElkClass> elkClasses = new HashMap<>();

	private HashMap<String, ElkObjectProperty> elkObjectProperties = new HashMap<>();

	private HashMap<String, ElkDataProperty> elkDataProperties = new HashMap<>();

	private HashSet<ElkAxiom> axioms = new HashSet<>();

	public ElkObject.Factory getObjectFactory() {
		return objectFactory;
	}

	public Set<ElkAxiom> getAxioms() {
		return axioms;
	}

	public boolean addAxiom(ElkAxiom axiom) {
		boolean ret = axioms.add(axiom);
		OWLOntologyChange change = OWLOntologyChange.createAdded(this, axiom);
		for (OWLOntologyChangeListener listener : change_listeners) {
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
		OWLOntologyChange change = OWLOntologyChange.createRemoved(this, axiom);
		for (OWLOntologyChangeListener listener : change_listeners) {
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

	public ElkObjectProperty getOwlTopObjectProperty() {
		return objectFactory.getOwlTopObjectProperty();
	}

	public ElkObjectProperty getOwlBottomObjectProperty() {
		return objectFactory.getOwlBottomObjectProperty();
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

	public ElkDataProperty getElkDataProperty(String name) {
		return elkDataProperties.computeIfAbsent(name, key -> objectFactory.getDataProperty(getIri(name)));
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

	public ElkAxiom getElkSubDataPropertyOfAxiom(String sub, String sup) {
		return objectFactory.getSubDataPropertyOfAxiom(getElkDataProperty(sub), getElkDataProperty(sup));
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
		return getSubClassOfAxiom(getElkClass(name), expr);
	}

	public ElkSubClassOfAxiom getSubClassOfAxiom(ElkClassExpression expr, String name) {
		return getSubClassOfAxiom(expr, getElkClass(name));
	}

	public ElkSubClassOfAxiom getSubClassOfAxiom(ElkClassExpression subExpr, ElkClassExpression supExpr) {
		return objectFactory.getSubClassOfAxiom(subExpr, supExpr);
	}

	public ElkDataHasValue getDataHasValue(String name, ElkLiteral value) {
		return objectFactory.getDataHasValue(getElkDataProperty(name), value);
	}

	public OWLOntologyManager getOWLOntologyManager() {
		return this;
	}

	public Set<OWLOntology> getImportsClosure() {
		return Set.of(this);
	}

	// OwlxOntologyManager

	private HashSet<OWLOntologyChangeListener> change_listeners = new HashSet<>();

	public void addOntologyChangeListener(OWLOntologyChangeListener ontologyChangeListener_) {
		change_listeners.add(ontologyChangeListener_);
	}

	public void addOntologyChangeProgessListener(OWLOntologyChangeProgressListener ontologyChangeProgressListener_) {
		// TODO Auto-generated method stub

	}

	public void removeOntologyChangeListener(OWLOntologyChangeListener ontologyChangeListener_) {
		change_listeners.remove(ontologyChangeListener_);
	}

	public void removeOntologyChangeProgessListener(OWLOntologyChangeProgressListener ontologyChangeProgressListener_) {
		// TODO Auto-generated method stub

	}

}
