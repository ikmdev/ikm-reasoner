package dev.ikm.elk.snomed.owlel.model;

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

public class OwlElTypedLiteral extends OwlElObject {

	private String lexicalForm;

	private String datatype;

	public String getLiteral() {
		return lexicalForm;
	}

	public String getDatatype() {
		return datatype;
	}

	public OwlElTypedLiteral(String lexicalForm, String datatype) {
		super();
		this.lexicalForm = lexicalForm.replaceFirst("^\"", "").replaceFirst("\"$", "");
		this.datatype = datatype;
	}

	@Override
	public String toString() {
		return "\"" + lexicalForm + "\"" + "^^" + datatype;
	}

}
