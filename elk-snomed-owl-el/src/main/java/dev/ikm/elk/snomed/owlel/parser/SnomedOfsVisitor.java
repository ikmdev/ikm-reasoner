package dev.ikm.elk.snomed.owlel.parser;

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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.owlel.OwlElObjectFactory;
import dev.ikm.elk.snomed.owlel.model.IriString;
import dev.ikm.elk.snomed.owlel.model.OwlElAnnotationProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElClassExpression;
import dev.ikm.elk.snomed.owlel.model.OwlElDataHasValue;
import dev.ikm.elk.snomed.owlel.model.OwlElDataPropertyExpression;
import dev.ikm.elk.snomed.owlel.model.OwlElEquivalentClasses;
import dev.ikm.elk.snomed.owlel.model.OwlElObject;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectIntersectionOf;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectPropertyChain;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectPropertyExpression;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectSomeValuesFrom;
import dev.ikm.elk.snomed.owlel.model.OwlElOntologyDeclaration;
import dev.ikm.elk.snomed.owlel.model.OwlElPrefixDeclaration;
import dev.ikm.elk.snomed.owlel.model.OwlElReflexiveObjectProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElSubAnnotationPropertyOf;
import dev.ikm.elk.snomed.owlel.model.OwlElSubClassOf;
import dev.ikm.elk.snomed.owlel.model.OwlElSubDataPropertyOf;
import dev.ikm.elk.snomed.owlel.model.OwlElSubObjectPropertyOf;
import dev.ikm.elk.snomed.owlel.model.OwlElTransitiveObjectProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElTypedLiteral;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.AbbreviatedIRIContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.AnnotationPropertyContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.ClassContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.DataHasValueContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.DataPropertyContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.EquivalentClassesContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.FullIRIContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.IriContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.ObjectIntersectionOfContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.ObjectPropertyContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.ObjectSomeValuesFromContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.OntologyDeclarationContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.PrefixDeclarationContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.PropertyExpressionChainContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.ReflexiveObjectPropertyContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.SubAnnotationPropertyOfContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.SubClassOfContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.SubDataPropertyOfContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.SubObjectPropertyOfContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.TransitiveObjectPropertyContext;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.TypedLiteralContext;

public class SnomedOfsVisitor extends SnomedOfsGrammarBaseVisitor<OwlElObject> {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOfsVisitor.class);

	private final static boolean log_visit = false;

	private OwlElObjectFactory factory;

	public SnomedOfsVisitor(OwlElObjectFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public OwlElObject visitPrefixDeclaration(PrefixDeclarationContext context) {
		if (log_visit)
			LOG.info("visitPrefixDeclaration: " + context.getText());
		String prefix_name = context.prefixName().getText();
		IriString iri = (IriString) context.fullIRI().accept(this);
		return new OwlElPrefixDeclaration(prefix_name, iri.getIri());
	}

	@Override
	public OwlElObject visitOntologyDeclaration(OntologyDeclarationContext context) {
		if (log_visit)
			LOG.info("visitOntologyDeclaration: " + context.getText());
		IriString iri = (IriString) context.iri().accept(this);
		return new OwlElOntologyDeclaration(iri.getIri());
	}

	@Override
	public OwlElObject visitObjectIntersectionOf(ObjectIntersectionOfContext context) {
		if (log_visit)
			LOG.info("visitObjectIntersectionOf: " + context.getText());
		List<OwlElClassExpression> exprs = context.classExpression().stream()
				.map(class_expr_context -> (OwlElClassExpression) class_expr_context.accept(this)).toList();
		return new OwlElObjectIntersectionOf(exprs);
	}

	@Override
	public OwlElObject visitObjectSomeValuesFrom(ObjectSomeValuesFromContext context) {
		if (log_visit)
			LOG.info("visitObjectSomeValuesFrom: " + context.getText());
		OwlElObjectPropertyExpression prop_expr = (OwlElObjectPropertyExpression) context.objectPropertyExpression()
				.accept(this);
		OwlElClassExpression class_expr = (OwlElClassExpression) context.classExpression().accept(this);
		return new OwlElObjectSomeValuesFrom(prop_expr, class_expr);
	}

	@Override
	public OwlElObject visitDataHasValue(DataHasValueContext context) {
		if (log_visit)
			LOG.info("visitDataHasValue: " + context.getText());
		OwlElDataPropertyExpression prop_expr = (OwlElDataPropertyExpression) context.dataPropertyExpression()
				.accept(this);
		OwlElTypedLiteral class_expr = (OwlElTypedLiteral) context.literal().accept(this);
		return new OwlElDataHasValue(prop_expr, class_expr);
	}

	@Override
	public OwlElObject visitSubClassOf(SubClassOfContext context) {
		if (log_visit)
			LOG.info("visitSubClassOf: " + context.getText());
		OwlElClassExpression expr1 = (OwlElClassExpression) context.classExpression(0).accept(this);
		OwlElClassExpression expr2 = (OwlElClassExpression) context.classExpression(1).accept(this);
		return new OwlElSubClassOf(expr1, expr2);
	}

	@Override
	public OwlElObject visitEquivalentClasses(EquivalentClassesContext context) {
		if (log_visit)
			LOG.info("visitEquivalentClasses: " + context.getText());
		OwlElClassExpression expr1 = (OwlElClassExpression) context.classExpression(0).accept(this);
		OwlElClassExpression expr2 = (OwlElClassExpression) context.classExpression(1).accept(this);
		return new OwlElEquivalentClasses(expr1, expr2);
	}

	@Override
	public OwlElObject visitSubObjectPropertyOf(SubObjectPropertyOfContext context) {
		if (log_visit)
			LOG.info("visitSubObjectPropertyOf: " + context.getText());
		OwlElObjectPropertyExpression expr1 = (OwlElObjectPropertyExpression) context.subObjectPropertyExpression()
				.accept(this);
		OwlElObjectPropertyExpression expr2 = (OwlElObjectPropertyExpression) context.objectPropertyExpression()
				.accept(this);
		return new OwlElSubObjectPropertyOf(expr1, expr2);
	}

	@Override
	public OwlElObject visitPropertyExpressionChain(PropertyExpressionChainContext context) {
		if (log_visit)
			LOG.info("visitPropertyExpressionChain: " + context.getText());
		OwlElObjectPropertyExpression expr1 = (OwlElObjectPropertyExpression) context.objectPropertyExpression(0)
				.accept(this);
		OwlElObjectPropertyExpression expr2 = (OwlElObjectPropertyExpression) context.objectPropertyExpression(1)
				.accept(this);
		return new OwlElObjectPropertyChain(expr1, expr2);
	}

	@Override
	public OwlElObject visitReflexiveObjectProperty(ReflexiveObjectPropertyContext context) {
		if (log_visit)
			LOG.info("visitReflexiveObjectProperty: " + context.getText());
		OwlElObjectPropertyExpression expr1 = (OwlElObjectPropertyExpression) context.objectPropertyExpression()
				.accept(this);
		return new OwlElReflexiveObjectProperty(expr1);
	}

	@Override
	public OwlElObject visitTransitiveObjectProperty(TransitiveObjectPropertyContext context) {
		if (log_visit)
			LOG.info("visitTransitiveObjectProperty: " + context.getText());
		OwlElObjectPropertyExpression expr1 = (OwlElObjectPropertyExpression) context.objectPropertyExpression()
				.accept(this);
		return new OwlElTransitiveObjectProperty(expr1);
	}

	@Override
	public OwlElObject visitSubDataPropertyOf(SubDataPropertyOfContext context) {
		if (log_visit)
			LOG.info("visitSubDataPropertyOf: " + context.getText());
		OwlElDataPropertyExpression expr1 = (OwlElDataPropertyExpression) context.dataPropertyExpression(0)
				.accept(this);
		OwlElDataPropertyExpression expr2 = (OwlElDataPropertyExpression) context.dataPropertyExpression(1)
				.accept(this);
		return new OwlElSubDataPropertyOf(expr1, expr2);
	}

	@Override
	public OwlElObject visitSubAnnotationPropertyOf(SubAnnotationPropertyOfContext context) {
		if (log_visit)
			LOG.info("visitSubAnnotationPropertyOf: " + context.getText());
		OwlElAnnotationProperty expr1 = (OwlElAnnotationProperty) context.annotationProperty(0).accept(this);
		OwlElAnnotationProperty expr2 = (OwlElAnnotationProperty) context.annotationProperty(1).accept(this);
		return new OwlElSubAnnotationPropertyOf(expr1, expr2);
	}

	@Override
	public OwlElObject visitClass(ClassContext context) {
		if (log_visit)
			LOG.info("visitClass: " + context.getText());
		IriString iri = (IriString) context.iri().accept(this);
		return factory.getOwlElClass(iri.getIri());
	}

	@Override
	public OwlElObject visitObjectProperty(ObjectPropertyContext context) {
		if (log_visit)
			LOG.info("visitObjectProperty: " + context.getText());
		IriString iri = (IriString) context.iri().accept(this);
		return factory.getOwlElObjectProperty(iri.getIri());
	}

	@Override
	public OwlElObject visitDataProperty(DataPropertyContext context) {
		if (log_visit)
			LOG.info("visitDataProperty: " + context.getText());
		IriString iri = (IriString) context.iri().accept(this);
		return factory.getOwlElDataProperty(iri.getIri());
	}

	@Override
	public OwlElObject visitAnnotationProperty(AnnotationPropertyContext context) {
		if (log_visit)
			LOG.info("visitAnnotationProperty: " + context.getText());
		IriString iri = (IriString) context.iri().accept(this);
		return new OwlElAnnotationProperty(iri.getIri());
	}

	@Override
	public OwlElObject visitTypedLiteral(TypedLiteralContext context) {
		if (log_visit)
			LOG.info("visitTypedLiteral: " + context.getText());
		String lexical_form = context.lexicalForm().getText();
		String datatype = context.datatype().getText();
		return new OwlElTypedLiteral(lexical_form, datatype);
	}

	@Override
	public OwlElObject visitFullIRI(FullIRIContext context) {
		if (log_visit)
			LOG.info("visitFullIRI: " + context.getText());
		String str = context.fullIRI_IRI().getText();
		return new IriString(str);
	}

	@Override
	public OwlElObject visitAbbreviatedIRI(AbbreviatedIRIContext context) {
		if (log_visit)
			LOG.info("visitAbbreviatedIRI: " + context.getText());
		return new IriString(context.getText());
	}

	@Override
	public OwlElObject visitIri(IriContext context) {
		if (log_visit)
			LOG.info("visitIri: " + context.getText());
		return super.visitChildren(context);
	}

}
