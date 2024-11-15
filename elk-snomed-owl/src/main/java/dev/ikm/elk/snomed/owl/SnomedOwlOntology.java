package dev.ikm.elk.snomed.owl;

/*-
 * #%L
 * ELK Integration with SNOMED using OWL API
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.owlapi.OWLAPIConfigProvider;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.functional.parser.OWLFunctionalSyntaxOWLParser;
import org.semanticweb.owlapi.functional.renderer.OWLFunctionalSyntaxRenderer;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.SnomedIds;

public class SnomedOwlOntology {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOwlOntology.class);

	@Deprecated
	public static final long root = SnomedIds.root;

	@Deprecated
	public static final long isa = SnomedIds.isa;

	@Deprecated
	public static final long role_group = SnomedIds.role_group;

	private OWLOntology ontology;

	private OWLDataFactory dataFactory;

	private OWLReasoner reasoner;

	private HashMap<Long, OWLClass> classMap;

	private HashMap<Long, OWLObjectProperty> objectPropertyMap;

	public OWLOntology getOntology() {
		return ontology;
	}

	public OWLDataFactory getDataFactory() {
		return dataFactory;
	}

	public OWLReasoner getReasoner() {
		return reasoner;
	}

	private SnomedOwlOntology(OWLOntologyManager mgr) throws OWLOntologyCreationException {
		this.ontology = mgr.createOntology();
		this.dataFactory = mgr.getOWLDataFactory();
	}

	public OWLOntologyManager getOntologyManager() {
		return ontology.getOWLOntologyManager();
	}

	public static List<String> readAxioms(Path file) throws IOException {
		// id effectiveTime active moduleId refsetId referencedComponentId owlExpression
		try (Stream<String> st = Files.lines(file)) {
			return st.skip(1).map(line -> line.split("\\t")) //
					.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
					.map(fields -> fields[6]) //
					.collect(Collectors.toCollection(ArrayList::new));
		}
	}

	public static List<String> getPrefixDeclaration(List<String> lines) {
		return lines.stream().filter(line -> line.startsWith("Prefix"))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public static String getOntologyDeclaration(List<String> lines) {
		return lines.stream().filter(line -> line.startsWith("Ontology")).findFirst().get();
	}

	public static List<String> readOntology(Path file) throws IOException {
		List<String> lines = readAxioms(file);
		List<String> prefix = getPrefixDeclaration(lines);
		lines.removeAll(prefix);
		String ontology = getOntologyDeclaration(lines);
		lines.remove(ontology);
		ontology = ontology.replace(")", "");
		lines.add(0, ontology);
		lines.addAll(0, prefix);
		lines.add(")");
		return lines;
	}

	public static SnomedOwlOntology createOntology() throws Exception {
		OWLOntologyManager mgr = OWLManager.createOWLOntologyManager();
		return new SnomedOwlOntology(mgr);
	}

	public void writeOntology(Path path) throws Exception {
		OWLFunctionalSyntaxRenderer fsr = new OWLFunctionalSyntaxRenderer();
		fsr.render(ontology, Files.newBufferedWriter(path));
	}

	private void initMaps() {
		classMap = new HashMap<>();
		for (OWLClass clazz : ontology.getClassesInSignature()) {
			classMap.put(getId(clazz), clazz);
		}
		objectPropertyMap = new HashMap<>();
		for (OWLObjectProperty prop : ontology.getObjectPropertiesInSignature()) {
			objectPropertyMap.put(getId(prop), prop);
		}
	}

	public Set<OWLAxiom> getAxioms() {
		return ontology.getAxioms(Imports.EXCLUDED);
	}

	private Set<OWLSubClassOfAxiom> gci_axioms = null;

	public Set<OWLSubClassOfAxiom> getGciAxioms() {
		if (gci_axioms == null)
			gci_axioms = getAxioms().stream().filter(ax -> ax instanceof OWLSubClassOfAxiom) //
					.map(ax -> ((OWLSubClassOfAxiom) ax)) //
					.filter(ax -> !ax.getSubClass().isClassExpressionLiteral()) //
					.collect(Collectors.toCollection(HashSet::new));
		return gci_axioms;
	}

	public Set<OWLSubClassOfAxiom> getGciAxioms(OWLClass clazz) {
		return getGciAxioms().stream().filter(ax -> ax.getSuperClass().equals(clazz))
				.collect(Collectors.toCollection(HashSet::new));
	}

	public static long getId(OWLClass clazz) {
		return Long.parseLong(clazz.getIRI().getShortForm());
	}

	public OWLClass getOwlClass(long id) {
		return classMap.get(id);
	}

	public Set<OWLClass> getOwlClasses() {
		return ontology.getClassesInSignature();
	}

	public Set<OWLObjectProperty> getOwlObjectProperties() {
		return ontology.getObjectPropertiesInSignature();
	}

	public Set<OWLDataProperty> getOwlDataProperties() {
		return ontology.getDataPropertiesInSignature();
	}

	public Set<OWLClassAxiom> getAxioms(OWLClass clazz) {
		return ontology.getAxioms(clazz, Imports.EXCLUDED);
	}

	public static long getId(OWLObjectProperty prop) {
		return Long.parseLong(prop.getIRI().getShortForm());
	}

	public OWLObjectProperty getOwlObjectProperty(long id) {
		return objectPropertyMap.get(id);
	}

	public static long getId(OWLDataProperty prop) {
		return Long.parseLong(prop.getIRI().getShortForm());
	}

	public Set<OWLObjectPropertyAxiom> getAxioms(OWLObjectProperty prop) {
		return ontology.getAxioms(prop, Imports.EXCLUDED);
	}

	public void addAxioms(Set<OWLAxiom> axioms) {
		OWLOntologyManager mgr = getOntologyManager();
		mgr.addAxioms(ontology, axioms);
	}

	public void removeAxioms(Set<OWLAxiom> axioms) {
		OWLOntologyManager mgr = getOntologyManager();
		mgr.removeAxioms(ontology, axioms);
	}

	public void loadOntology(Set<OWLAxiom> axioms) {
		addAxioms(axioms);
		initMaps();
	}

	public void loadOntology(Path file) throws Exception {
		List<String> lines = readOntology(file);
		loadOntology(lines);
	}

	public void loadOntology(List<String> lines) throws Exception {
		OWLOntologyLoaderConfiguration config = new OWLAPIConfigProvider().get();
		new OWLFunctionalSyntaxOWLParser().parse(new StringDocumentSource(String.join("\n", lines)), ontology, config);
		initMaps();
	}

	public void classify() {
		long beg = System.currentTimeMillis();
		OWLReasonerFactory rf = new ElkReasonerFactory();
		reasoner = rf.createReasoner(ontology);
		reasoner.flush();
		reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY, InferenceType.OBJECT_PROPERTY_HIERARCHY);
		LOG.info("Classify in: " + (System.currentTimeMillis() - beg) / 1000 + " secs");
	}

	public Set<OWLClass> getEquivalentClasses(OWLClass clazz) {
		Set<OWLClass> equivalentClasses = reasoner.getEquivalentClasses(clazz).getEntities().stream() //
				.filter(x -> !x.isTopEntity()) //
				.filter(x -> !x.isBottomEntity()) //
				.collect(Collectors.toSet());
		return equivalentClasses;
	}

	public Set<Long> getEquivalentClasses(long id) {
		OWLClass clazz = getOwlClass(id);
		return getEquivalentClasses(clazz).stream().map(SnomedOwlOntology::getId).collect(Collectors.toSet());
	}

	public Set<Long> getSuperClasses(long id) {
		OWLClass clazz = getOwlClass(id);
		return getSuperClasses(clazz).stream().map(SnomedOwlOntology::getId).collect(Collectors.toSet());
	}

	public Set<OWLClass> getSuperClasses(OWLClass clazz) {
		Set<OWLClass> sups = reasoner.getSuperClasses(clazz, true).getFlattened().stream() //
				.filter(x -> !x.isTopEntity()) //
				.collect(Collectors.toSet());
		return sups;
	}

	public HashMap<Long, Set<Long>> getSuperClasses() {
		HashMap<Long, Set<Long>> superClasses = new HashMap<>();
		for (OWLClass concept : ontology.getClassesInSignature()) {
			long id = SnomedOwlOntology.getId(concept);
			superClasses.put(id, getSuperClasses(id));
		}
		return superClasses;
	}

	public Set<Long> getSubClasses(long id) {
		OWLClass clazz = getOwlClass(id);
		return getSubClasses(clazz).stream().map(SnomedOwlOntology::getId).collect(Collectors.toSet());
	}

	public Set<OWLClass> getSubClasses(OWLClass clazz) {
		Set<OWLClass> sups = reasoner.getSubClasses(clazz, true).getFlattened().stream() //
				.filter(x -> !x.isBottomEntity()) //
				.collect(Collectors.toSet());
		return sups;
	}

	public Set<Long> getSuperObjectProperties(long id, boolean direct) {
		OWLObjectProperty prop = getOwlObjectProperty(id);
		return getSuperObjectProperties(prop, direct).stream().map(SnomedOwlOntology::getId)
				.collect(Collectors.toSet());
	}

	public Set<OWLObjectProperty> getSuperObjectProperties(OWLObjectProperty prop, boolean direct) {
		// TODO Why does this this cause incompleteness warnings
		Set<OWLObjectProperty> sups = reasoner.getSuperObjectProperties(prop, direct).getFlattened().stream()
				.filter(x -> !x.isOWLTopObjectProperty()) //
				.map(x -> x.asOWLObjectProperty()) //
				.collect(Collectors.toSet());
		return sups;
	}

	public HashMap<Long, Set<Long>> getSuperObjectProperties(boolean direct) {
		HashMap<Long, Set<Long>> superObjectProperties = new HashMap<>();
		for (OWLObjectProperty prop : ontology.getObjectPropertiesInSignature()) {
			superObjectProperties.put(getId(prop), getSuperObjectProperties(getId(prop), direct));
		}
		return superObjectProperties;
	}

}
