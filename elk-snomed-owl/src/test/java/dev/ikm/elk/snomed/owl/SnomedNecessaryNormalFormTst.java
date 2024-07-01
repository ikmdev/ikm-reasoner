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

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.NecessaryNormalFormBuilder;
import dev.ikm.elk.snomed.SnomedOntology;
import dev.ikm.elk.snomed.SnomedRoles;

public class SnomedNecessaryNormalFormTst extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedNecessaryNormalFormTst.class);

	@Test
	public void init() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		LOG.info("Classify complete");
		SnomedOntology snomedOntology = new OwlTransformer().transform(ontology);
		NecessaryNormalFormBuilder nnfb = new NecessaryNormalFormBuilder(snomedOntology, ontology.getSuperClasses(),
				ontology.getSuperObjectProperties(false));
		nnfb.init();
		assertEquals(361331, nnfb.getConcepts().size());
		LOG.info("Init complete");
		NecessaryNormalFormTest.checkPriors(ontology, nnfb);
//		SnomedRoles roles = SnomedRoles.init(rels_file);
		SnomedRoles roles = SnomedRoles.init(Paths.get("nnf-data", "nnf-20231121.txt"));
		nnfb.generate(roles, null);
//		write(nnfb);
	}

//	private void write(NecessaryNormalFormBuilder nnfb) throws Exception {
//		// id effectiveTime active moduleId sourceId destinationId relationshipGroup
//		// typeId characteristicTypeId modifierId
//		PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get("nnf.txt")));
//		out.println("id" + "\t" + "effectiveTime" + "\t" + "active" + "\t" + "moduleId" + "\t" + "sourceId" + "\t"
//				+ "destinationId" + "\t" + "relationshipGroup" + "\t" + "typeId" + "\t" + "characteristicTypeId" + "\t"
//				+ "modifierId");
//		String active = "1";
//		for (Entry<OWLClass, NecessaryNormalForm> es : nnfb.getNecessaryNormalForm().entrySet()) {
//			long sourceId = SnomedOwlOntology.getId(es.getKey());
//			ArrayList<Set<OWLObjectSomeValuesFrom>> props = new ArrayList<>();
//			props.add(es.getValue().getUngroupedProps());
//			props.addAll(es.getValue().getGroupedProps());
////			if (!es.getValue().getUngroupedProps().isEmpty() && !es.getValue().getGroupedProps().isEmpty())
////				LOG.info("Ungrouped and grouped (props): " + sourceId);
//			int relationshipGroup = 0;
//			for (Set<OWLObjectSomeValuesFrom> group : props) {
//				for (OWLObjectSomeValuesFrom prop : group) {
//					OWLObjectProperty property = prop.getProperty().asOWLObjectProperty();
//					OWLClass filler = prop.getFiller().asOWLClass();
//					long destinationId = SnomedOwlOntology.getId(filler);
//					long typeId = SnomedOwlOntology.getId(property);
//					out.println("-1\t-2\t" + active + "\t-4\t" + sourceId + "\t" + destinationId + "\t"
//							+ relationshipGroup + "\t" + typeId + "\t" + "-9\t-10");
//				}
//				relationshipGroup++;
//			}
//		}
//		out.close();
//	}

}
