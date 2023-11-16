package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration Testing with SNOMED
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
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.owlapi.OWLAPIConfigProvider;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.functional.parser.OWLFunctionalSyntaxOWLParser;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

public class SnomedOwlOntology {

	// 138875005 |SNOMED CT Concept (SNOMED RT+CTV3)|
	public static final long root = 138875005;

	// 609096000 |Role group (attribute)|
	public static final long role_group = 609096000;

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
		return Files.lines(file).skip(1).map(line -> line.split("\\t")) //
				.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
				.map(fields -> fields[6]) //
				.collect(Collectors.toList());
	}

	public static List<String> getPrefixDeclaration(List<String> lines) {
		return lines.stream().filter(line -> line.startsWith("Prefix")).collect(Collectors.toList());
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

	public static long getId(OWLClass clazz) {
		return Long.parseLong(clazz.getIRI().getShortForm());
	}

	public OWLClass getOwlClass(long id) {
		return classMap.get(id);
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

	public Set<OWLObjectPropertyAxiom> getAxioms(OWLObjectProperty prop) {
		return ontology.getAxioms(prop, Imports.EXCLUDED);
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
		OWLReasonerFactory rf = (OWLReasonerFactory) new ElkReasonerFactory();
		reasoner = rf.createReasoner(ontology);
		reasoner.flush();
		reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
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

}
