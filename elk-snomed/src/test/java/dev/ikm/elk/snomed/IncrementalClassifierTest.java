package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncrementalClassifierTest {

	private static final Logger LOG = LoggerFactory.getLogger(IncrementalClassifierTest.class);

	private final String home = System.getProperty("user.home");

	private final String dir = "data/snomed/SnomedCT_ManagedServiceUS_PRODUCTION_US1000124_20230301T120000Z/Snapshot/Terminology/";

	private final Path axioms_file = Paths.get(home, dir, "sct2_sRefset_OWLExpressionSnapshot_US1000124_20230301.txt");

	private final Path rels_file = Paths.get(home, dir, "sct2_Relationship_Snapshot_US1000124_20230301.txt");

	@BeforeEach
	private void filesExist() {
		assumeTrue(Files.exists(axioms_file), "No file: " + axioms_file);
		assumeTrue(Files.exists(rels_file), "No file: " + rels_file);
	}

	public SnomedOwlOntology classify() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		return ontology;
	}

	// 123823007 |Decreased blood oxygen pressure (finding)|
	// 123822002 |Increased blood oxygen pressure (finding)|

	private final long decreased_id = 123823007;
	private final long increased_id = 123822002;

	@Test
	public void change() throws Exception {
		SnomedOwlOntology ontology = classify();
		Set<OWLClassAxiom> axioms = ontology.getAxioms(ontology.getOwlClass(increased_id));
		OWLOntologyManager mgr = ontology.getOntology().getOWLOntologyManager();
		mgr.removeAxioms(ontology.getOntology(), axioms);
		OWLSubClassOfAxiom ax = mgr.getOWLDataFactory().getOWLSubClassOfAxiom(ontology.getOwlClass(increased_id),
				ontology.getOwlClass(decreased_id));
		mgr.addAxiom(ontology.getOntology(), ax);
		LOG.info("Flushing");
		ontology.getReasoner().flush();
		LOG.info("Flushed");
		Set<Long> sups = ontology.getSuperClasses(increased_id);
		LOG.info("Sups: " + sups);
		assertEquals(Set.of(decreased_id), sups);
	}

}
