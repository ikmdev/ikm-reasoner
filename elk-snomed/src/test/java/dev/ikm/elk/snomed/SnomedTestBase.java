package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedTestBase.class);

	private final String home = System.getProperty("user.home");

	protected String getDir() {
		return "data/snomed/SnomedCT_USEditionRF2_PRODUCTION_20210301T120000Z/Snapshot/Terminology/";
	}

	protected String getVersion() {
		return "20210301";
	}

	protected Path axioms_file = Paths.get(home, getDir(),
			"sct2_sRefset_OWLExpressionSnapshot_US1000124_" + getVersion() + ".txt");

	protected Path rels_file = Paths.get(home, getDir(),
			"sct2_Relationship_Snapshot_US1000124_" + getVersion() + ".txt");

	@BeforeEach
	private void filesExist() {
		assumeTrue(Files.exists(axioms_file), "No file: " + axioms_file);
		assumeTrue(Files.exists(rels_file), "No file: " + rels_file);
		LOG.info("Files exist");
		LOG.info("\t" + axioms_file);
		LOG.info("\t" + rels_file);
	}

}
