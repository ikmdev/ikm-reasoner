package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnomedNecessaryNormalFormTst extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedNecessaryNormalFormTst.class);

	@Test
	public void init() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		LOG.info("Classify complete");
		NecessaryNormalFormBuilder nnf = new NecessaryNormalFormBuilder(ontology);
		nnf.init();
		assertEquals(361331, nnf.getConcepts().size());
		LOG.info("Init complete");
		NecessaryNormalFormTest.checkPriors(ontology, nnf);
		SnomedRoles roles = SnomedRoles.init(rels_file);
		nnf.generate(roles);
	}

}
