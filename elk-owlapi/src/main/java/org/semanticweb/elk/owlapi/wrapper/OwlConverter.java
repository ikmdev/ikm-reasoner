/*
 * #%L
 * ELK OWL API Binding
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 Department of Computer Science, University of Oxford
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
package org.semanticweb.elk.owlapi.wrapper;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationAxiom;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationSubject;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationValue;
import org.semanticweb.elk.owl.interfaces.ElkAnonymousIndividual;
import org.semanticweb.elk.owl.interfaces.ElkAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkAsymmetricObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataAllValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkDataComplementOf;
import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinality;
import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;
import org.semanticweb.elk.owl.interfaces.ElkDataIntersectionOf;
import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinality;
import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinality;
import org.semanticweb.elk.owl.interfaces.ElkDataOneOf;
import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyDomainAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyRangeAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.interfaces.ElkDataSomeValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkDataUnionOf;
import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkDatatypeDefinitionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDatatypeRestriction;
import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDisjointDataPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDisjointObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentDataPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkFacetRestriction;
import org.semanticweb.elk.owl.interfaces.ElkFunctionalDataPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkFunctionalObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkHasKeyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkInverseObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIrreflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.interfaces.ElkNegativeDataPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectAllValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkObjectComplementOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinality;
import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;
import org.semanticweb.elk.owl.interfaces.ElkObjectHasValue;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinality;
import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinality;
import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSWRLRule;
import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubDataPropertyOfAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSymmetricObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.iris.ElkFullIri;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLDataComplementOf;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataIntersectionOf;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataOneOf;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDataUnionOf;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFacetRestriction;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasSelf;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.SWRLRule;

/**
 * 
 * @author Yevgeny Kazakov
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 */
public class OwlConverter {

	private static OwlConverter INSTANCE_ = new OwlConverter();

	private OwlConverter() {
	}

	public static OwlConverter getInstance() {
		return INSTANCE_;
	}

	protected static OwlAxiomConverterVisitor OWL_AXIOM_CONVERTER = OwlAxiomConverterVisitor
			.getInstance();

	protected static OwlAnnotationAxiomConverterVisitor OWL_ANNOTATION_AXIOM_CONVERTER = OwlAnnotationAxiomConverterVisitor
			.getInstance();

	protected static OwlClassAxiomConverterVisitor OWL_CLASS_AXIOM_CONVERTER = OwlClassAxiomConverterVisitor
			.getInstance();

	protected static OwlObjectPropertyAxiomConverterVisitor OWL_OBJECT_PROPERTY_AXIOM_CONVERTER = OwlObjectPropertyAxiomConverterVisitor
			.getInstance();

	protected static OwlDataPropertyAxiomConverterVisitor OWL_DATA_PROPERTY_AXIOM_CONVERTER = OwlDataPropertyAxiomConverterVisitor
			.getInstance();

	protected static OwlIndividualAxiomConverterVisitor OWL_INDIVIDUAL_AXIOM_CONVERTER = OwlIndividualAxiomConverterVisitor
			.getInstance();

	protected static OwlClassExpressionConverterVisitor OWL_CLASS_EXPRESSION_CONVERTER = OwlClassExpressionConverterVisitor
			.getInstance();

	protected static OwlDataRangeConverterVisitor OWL_DATA_RANGE_CONVERTER = OwlDataRangeConverterVisitor
			.getInstance();

	protected static OwlObjectPropertyExpressionConverterVisitor OWL_OBJECT_PROPERTY_EXPRESSION_CONVERTER = OwlObjectPropertyExpressionConverterVisitor
			.getInstance();

	protected static OwlIndividualConverterVisitor OWL_INDIVIDUAL_CONVERTER = OwlIndividualConverterVisitor
			.getInstance();

	protected static OwlAnnotationSubjectValueVisitor OWL_ANNOTATION_CONVERTER = OwlAnnotationSubjectValueVisitor
			.getInstance();

	protected static OwlEntityConverterVisitor OWL_ENTITY_CONVERTER = OwlEntityConverterVisitor
			.getInstance();

	public ElkAnnotationProperty convert(
			OWLAnnotationProperty owlAnnotationProperty) {
		return new ElkAnnotationPropertyWrap<OWLAnnotationProperty>(
				owlAnnotationProperty);
	}

	public ElkAnonymousIndividual convert(
			OWLAnonymousIndividual owlAnonymousIndividual) {
		return new ElkAnonymousIndividualWrap<OWLAnonymousIndividual>(
				owlAnonymousIndividual);
	}

	public ElkAnnotationAxiom convert(OWLAnnotationAxiom owlAnnotationAxiom) {
		return owlAnnotationAxiom.accept(OWL_ANNOTATION_AXIOM_CONVERTER);
	}

	public ElkAssertionAxiom convert(OWLIndividualAxiom owlIndividualAxiom) {
		return owlIndividualAxiom.accept(OWL_INDIVIDUAL_AXIOM_CONVERTER);
	}

	public ElkAsymmetricObjectPropertyAxiom convert(
			OWLAsymmetricObjectPropertyAxiom owlAsymmetricObjectPropertyAxiom) {
		return new ElkAsymmetricObjectPropertyAxiomWrap<OWLAsymmetricObjectPropertyAxiom>(
				owlAsymmetricObjectPropertyAxiom);
	}

	public ElkAxiom convert(OWLAxiom owlAxiom) {
		return owlAxiom.accept(OWL_AXIOM_CONVERTER);
	}

	public ElkClassAssertionAxiom convert(
			OWLClassAssertionAxiom owlClassAssertionAxiom) {
		return new ElkClassAssertionAxiomWrap<OWLClassAssertionAxiom>(
				owlClassAssertionAxiom);
	}

	public ElkClassAxiom convert(OWLClassAxiom owlClassAxiom) {
		return owlClassAxiom.accept(OWL_CLASS_AXIOM_CONVERTER);
	}

	public ElkClassExpression convert(OWLClassExpression owlClassExpression) {
		return owlClassExpression.accept(OWL_CLASS_EXPRESSION_CONVERTER);
	}

	public ElkClass convert(OWLClass owlClass) {
		return new ElkClassWrap<OWLClass>(owlClass);
	}

	public ElkDataAllValuesFrom convert(
			OWLDataAllValuesFrom owlDataAllValuesFrom) {
		return new ElkDataAllValuesFromWrap<OWLDataAllValuesFrom>(
				owlDataAllValuesFrom);
	}

	public ElkDataComplementOf convert(
			OWLDataComplementOf owlDataComplementOf) {
		return new ElkDataComplementOfWrap<OWLDataComplementOf>(
				owlDataComplementOf);
	}

	public ElkDataExactCardinality convert(
			OWLDataExactCardinality owlDataExactCardinality) {
		if (owlDataExactCardinality.isQualified())
			return new ElkDataExactCardinalityQualifiedWrap<OWLDataExactCardinality>(
					owlDataExactCardinality);
		// else
		return new ElkDataExactCardinalityUnqualifiedWrap<OWLDataExactCardinality>(
				owlDataExactCardinality);
	}

	public ElkDataHasValue convert(OWLDataHasValue owlDataHasValue) {
		return new ElkDataHasValueWrap<OWLDataHasValue>(owlDataHasValue);
	}

	public ElkDataIntersectionOf convert(
			OWLDataIntersectionOf owlDataIntersectionOf) {
		return new ElkDataIntersectionOfWrap<OWLDataIntersectionOf>(
				owlDataIntersectionOf);
	}

	public ElkDataMaxCardinality convert(
			OWLDataMaxCardinality owlDataMaxCardinality) {
		if (owlDataMaxCardinality.isQualified())
			return new ElkDataMaxCardinalityQualifiedWrap<OWLDataMaxCardinality>(
					owlDataMaxCardinality);
		// else
		return new ElkDataMaxCardinalityUnqualifiedWrap<OWLDataMaxCardinality>(
				owlDataMaxCardinality);
	}

	public ElkDataMinCardinality convert(
			OWLDataMinCardinality owlDataMinCardinality) {
		if (owlDataMinCardinality.isQualified())
			return new ElkDataMinCardinalityQualifiedWrap<OWLDataMinCardinality>(
					owlDataMinCardinality);
		// else
		return new ElkDataMinCardinalityUnqualifiedWrap<OWLDataMinCardinality>(
				owlDataMinCardinality);
	}

	public ElkDataOneOf convert(OWLDataOneOf owlDataOneOf) {
		return new ElkDataOneOfWrap<OWLDataOneOf>(owlDataOneOf);
	}

	public ElkDataPropertyAssertionAxiom convert(
			OWLDataPropertyAssertionAxiom owlDataPropertyAssertionAxiom) {
		return new ElkDataPropertyAssertionAxiomWrap<OWLDataPropertyAssertionAxiom>(
				owlDataPropertyAssertionAxiom);
	}

	public ElkDataPropertyAxiom convert(
			OWLDataPropertyAxiom owlDataPropertyAxiom) {
		return owlDataPropertyAxiom.accept(OWL_DATA_PROPERTY_AXIOM_CONVERTER);
	}

	public ElkDataPropertyDomainAxiom convert(
			OWLDataPropertyDomainAxiom owlDataPropertyDomainAxiom) {
		return new ElkDataPropertyDomainAxiomWrap<OWLDataPropertyDomainAxiom>(
				owlDataPropertyDomainAxiom);
	}

	public ElkDataPropertyExpression convert(
			OWLDataPropertyExpression owlDataPropertyExpression) {
		return this.convert(owlDataPropertyExpression.asOWLDataProperty());
	}

	public ElkDataPropertyRangeAxiom convert(
			OWLDataPropertyRangeAxiom owlDataPropertyRangeAxiom) {
		return new ElkDataPropertyRangeAxiomWrap<OWLDataPropertyRangeAxiom>(
				owlDataPropertyRangeAxiom);
	}

	public ElkDataProperty convert(OWLDataProperty owlDataProperty) {
		return new ElkDataPropertyWrap<OWLDataProperty>(owlDataProperty);
	}

	public ElkDataRange convert(OWLDataRange owlDataRange) {
		return owlDataRange.accept(OWL_DATA_RANGE_CONVERTER);
	}

	public ElkDataSomeValuesFrom convert(
			OWLDataSomeValuesFrom owlDataSomeValuesFrom) {
		return new ElkDataSomeValuesFromWrap<OWLDataSomeValuesFrom>(
				owlDataSomeValuesFrom);
	}

	public ElkDatatypeRestriction convert(
			OWLDatatypeRestriction owlDatatypeRestriction) {
		return new ElkDatatypeRestrictionWrap<OWLDatatypeRestriction>(
				owlDatatypeRestriction);
	}

	public ElkDatatypeDefinitionAxiom convert(
			OWLDatatypeDefinitionAxiom owlDatatypeDefinition) {
		return new ElkDatatypeDefinitionAxiomWrap<OWLDatatypeDefinitionAxiom>(
				owlDatatypeDefinition);
	}

	public ElkDatatype convert(OWLDatatype owlDatatype) {
		return new ElkDatatypeWrap<OWLDatatype>(owlDatatype);
	}

	public ElkDataUnionOf convert(OWLDataUnionOf owlDataUnionOf) {
		return new ElkDataUnionOfWrap<OWLDataUnionOf>(owlDataUnionOf);
	}

	public ElkDeclarationAxiom convert(
			OWLDeclarationAxiom owlDeclarationAxiom) {
		return new ElkDeclarationAxiomWrap<OWLDeclarationAxiom>(
				owlDeclarationAxiom);
	}

	public ElkDifferentIndividualsAxiom convert(
			OWLDifferentIndividualsAxiom owlDifferentIndividualsAxiom) {
		return new ElkDifferentIndividualsAxiomWrap<OWLDifferentIndividualsAxiom>(
				owlDifferentIndividualsAxiom);
	}

	public ElkDisjointClassesAxiom convert(
			OWLDisjointClassesAxiom owlDisjointClassesAxiom) {
		return new ElkDisjointClassesAxiomWrap<OWLDisjointClassesAxiom>(
				owlDisjointClassesAxiom);
	}

	public ElkDisjointDataPropertiesAxiom convert(
			OWLDisjointDataPropertiesAxiom owlDisjointDataPropertiesAxiom) {
		return new ElkDisjointDataPropertiesAxiomWrap<OWLDisjointDataPropertiesAxiom>(
				owlDisjointDataPropertiesAxiom);
	}

	public ElkDisjointObjectPropertiesAxiom convert(
			OWLDisjointObjectPropertiesAxiom owlDisjointObjectPropertiesAxiom) {
		return new ElkDisjointObjectPropertiesAxiomWrap<OWLDisjointObjectPropertiesAxiom>(
				owlDisjointObjectPropertiesAxiom);
	}

	public ElkDisjointUnionAxiom convert(
			OWLDisjointUnionAxiom owlDisjointUnionAxiom) {
		return new ElkDisjointUnionAxiomWrap<OWLDisjointUnionAxiom>(
				owlDisjointUnionAxiom);
	}

	public ElkEntity convert(OWLEntity owlEntity) {
		return owlEntity.accept(OWL_ENTITY_CONVERTER);
	}

	public ElkEquivalentClassesAxiom convert(
			OWLEquivalentClassesAxiom owlEquivalentClassesAxiom) {
		return new ElkEquivalentClassesAxiomWrap<OWLEquivalentClassesAxiom>(
				owlEquivalentClassesAxiom);
	}

	public ElkEquivalentDataPropertiesAxiom convert(
			OWLEquivalentDataPropertiesAxiom owlEquivalentDataPropertiesAxiom) {
		return new ElkEquivalentDataPropertiesAxiomWrap<OWLEquivalentDataPropertiesAxiom>(
				owlEquivalentDataPropertiesAxiom);
	}

	public ElkEquivalentObjectPropertiesAxiom convert(
			OWLEquivalentObjectPropertiesAxiom owlEquivalentObjectPropertiesAxiom) {
		return new ElkEquivalentObjectPropertiesAxiomWrap<OWLEquivalentObjectPropertiesAxiom>(
				owlEquivalentObjectPropertiesAxiom);
	}

	public ElkFacetRestriction convert(
			OWLFacetRestriction owlFacetRestriction) {
		return new ElkFacetRestrictionWrap<OWLFacetRestriction>(
				owlFacetRestriction);
	}

	public ElkFunctionalDataPropertyAxiom convert(
			OWLFunctionalDataPropertyAxiom owlFunctionalDataPropertyAxiom) {
		return new ElkFunctionalDataPropertyAxiomWrap<OWLFunctionalDataPropertyAxiom>(
				owlFunctionalDataPropertyAxiom);
	}

	public ElkFunctionalObjectPropertyAxiom convert(
			OWLFunctionalObjectPropertyAxiom owlFunctionalObjectPropertyAxiom) {
		return new ElkFunctionalObjectPropertyAxiomWrap<OWLFunctionalObjectPropertyAxiom>(
				owlFunctionalObjectPropertyAxiom);
	}

	public ElkIndividual convert(OWLIndividual owlIndividual) {
		return owlIndividual.accept(OWL_INDIVIDUAL_CONVERTER);
	}

	public ElkInverseFunctionalObjectPropertyAxiom convert(
			OWLInverseFunctionalObjectPropertyAxiom owlInverseFunctionalObjectPropertyAxiom) {
		return new ElkInverseFunctionalObjectPropertyAxiomWrap<OWLInverseFunctionalObjectPropertyAxiom>(
				owlInverseFunctionalObjectPropertyAxiom);
	}

	public ElkInverseObjectPropertiesAxiom convert(
			OWLInverseObjectPropertiesAxiom owlInverseObjectPropertiesAxiom) {
		return new ElkInverseObjectPropertiesAxiomWrap<OWLInverseObjectPropertiesAxiom>(
				owlInverseObjectPropertiesAxiom);
	}

	public ElkIrreflexiveObjectPropertyAxiom convert(
			OWLIrreflexiveObjectPropertyAxiom owlIrreflexiveObjectPropertyAxiom) {
		return new ElkIrreflexiveObjectPropertyAxiomWrap<OWLIrreflexiveObjectPropertyAxiom>(
				owlIrreflexiveObjectPropertyAxiom);
	}

	public ElkLiteral convert(OWLLiteral owlLiteral) {
		return new ElkLiteralWrap<OWLLiteral>(owlLiteral);
	}

	public ElkNamedIndividual convert(OWLNamedIndividual owlNamedIndividual) {
		return new ElkNamedIndividualWrap<OWLNamedIndividual>(
				owlNamedIndividual);
	}

	public ElkNegativeDataPropertyAssertionAxiom convert(
			OWLNegativeDataPropertyAssertionAxiom owlNegativeDataPropertyAssertionAxiom) {
		return new ElkNegativeDataPropertyAssertionAxiomWrap<OWLNegativeDataPropertyAssertionAxiom>(
				owlNegativeDataPropertyAssertionAxiom);
	}

	public ElkNegativeObjectPropertyAssertionAxiom convert(
			OWLNegativeObjectPropertyAssertionAxiom owlNegativeObjectPropertyAssertionAxiom) {
		return new ElkNegativeObjectPropertyAssertionAxiomWrap<OWLNegativeObjectPropertyAssertionAxiom>(
				owlNegativeObjectPropertyAssertionAxiom);
	}

	public ElkObjectAllValuesFrom convert(
			OWLObjectAllValuesFrom owlObjectAllValuesFrom) {
		return new ElkObjectAllValuesFromWrap<OWLObjectAllValuesFrom>(
				owlObjectAllValuesFrom);
	}

	public ElkObjectComplementOf convert(
			OWLObjectComplementOf owlObjectComplementOf) {
		return new ElkObjectComplementOfWrap<OWLObjectComplementOf>(
				owlObjectComplementOf);
	}

	public ElkObjectExactCardinality convert(
			OWLObjectExactCardinality owlObjectExactCardinality) {
		if (owlObjectExactCardinality.isQualified())
			return new ElkObjectExactCardinalityQualifiedWrap<OWLObjectExactCardinality>(
					owlObjectExactCardinality);
		// else
		return new ElkObjectExactCardinalityUnqualifiedWrap<OWLObjectExactCardinality>(
				owlObjectExactCardinality);
	}

	public ElkObjectHasSelf convert(OWLObjectHasSelf owlObjectHasSelf) {
		return new ElkObjectHasSelfWrap<OWLObjectHasSelf>(owlObjectHasSelf);
	}

	public ElkObjectHasValue convert(OWLObjectHasValue owlObjectHasValue) {
		return new ElkObjectHasValueWrap<OWLObjectHasValue>(owlObjectHasValue);
	}

	public ElkObjectIntersectionOf convert(
			OWLObjectIntersectionOf owlObjectIntersectionOf) {
		return new ElkObjectIntersectionOfWrap<OWLObjectIntersectionOf>(
				owlObjectIntersectionOf);
	}

	public ElkObjectMaxCardinality convert(
			OWLObjectMaxCardinality owlObjectMaxCardinality) {
		if (owlObjectMaxCardinality.isQualified())
			return new ElkObjectMaxCardinalityQualifiedWrap<OWLObjectMaxCardinality>(
					owlObjectMaxCardinality);
		// else
		return new ElkObjectMaxCardinalityUnqualifiedWrap<OWLObjectMaxCardinality>(
				owlObjectMaxCardinality);
	}

	public ElkObjectMinCardinality convert(
			OWLObjectMinCardinality owlObjectMaxCardinality) {
		if (owlObjectMaxCardinality.isQualified())
			return new ElkObjectMinCardinalityQualifiedWrap<OWLObjectMinCardinality>(
					owlObjectMaxCardinality);
		// else
		return new ElkObjectMinCardinalityUnqualifiedWrap<OWLObjectMinCardinality>(
				owlObjectMaxCardinality);
	}

	public ElkObjectOneOf convert(OWLObjectOneOf owlObjectOneOf) {
		return new ElkObjectOneOfWrap<OWLObjectOneOf>(owlObjectOneOf);
	}

	public ElkObjectPropertyAssertionAxiom convert(
			OWLObjectPropertyAssertionAxiom owlObjectPropertyAssertionAxiom) {
		return new ElkObjectPropertyAssertionAxiomWrap<OWLObjectPropertyAssertionAxiom>(
				owlObjectPropertyAssertionAxiom);
	}

	public ElkObjectPropertyAxiom convert(
			OWLObjectPropertyAxiom owlObjectPropertyAxiom) {
		return owlObjectPropertyAxiom
				.accept(OWL_OBJECT_PROPERTY_AXIOM_CONVERTER);
	}

	public ElkObjectPropertyChain convert(
			OWLSubPropertyChainOfAxiom owlSubPropertyChainOfAxiom) {
		return new ElkObjectPropertyChainWrap<List<? extends OWLObjectPropertyExpression>>(
				owlSubPropertyChainOfAxiom.getPropertyChain());
	}

	public ElkObjectPropertyDomainAxiom convert(
			OWLObjectPropertyDomainAxiom owlObjectPropertyDomainAxiom) {
		return new ElkObjectPropertyDomainAxiomWrap<OWLObjectPropertyDomainAxiom>(
				owlObjectPropertyDomainAxiom);
	}

	public ElkObjectPropertyExpression convert(
			OWLObjectPropertyExpression objectPropertyExpression) {
		return objectPropertyExpression
				.accept(OWL_OBJECT_PROPERTY_EXPRESSION_CONVERTER);
	}

	public ElkObjectPropertyRangeAxiom convert(
			OWLObjectPropertyRangeAxiom owlObjectPropertyRangeAxiom) {
		return new ElkObjectPropertyRangeAxiomWrap<OWLObjectPropertyRangeAxiom>(
				owlObjectPropertyRangeAxiom);
	}

	public ElkObjectProperty convert(OWLObjectProperty owlObjectProperty) {
		return new ElkObjectPropertyWrap<OWLObjectProperty>(owlObjectProperty);
	}

	public ElkObjectSomeValuesFrom convert(
			OWLObjectSomeValuesFrom owlObjectSomeValuesFrom) {
		return new ElkObjectSomeValuesFromWrap<OWLObjectSomeValuesFrom>(
				owlObjectSomeValuesFrom);
	}

	public ElkObjectUnionOf convert(OWLObjectUnionOf owlObjectUnionOf) {
		return new ElkObjectUnionOfWrap<OWLObjectUnionOf>(owlObjectUnionOf);
	}

	public ElkReflexiveObjectPropertyAxiom convert(
			OWLReflexiveObjectPropertyAxiom owlReflexiveObjectPropertyAxiom) {
		return new ElkReflexiveObjectPropertyAxiomWrap<OWLReflexiveObjectPropertyAxiom>(
				owlReflexiveObjectPropertyAxiom);
	}

	public ElkSameIndividualAxiom convert(
			OWLSameIndividualAxiom owlSameIndividualAxiom) {
		return new ElkSameIndividualAxiomWrap<OWLSameIndividualAxiom>(
				owlSameIndividualAxiom);
	}

	public ElkSubClassOfAxiom convert(OWLSubClassOfAxiom owlSubClassOfAxiom) {
		return new ElkSubClassOfAxiomWrap<OWLSubClassOfAxiom>(
				owlSubClassOfAxiom);
	}

	public ElkSubDataPropertyOfAxiom convert(
			OWLSubDataPropertyOfAxiom owlSubDataPropertyOfAxiom) {
		return new ElkSubDataPropertyOfAxiomWrap<OWLSubDataPropertyOfAxiom>(
				owlSubDataPropertyOfAxiom);
	}

	public ElkSubObjectPropertyOfAxiom convert(
			OWLSubObjectPropertyOfAxiom owlSubObjectPropertyOfAxiom) {
		return new ElkSubObjectPropertyOfAxiomWrap<OWLSubObjectPropertyOfAxiom>(
				owlSubObjectPropertyOfAxiom);
	}

	public ElkSymmetricObjectPropertyAxiom convert(
			OWLSymmetricObjectPropertyAxiom owlSymmetricObjectPropertyAxiom) {
		return new ElkSymmetricObjectPropertyAxiomWrap<OWLSymmetricObjectPropertyAxiom>(
				owlSymmetricObjectPropertyAxiom);
	}

	public ElkTransitiveObjectPropertyAxiom convert(
			OWLTransitiveObjectPropertyAxiom owlTransitiveObjectPropertyAxiom) {
		return new ElkTransitiveObjectPropertyAxiomWrap<OWLTransitiveObjectPropertyAxiom>(
				owlTransitiveObjectPropertyAxiom);
	}

	public ElkHasKeyAxiom convert(OWLHasKeyAxiom owlHasKey) {
		return new ElkHasKeyAxiomWrap<OWLHasKeyAxiom>(owlHasKey);
	}

	public ElkAnnotationSubject convert(OWLAnnotationSubject subject) {
		return OWL_ANNOTATION_CONVERTER.visit(subject);
	}

	public ElkAnnotationValue convert(OWLAnnotationValue value) {
		return OWL_ANNOTATION_CONVERTER.visit(value);
	}

	public ElkIri convert(IRI iri) {
		return new ElkFullIri(iri.toString());
	}

	/**
	 * @param axiom
	 *            the owl axiom to test
	 * @return {@code true} if the owl axiom can be converted to ELK axiom
	 */
	public boolean isRelevantAxiom(OWLAxiom axiom) {
		return axiom.isLogicalAxiom() || axiom.isOfType(AxiomType.DECLARATION);
	}

	public ElkSWRLRule convert(SWRLRule rule) {
		return new ElkSWRLRuleWrap<SWRLRule>(rule);
	}

	public Set<? extends ElkAxiom> convertAxiomSet(
			final Set<? extends OWLAxiom> owlAxioms) {
		final Set<ElkAxiom> result = new HashSet<ElkAxiom>(owlAxioms.size());
		for (final OWLAxiom owlAxiom : owlAxioms) {
			result.add(convert(owlAxiom));
		}
		return result;
	}

	/**
	 * @param owlClass
	 *            the owl object class to be converted
	 * @return the elk object class corresponding to the given owl object class,
	 *         or {@code null} if the input class cannot be converted
	 */
	public static Class<? extends ElkObject> convertType(
			final Class<?> owlClass) {

		if (!OWLObject.class.isAssignableFrom(owlClass)) {
			return null;
		}

		for (final Method declaredMethod : OwlConverter.class
				.getDeclaredMethods()) {

			final int mod = declaredMethod.getModifiers();
			final Class<?>[] parameterTypes = declaredMethod
					.getParameterTypes();
			final Class<?> returnType = declaredMethod.getReturnType();
			if ("convert".equals(declaredMethod.getName())
					&& Modifier.isPublic(mod) && !Modifier.isStatic(mod)
					&& parameterTypes.length == 1
					&& parameterTypes[0].equals(owlClass)
					&& ElkObject.class.isAssignableFrom(returnType)) {
				return returnType.asSubclass(ElkObject.class);
			}

		}

		return null;
	}

}
