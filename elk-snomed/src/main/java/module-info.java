/*-
 * #%L
 * ELK Integration Testing with SNOMED
 * %%
 * Copyright (C) 2023 US FDA SHIELD Program
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
open module dev.ikm.elk.snomed {

	requires org.slf4j;

	requires org.semanticweb.owlapi;
	requires org.semanticweb.owlapi.impl;
	requires org.semanticweb.owlapi.apibinding;
	requires org.semanticweb.owlapi.parsers;

	requires org.semanticweb.elk.owlapi;

	exports dev.ikm.elk.snomed;

}