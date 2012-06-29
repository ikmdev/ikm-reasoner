/*
 * #%L
 * ELK Command Line Interface
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 - 2012 Department of Computer Science, University of Oxford
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
 * 
 */
package org.semanticweb.elk.cli.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.Executors;

import org.semanticweb.elk.cli.IOReasoner;
import org.semanticweb.elk.io.FileUtils;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.parsing.Owl2ParseException;
import org.semanticweb.elk.owl.predefined.PredefinedElkClass;
import org.semanticweb.elk.reasoner.InconsistentOntologyException;
import org.semanticweb.elk.reasoner.stages.TestStageExecutor;
import org.semanticweb.elk.reasoner.taxonomy.InconsistentInstanceTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.InconsistentTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.InstanceTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.Taxonomy;
import org.semanticweb.elk.reasoner.taxonomy.TaxonomyPrinter;

/**
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class ComputeExpectedTaxonomies {

	static final String CLASSIFICATION_PATH = "../elk-reasoner/src/test/resources/classification_test_input";
	static final String REALIZATION_PATH = "../elk-reasoner/src/test/resources/realization_test_input";
	static final Taxonomy<ElkClass> INCONSISTENT_CLASS_TAXONOMY = new InconsistentTaxonomy<ElkClass>(
			PredefinedElkClass.OWL_THING, PredefinedElkClass.OWL_NOTHING);
	static final InconsistentInstanceTaxonomy<ElkClass, ElkNamedIndividual> INCONSISTENT_INSTANCE_TAXONOMY = new InconsistentInstanceTaxonomy<ElkClass, ElkNamedIndividual>(
			PredefinedElkClass.OWL_THING, PredefinedElkClass.OWL_NOTHING);

	/**
	 * args[0]: path to the dir with source ontologies
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		generateExpectedTaxonomy(CLASSIFICATION_PATH,
				new GetTaxonomy<ElkClass>() {

					@Override
					public Taxonomy<ElkClass> getTaxonomy(IOReasoner reasoner) {
						try {
							return reasoner.getTaxonomy();
						} catch (InconsistentOntologyException e) {
							System.err.println("Inconsistent!");

							return INCONSISTENT_CLASS_TAXONOMY;
						}
					}
					
					@Override
					public void dumpTaxonomy(Taxonomy<ElkClass> taxonomy, Writer writer) throws IOException {
						TaxonomyPrinter.dumpClassTaxomomy(taxonomy, writer, false);
					}
				});

		generateExpectedTaxonomy(REALIZATION_PATH, new GetTaxonomy<ElkClass>() {

			@Override
			public InstanceTaxonomy<ElkClass, ElkNamedIndividual> getTaxonomy(
					IOReasoner reasoner) {
				try {
					return reasoner.getInstanceTaxonomy();
				} catch (InconsistentOntologyException e) {
					System.err.println("Inconsistent!");

					return INCONSISTENT_INSTANCE_TAXONOMY;
				}
			}

			@SuppressWarnings("unchecked")
			@Override
			public void dumpTaxonomy(Taxonomy<ElkClass> taxonomy, Writer writer) throws IOException {
				TaxonomyPrinter.dumpInstanceTaxomomy((InstanceTaxonomy<ElkClass, ElkNamedIndividual>)taxonomy, writer, false);
			}
		});
	}

	static void generateExpectedTaxonomy(String path, GetTaxonomy<ElkClass> gt)
			throws IOException, Owl2ParseException {
		File srcDir = new File(path);
		// use just one worker to minimize the risk of errors:
		IOReasoner reasoner = new IOReasoner(new TestStageExecutor(),
				Executors.newCachedThreadPool(), 1);

		for (File ontFile : srcDir.listFiles(FileUtils
				.getExtBasedFilenameFilter("owl"))) {

			System.err.println(ontFile.getName());

			reasoner.loadOntologyFromFile(ontFile);

			Taxonomy<ElkClass> taxonomy = gt.getTaxonomy(reasoner);
			// create the expected result file
			File out = new File(srcDir.getAbsolutePath() + "/"
					+ FileUtils.dropExtension(ontFile.getName()) + ".expected");
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(out));

			gt.dumpTaxonomy(taxonomy, writer);

			writer.flush();
			writer.close();
		}

		reasoner.shutdown();
	}



	interface GetTaxonomy<T extends ElkObject> {
		Taxonomy<T> getTaxonomy(IOReasoner reasoner);
		void dumpTaxonomy(Taxonomy<T> taxonomy, Writer writer) throws IOException;
	}
}