package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncrementalClassifierTest extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(IncrementalClassifierTest.class);

	public SnomedOwlOntology classify() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		return ontology;
	}

	public Set<Long> setParent(long concept_id, long parent_id) throws Exception {
		SnomedOwlOntology ontology = classify();
		Set<OWLClassAxiom> axioms = ontology.getAxioms(ontology.getOwlClass(concept_id));
		LOG.info("Axioms: " + axioms);
		OWLOntologyManager mgr = ontology.getOntology().getOWLOntologyManager();
		mgr.removeAxioms(ontology.getOntology(), axioms);
		OWLSubClassOfAxiom ax = mgr.getOWLDataFactory().getOWLSubClassOfAxiom(ontology.getOwlClass(concept_id),
				ontology.getOwlClass(parent_id));
		mgr.addAxiom(ontology.getOntology(), ax);
		LOG.info("Flushing");
		ontology.getReasoner().flush();
		LOG.info("Flushed");
		Set<Long> sups = ontology.getSuperClasses(concept_id);
		LOG.info("Sups: " + sups);
		return sups;
	}

	// 123823007 |Decreased blood oxygen pressure (finding)|
	// 123822002 |Increased blood oxygen pressure (finding)|

	private final long decreased_id = 123823007;
	private final long increased_id = 123822002;

	@Test
	public void change() throws Exception {
		Set<Long> sups = setParent(increased_id, decreased_id);
		assertEquals(Set.of(decreased_id), sups);
	}

	// 307824009 |Administrative statuses (finding)|
	// 138875005 |SNOMED CT Concept (SNOMED RT+CTV3)|

	private final long admin_id = 307824009;
	private final long snomed_id = 138875005;

	@Test
	public void changeBig() throws Exception {
		Set<Long> sups = setParent(admin_id, snomed_id);
		assertEquals(Set.of(snomed_id), sups);
	}

}
