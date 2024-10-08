/*-
 * #%L
 * ELK Reasoner Core
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
open module org.semanticweb.elk.reasoner {

	requires dev.ikm.jpms.google.common;

	requires org.slf4j;

	requires org.semanticweb.elk.owl.implementation;
	requires org.semanticweb.elk.owl.model;

	requires org.semanticweb.elk.util.collections;
	requires org.semanticweb.elk.util.common;
	requires org.semanticweb.elk.util.concurrent;
	requires org.semanticweb.elk.util.hashing;
	requires org.semanticweb.elk.util.io;
	requires org.semanticweb.elk.util.logging;

	exports org.semanticweb.elk.loading;
	exports org.semanticweb.elk.reasoner;
	exports org.semanticweb.elk.reasoner.completeness;
	exports org.semanticweb.elk.reasoner.config;
	exports org.semanticweb.elk.reasoner.entailments.model;
	exports org.semanticweb.elk.reasoner.indexing.classes;
	exports org.semanticweb.elk.reasoner.indexing.model;
	exports org.semanticweb.elk.reasoner.query;
	exports org.semanticweb.elk.reasoner.proof;
	exports org.semanticweb.elk.reasoner.saturation.conclusions.model;
	exports org.semanticweb.elk.reasoner.saturation.inferences;
	exports org.semanticweb.elk.reasoner.saturation.properties.inferences;
	exports org.semanticweb.elk.reasoner.stages;
	exports org.semanticweb.elk.reasoner.taxonomy.model;
	exports org.semanticweb.elk.reasoner.tracing;

}
