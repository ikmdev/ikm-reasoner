package dev.ikm.elk.snomed.owlapix.model;

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

/**
 * A few methods from OWLOntologyManager in the OWL API
 * 
 */
public interface OWLOntologyManager {

	public void addOntologyChangeListener(OWLOntologyChangeListener ontologyChangeListener_);

	public void addOntologyChangeProgessListener(OWLOntologyChangeProgressListener ontologyChangeProgressListener_);

	public void removeOntologyChangeListener(OWLOntologyChangeListener ontologyChangeListener_);

	public void removeOntologyChangeProgessListener(OWLOntologyChangeProgressListener ontologyChangeProgressListener_);

}
