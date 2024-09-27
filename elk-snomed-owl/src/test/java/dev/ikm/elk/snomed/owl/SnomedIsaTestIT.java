package dev.ikm.elk.snomed.owl;

/*-
 * #%L
 * ELK Integration with SNOMED
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.SnomedIds;
import dev.ikm.elk.snomed.SnomedIsa;

@TestInstance(Lifecycle.PER_CLASS)
public class SnomedIsaTestIT extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedIsaTestIT.class);

	private SnomedIsa isas;

	@BeforeAll
	public void init() throws Exception {
		LOG.info("Init");
		filesExist();
		isas = SnomedIsa.init(rels_file);
	}

	@Test
	public void size() throws Exception {
		assertEquals(361461, isas.getOrderedConcepts().size());
	}

	@Test
	public void root() {
		assertEquals(0, isas.getParents(SnomedIds.root).size());
		assertEquals(0, isas.getAncestors(SnomedIds.root).size());
		assertEquals(19, isas.getChildren(SnomedIds.root).size());
		assertEquals(361461 - 1, isas.getDescendants(SnomedIds.root).size());
	}

	@Test
	public void hasAncestor() {
		for (long con : isas.getOrderedConcepts()) {
			for (long ancestor : isas.getAncestors(con)) {
				assertTrue(isas.hasAncestor(con, ancestor));
				assertFalse(isas.hasAncestor(ancestor, con));
			}
		}
	}

	@Test
	public void hasParent() {
		for (long con : isas.getOrderedConcepts()) {
			for (long parent : isas.getParents(con)) {
				assertTrue(isas.hasParent(con, parent));
				assertFalse(isas.hasParent(parent, con));
				assertTrue(isas.hasAncestor(con, parent));
				assertFalse(isas.hasAncestor(parent, con));
				assertTrue(isas.getAncestors(con).contains(parent));
			}
		}
	}

	@Test
	public void hasDescendant() {
		int cnt = 0;
		for (long con : isas.getOrderedConcepts()) {
			if (isas.getDescendants(con).size() > 1000) {
				cnt++;
				continue;
			}
			for (long desc : isas.getDescendants(con)) {
				assertTrue(isas.hasDescendant(con, desc));
				assertFalse(isas.hasDescendant(desc, con));
			}
		}
		LOG.info("Skipped: " + cnt);
	}

	@Test
	public void hasChild() {
		for (long con : isas.getOrderedConcepts()) {
			for (long child : isas.getChildren(con)) {
				assertTrue(isas.hasChild(con, child));
				assertFalse(isas.hasChild(child, con));
				assertTrue(isas.hasDescendant(con, child));
				assertFalse(isas.hasDescendant(child, con));
				assertTrue(isas.getDescendants(con).contains(child));
			}
		}
	}

	@Test
	public void checkPriors() {
		HashSet<Long> priors = new HashSet<>();
		for (long con : isas.getOrderedConcepts()) {
			for (long sup : isas.getParents(con)) {
				assertTrue(priors.contains(sup));
			}
			priors.add(con);
		}
	}

}
