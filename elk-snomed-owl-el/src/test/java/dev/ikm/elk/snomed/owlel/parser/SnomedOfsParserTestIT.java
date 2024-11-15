package dev.ikm.elk.snomed.owlel.parser;

/*-
 * #%L
 * elk-snomed-owl-el
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.owlel.OwlElObjectFactory;
import dev.ikm.elk.snomed.owlel.SnomedOwlExpressions;
import dev.ikm.elk.snomed.owlel.model.OwlElObject;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.OntologyExpressionContext;

public class SnomedOfsParserTestIT {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOfsParserTestIT.class);

	public static Path file = Paths.get("target/data", "snomed-test-data-intl-20241001",
			"sct2_sRefset_OWLExpressionSnapshot_INT_20241001.txt");

	private List<String> getExpressions(String regex) throws IOException {
		return SnomedOwlExpressions.read(file).stream().filter(expr -> expr.matches(regex)).toList();
	}

	public int parse(List<String> exprs) throws Exception {
		return parse(exprs, false);
	}

	public int parse(List<String> exprs, boolean log_parse) throws Exception {
		int miss_cnt = 0;
		for (String expr : exprs) {
			SnomedOfsParser parser = new SnomedOfsParser(new OwlElObjectFactory());
			OntologyExpressionContext context = parser.parseStatement(expr);
			OwlElObject obj = parser.buildExpression(context);
			if (log_parse) {
				LOG.info("Stmt: " + expr);
				LOG.info("      " + parser.toStringTree(context));
				LOG.info("      " + parser.buildExpression(context));
			}
			if (obj == null || !expr.equals(obj.toString())) {
				LOG.error("Expect: " + expr);
				LOG.error("Actual: " + obj);
				miss_cnt++;
			}
			assertNull(context.exception);
			assertNull(parser.getSyntaxError());
		}
		return miss_cnt;
	}

	@Test
	public void syntaxError() throws Exception {
		String expr = "SubClassOf )";
		SnomedOfsParser parser = new SnomedOfsParser(new OwlElObjectFactory());
		OntologyExpressionContext context = parser.parseStatement(expr);
		assertNotNull(parser.getSyntaxError());
		LOG.info("Stmt: " + expr);
		LOG.info("      " + parser.toStringTree(context));
	}

	@Test
	public void subClassOf() throws Exception {
		List<String> exprs = List.of("SubClassOf(:1 :2)");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void equivalentClasses() throws Exception {
		List<String> exprs = List.of("EquivalentClasses(:1 :2)");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void subObjectPropertyOf() throws Exception {
		List<String> exprs = List.of("SubObjectPropertyOf(:1 :2)");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void subDataPropertyOf() throws Exception {
		List<String> exprs = List.of("SubDataPropertyOf(:1 :2)");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void subAnnotationPropertyOf() throws Exception {
		List<String> exprs = List.of("SubAnnotationPropertyOf(:1 :2)");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void objectIntersectionOf() throws Exception {
		List<String> exprs = List.of("SubClassOf(:1 ObjectIntersectionOf(:2 :3))");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void objectSomeValuesFrom() throws Exception {
		List<String> exprs = List.of("SubClassOf(:1 ObjectIntersectionOf(:2 ObjectSomeValuesFrom(:3 :4)))");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void dataHasValue() throws Exception {
		List<String> exprs = List.of("SubClassOf(:1 ObjectIntersectionOf(:2 DataHasValue(:3 \"1.1\"^^xsd:decimal)))");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void allOntologyDecl() throws Exception {
		List<String> exprs = getExpressions("^Ontology.+");
		assertEquals(1, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void allPrefixDecl() throws Exception {
		List<String> exprs = getExpressions("^Prefix.+");
		assertEquals(6, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void allObjectProperty() throws Exception {
		List<String> exprs = getExpressions("^.*ObjectProperty.+");
		assertEquals(136, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void allDataProperty() throws Exception {
		List<String> exprs = getExpressions("^.*DataProperty.+");
		assertEquals(10, exprs.size());
		assertEquals(0, parse(exprs));
	}

	@Test
	public void all() throws Exception {
		List<String> exprs = getExpressions(".+");
		assertEquals(369445, exprs.size());
		assertEquals(0, parse(exprs, false));
	}

}
