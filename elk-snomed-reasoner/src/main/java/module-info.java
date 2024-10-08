/*-
 * #%L
 * ELK Reasoner for SNOMED
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
open module dev.ikm.elk.snomed.reasoner {

	requires dev.ikm.jpms.javax.annotation;

	requires org.slf4j;

	requires org.semanticweb.elk.owl.implementation;
	requires org.semanticweb.elk.owl.model;
	requires org.semanticweb.elk.reasoner;
	requires org.semanticweb.elk.util.common;
	requires org.semanticweb.elk.util.concurrent;
	requires org.semanticweb.elk.util.logging;

	exports dev.ikm.elk.snomed.owlapix.model;
	exports dev.ikm.elk.snomed.owlapix.reasoner;
	exports dev.ikm.elk.snomed.reasoner;

}
