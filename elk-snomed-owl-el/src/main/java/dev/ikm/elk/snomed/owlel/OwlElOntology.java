package dev.ikm.elk.snomed.owlel;

/*-
 * #%L
 * SNOMED OWL EL Profile Model and Parser
 * %%
 * Copyright (C) 2023 - 2024 Integrated Knowledge Management
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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import dev.ikm.elk.snomed.owlel.model.OwlElAxiom;
import dev.ikm.elk.snomed.owlel.model.OwlElClass;
import dev.ikm.elk.snomed.owlel.model.OwlElDataProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElObject;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElOntologyDeclaration;
import dev.ikm.elk.snomed.owlel.model.OwlElPrefixDeclaration;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsParser;

public class OwlElOntology {

	private OwlElObjectFactory factory = new OwlElObjectFactory();

	private String iri = "Undefined";

	private ArrayList<OwlElAxiom> axioms = new ArrayList<>();

	public List<OwlElClass> getClasses() {
		return factory.getClasses();
	}

	public List<OwlElDataProperty> getDataProperties() {
		return factory.getDataProperties();
	}

	public List<OwlElObjectProperty> getObjectProperties() {
		return factory.getObjectProperties();
	}

	public String getIri() {
		return iri;
	}

	public ArrayList<OwlElAxiom> getAxioms() {
		return axioms;
	}

	public <T extends OwlElAxiom> List<T> getAxioms(Class<T> clazz) {
		return axioms.stream().filter(clazz::isInstance).map(clazz::cast).toList();
	}

	public void load(Path file) throws IOException {
		for (String expr : SnomedOwlExpressions.read(file)) {
			SnomedOfsParser parser = new SnomedOfsParser(factory);
			OwlElObject obj = parser.buildExpression(expr);
			switch (obj) {
			case OwlElOntologyDeclaration x -> iri = x.getIri();
			case OwlElPrefixDeclaration x -> {
			}
			case OwlElAxiom x -> axioms.add(x);
			default -> throw new IllegalArgumentException("Unexpected value: " + obj);
			}
		}
	}

}
