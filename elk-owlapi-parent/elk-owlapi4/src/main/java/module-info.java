/*-
 * #%L
 * ELK OWL API v.4 Binding
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2023 Department of Computer Science, University of Oxford
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
open module org.semanticweb.elk.owlapi {

//	requires junit;
	
	requires com.google.common;

	requires org.slf4j;

	requires org.liveontologies.puli;

	requires org.semanticweb.owlapi;
	requires org.semanticweb.owlapi.impl;
	requires org.semanticweb.owlapi.apibinding;

	requires org.semanticweb.elk.owl.model;
	requires org.semanticweb.elk.proofs;
	requires org.semanticweb.elk.util.common;
	requires org.semanticweb.elk.util.concurrent;
	requires org.semanticweb.elk.util.io;
	requires org.semanticweb.elk.util.logging;
//	requires org.semanticweb.elk.util.testing;

	exports org.semanticweb.elk.owlapi;
	exports org.semanticweb.elk.owlapi.proofs;

}
