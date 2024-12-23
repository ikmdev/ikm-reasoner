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
open module dev.ikm.elk.snomed.owlel {

	requires org.slf4j;
	requires dev.ikm.jpms.antlr4.runtime;

	exports dev.ikm.elk.snomed.owlel;
	exports dev.ikm.elk.snomed.owlel.model;
	exports dev.ikm.elk.snomed.owlel.parser;

}