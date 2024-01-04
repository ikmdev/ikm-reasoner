
package org.semanticweb.elk.owl.predefined;



/**
 * Corresponds to the types <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Entity_Declarations_and_Typing" >Entity
 * Declaration Axioms<a> in the OWL 2 specification.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public enum ElkEntityType {

	CLASS("Class"),

	DATATYPE("Datatype"),

	OBJECT_PROPERTY("ObjectProperty"),

	DATA_PROPERTY("DataProperty"),

	ANNOTATION_PROPERTY("AnnotationProperty"),

	NAMED_INDIVIDUAL("NamedIndividual");

	private String name_;

	ElkEntityType(String name) {
		this.name_ = name;
	}

	@Override
	public String toString() {
		return this.name_;
	}

}
