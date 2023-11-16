/*-
 * #%L
 * ELK Utilities for Logging
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
open module org.semanticweb.elk.util.logging {

	requires java.management;

	requires org.slf4j;

	requires org.semanticweb.elk.util.hashing;

	exports org.semanticweb.elk.util.logging;
	exports org.semanticweb.elk.util.logging.statistics;

}
