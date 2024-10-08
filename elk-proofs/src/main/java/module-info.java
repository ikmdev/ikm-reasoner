/*-
 * #%L
 * ELK Proofs Package
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
open module org.semanticweb.elk.proofs {

	requires dev.ikm.jpms.google.common;

	requires org.slf4j;

	requires org.liveontologies.puli;

	requires transitive org.semanticweb.elk.owl.implementation;
	requires transitive org.semanticweb.elk.owl.model;
	requires transitive org.semanticweb.elk.reasoner;

	requires org.semanticweb.elk.util.collections;
	requires transitive org.semanticweb.elk.util.common;
	requires org.semanticweb.elk.util.hashing;
	requires org.semanticweb.elk.util.io;

	exports org.semanticweb.elk.owl.inferences;
	exports org.semanticweb.elk.proofs;

}
