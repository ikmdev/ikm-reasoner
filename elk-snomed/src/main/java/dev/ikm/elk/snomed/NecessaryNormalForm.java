package dev.ikm.elk.snomed;

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