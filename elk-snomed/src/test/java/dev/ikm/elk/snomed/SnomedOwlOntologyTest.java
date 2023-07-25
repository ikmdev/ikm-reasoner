package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnomedOwlOntologyTest {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOwlOntologyTest.class);

	private final String home = System.getProperty("user.home");

	private final String dir = "data/snomed/SnomedCT_ManagedServiceUS_PRODUCTION_US1000124_20230301T120000Z/Snapshot/Terminology/";

	private final Path axioms_file = Paths.get(home, dir, "sct2_sRefset_OWLExpressionSnapshot_US1000124_20230301.txt");

	private final Path rels_file = Paths.get(home, dir, "sct2_Relationship_Snapshot_US1000124_20230301.txt");

	@BeforeEach
	private void filesExist() {
		assumeTrue(Files.exists(axioms_file), "No file: " + axioms_file);
		assumeTrue(Files.exists(rels_file), "No file: " + rels_file);
	}

	@Test
	public void readAxioms() throws Exception {
		List<String> axioms = SnomedOwlOntology.readAxioms(axioms_file);
		assertEquals(369237, axioms.size());
		axioms.stream().map(ax -> ax.substring(0, ax.indexOf("("))).distinct().sorted().forEach(LOG::info);
	}

	@Test
	public void readOntology() throws Exception {
		List<String> axioms = SnomedOwlOntology.readOntology(axioms_file);
		assertEquals(369238, axioms.size());
		axioms.stream().limit(10).forEach(LOG::info);
		LOG.info("...");
		axioms.stream().skip(axioms.size() - 5).forEach(LOG::info);
	}

	@Test
	public void loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		assertEquals(369230, ontology.getOntology().getAxiomCount());
		assertEquals(367704, ontology.getOntology().getSignature().size());
		assertEquals(367565, ontology.getOntology().getClassesInSignature().size());
		assertEquals(126, ontology.getOntology().getObjectPropertiesInSignature().size());
		assertEquals(11, ontology.getOntology().getDataPropertiesInSignature().size());
		assertEquals(2, ontology.getOntology().getDatatypesInSignature().size());
		Set<OWLEntity> sig = ontology.getOntology().getSignature();
		sig.removeAll(ontology.getOntology().getClassesInSignature());
		sig.removeAll(ontology.getOntology().getObjectPropertiesInSignature());
		sig.removeAll(ontology.getOntology().getDataPropertiesInSignature());
		sig.stream().forEach(x -> LOG.info("In sig: " + x + " " + x.getClass()));
		sig.removeAll(ontology.getOntology().getDatatypesInSignature());
		assertEquals(0, sig.size());
	}

	@Test
	public void classify() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
	}

	@Test
	public void readIsa() throws Exception {
		HashMap<Long, Set<Long>> isas = SnomedOwlOntology.readIsa(rels_file);
		assertEquals(367699, isas.size());
	}

	private long getId(OWLClass clazz) {
		return Long.parseLong(clazz.getIRI().getShortForm());
	}

	@Test
	public void isas() throws Exception {
		int miss_cnt = 0;
		HashMap<Long, Set<Long>> isas = SnomedOwlOntology.readIsa(rels_file);
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		for (OWLClass clazz : ontology.getOntology().getClassesInSignature()) {
			Set<Long> sups = ontology.getReasoner().getSuperClasses(clazz, true).getFlattened().stream() //
					.filter(x -> !x.isTopEntity()) //
					.map(this::getId).collect(Collectors.toSet());
			long id = getId(clazz);
			Set<Long> parents = isas.get(id);
			// 138875005 |SNOMED CT Concept (SNOMED RT+CTV3)|
			if (id == 138875005) {
				assertNull(parents);
				parents = Set.of();
			} else {
				assertNotNull(parents);
			}
			if (!parents.equals(sups)) {
//				LOG.error("Miss: " + clazz);
//				HashSet<Long> par = new HashSet<>(parents);
//				par.removeAll(sups);
//				HashSet<Long> sup = new HashSet<>(sups);
//				sup.removeAll(parents);
//				LOG.error("Par:  " + par);
//				LOG.error("Sup:  " + sup);
				miss_cnt++;
			}
		}
		LOG.error("Miss cnt: " + miss_cnt);
//		assertEquals(0, miss_cnt);
	}

}
