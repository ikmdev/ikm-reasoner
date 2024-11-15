grammar SnomedOfsGrammar;

// Grammar rules for the Snomed Owl Functional Syntax (OFS)

// https://www.w3.org/TR/owl2-syntax/

// Rule definitions

ontologyExpression : prefixDeclaration | ontologyDeclaration | axiom ;

// 3.7 https://www.w3.org/TR/owl2-syntax/#Functional-Style_Syntax
prefixDeclaration : 'Prefix' '(' prefixName '=' fullIRI ')' ;
ontologyDeclaration : 'Ontology' '(' iri ')' ;

// 8 - https://www.w3.org/TR/owl2-syntax/#Class_Expressions
classExpression : class | objectIntersectionOf | objectSomeValuesFrom | dataHasValue ;

// 8.1.1 - https://www.w3.org/TR/owl2-syntax/#Intersection_of_Class_Expressions
objectIntersectionOf : 'ObjectIntersectionOf' '(' classExpression classExpression+ ')' ;

// 8.2.1 - https://www.w3.org/TR/owl2-syntax/#Existential_Quantification
objectSomeValuesFrom : 'ObjectSomeValuesFrom' '(' objectPropertyExpression classExpression ')' ;

// 8.4.3 - https://www.w3.org/TR/owl2-syntax/#Literal_Value_Restriction
dataHasValue : 'DataHasValue' '(' dataPropertyExpression literal ')' ;

// 9 - https://www.w3.org/TR/owl2-syntax/#Axioms
axiom: classAxiom | objectPropertyAxiom | dataPropertyAxiom | annotationAxiom ;

// 9.1 - https://www.w3.org/TR/owl2-syntax/#Class_Expression_Axioms
classAxiom : subClassOf | equivalentClasses ;

// 9.1.1 - https://www.w3.org/TR/owl2-syntax/#Subclass_Axioms
subClassOf : 'SubClassOf' '(' classExpression classExpression ')' ;

// 9.1.2 - https://www.w3.org/TR/owl2-syntax/#Equivalent_Classes
// Note: In general this could be two or more
equivalentClasses : 'EquivalentClasses' '(' classExpression classExpression ')' ;

// 9.2 - https://www.w3.org/TR/owl2-syntax/#Object_Property_Axioms
objectPropertyAxiom : subObjectPropertyOf | reflexiveObjectProperty | transitiveObjectProperty ;

// 9.2.1 - https://www.w3.org/TR/owl2-syntax/#Object_Subproperties
subObjectPropertyOf : 'SubObjectPropertyOf' '(' subObjectPropertyExpression objectPropertyExpression ')' ;
subObjectPropertyExpression : objectPropertyExpression | propertyExpressionChain ;
// Note: In general this could be two or more
propertyExpressionChain : 'ObjectPropertyChain' '(' objectPropertyExpression objectPropertyExpression ')' ;

// 9.2.9 - https://www.w3.org/TR/owl2-syntax/#Reflexive_Object_Properties
reflexiveObjectProperty : 'ReflexiveObjectProperty' '(' objectPropertyExpression ')' ;

// 9.2.13 - https://www.w3.org/TR/owl2-syntax/#Transitive_Object_Properties
transitiveObjectProperty : 'TransitiveObjectProperty' '(' objectPropertyExpression ')' ;

// 9.3 - https://www.w3.org/TR/owl2-syntax/#Data_Property_Axioms
dataPropertyAxiom : subDataPropertyOf ;

// 9.3.1 - https://www.w3.org/TR/owl2-syntax/#Data_Subproperties
subDataPropertyOf : 'SubDataPropertyOf' '(' dataPropertyExpression dataPropertyExpression ')' ;

// 10.2 - https://www.w3.org/TR/owl2-syntax/#Annotation_Axioms
annotationAxiom : subAnnotationPropertyOf ;

// 10.2.2 - https://www.w3.org/TR/owl2-syntax/#Annotation_Subproperties
subAnnotationPropertyOf : 'SubAnnotationPropertyOf' '(' annotationProperty annotationProperty ')' ;

// 5.1 - https://www.w3.org/TR/owl2-syntax/#Classes
class : iri ;

// 5.2 - https://www.w3.org/TR/owl2-syntax/#Datatypes
datatype : iri ;

// 5.3 - https://www.w3.org/TR/owl2-syntax/#Object_Properties
objectProperty : iri ;

// 5.4 - https://www.w3.org/TR/owl2-syntax/#Data_Properties
dataProperty : iri ;

// 5.5 - https://www.w3.org/TR/owl2-syntax/#Annotation_Properties
annotationProperty : iri ;

// 5.7 - https://www.w3.org/TR/owl2-syntax/#Literals
literal : typedLiteral ;
typedLiteral : lexicalForm '^^' datatype ;
lexicalForm : QUOTED_STRING ;

// 6.1 - https://www.w3.org/TR/owl2-syntax/#Object_Property_Expressions
objectPropertyExpression : objectProperty ;

// 6.2 -https://www.w3.org/TR/owl2-syntax/#Data_Property_Expressions
dataPropertyExpression : dataProperty ;

// 2.4 - https://www.w3.org/TR/owl2-syntax/#IRIs
// fullIRI := an IRI as defined in [RFC3987], enclosed in a pair of < (U+3C) and > (U+3E) characters
fullIRI : '<' fullIRI_IRI '>' ;
fullIRI_IRI : (CHARS_SEQ | ':' | '/' | '.' | '#')+ ;
// prefixName := a finite sequence of characters matching the PNAME_NS production of SPARQL
prefixName : CHARS_SEQ? ':' ;
// abbreviatedIRI := a finite sequence of characters matching the PNAME_LN production of SPARQL
abbreviatedIRI : prefixName CHARS_SEQ;
iri : fullIRI | abbreviatedIRI ;

// Token definitions
CHARS_SEQ : CHARS+ ;

fragment CHARS : [A-Z] | [a-z] | [0-9] | '-' | '_' ;

// https://www.w3.org/TR/2008/REC-rdf-sparql-query-20080115/#sparqlGrammar
// [71]  	PNAME_NS	  ::=  	PN_PREFIX? ':'
// [72]  	PNAME_LN	  ::=  	PNAME_NS PN_LOCAL
// [95]  	PN_CHARS_BASE	  ::=  	[A-Z] | [a-z] | ...
// [96]  	PN_CHARS_U	  ::=  	PN_CHARS_BASE | '_'
// [98]  	PN_CHARS	  ::=  	PN_CHARS_U | '-' | [0-9] | ...
// [99]  	PN_PREFIX	  ::=  	PN_CHARS_BASE ((PN_CHARS|'.')* PN_CHARS)?
// [100]  	PN_LOCAL	  ::=  	( PN_CHARS_U | [0-9] ) ((PN_CHARS|'.')* PN_CHARS)?

// https://www.w3.org/TR/owl2-syntax/#Integers.2C_Characters.2C_Strings.2C_Language_Tags.2C_and_Node_IDs
// quotedString := a finite sequence of characters in which " and \ occur only in pairs of the form \" and \\, enclosed in a pair of " characters
// https://github.com/antlr/grammars-v4/blob/master/java/java/JavaLexer.g4
// 135 - STRING_LITERAL: '"' (~["\\\r\n] | EscapeSequence)* '"' ;
QUOTED_STRING  : '"' (~["\r\n])* '"' ;

WS : [ \r\t\n]+ -> skip ;
