package dev.ikm.elk.snomed.owl;

/*-
 * #%L
 * ELK Integration with SNOMED using OWL API
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

import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;

public class NecessaryNormalForm {

	private boolean subClassOf;

	private Set<OWLClass> sups;

	private Set<OWLObjectSomeValuesFrom> ungroupedProps;

	private Set<Set<OWLObjectSomeValuesFrom>> groupedProps;

	public boolean isSubClassOf() {
		return subClassOf;
	}

	public void setSubClassOf(boolean subClass) {
		this.subClassOf = subClass;
	}

	public Set<OWLClass> getSups() {
		return sups;
	}

	public void setSups(Set<OWLClass> sups) {
		this.sups = sups;
	}

	public Set<OWLObjectSomeValuesFrom> getUngroupedProps() {
		return ungroupedProps;
	}

	public void setUngroupedProps(Set<OWLObjectSomeValuesFrom> ungroupedProps) {
		this.ungroupedProps = ungroupedProps;
	}

	public Set<Set<OWLObjectSomeValuesFrom>> getGroupedProps() {
		return groupedProps;
	}

	public void setGroupedProps(Set<Set<OWLObjectSomeValuesFrom>> groupedProps) {
		this.groupedProps = groupedProps;
	}

}
