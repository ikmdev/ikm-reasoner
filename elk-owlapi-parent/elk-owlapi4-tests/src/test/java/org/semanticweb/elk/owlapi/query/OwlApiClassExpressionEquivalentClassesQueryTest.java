/*
 * Copyright © 2023 Integrated Knowledge Management (support@ikm.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.semanticweb.elk.owlapi.query;

/*-
 * #%L
 * ELK OWL API v.4 Binding for Test
 * %%
 * Copyright (C) 2023 - 2024 Integrated Knowledge Management
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.runner.RunWith;
import org.semanticweb.elk.testing.ElkTestUtils;
import org.semanticweb.elk.io.IOUtils;
import org.semanticweb.elk.owlapi.OwlApiReasoningTestDelegate;
import org.semanticweb.elk.reasoner.query.BaseQueryTest;
import org.semanticweb.elk.reasoner.query.QueryTestInput;
import org.semanticweb.elk.reasoner.query.QueryTestManifest;
import org.semanticweb.elk.testing.ConfigurationUtils;
import org.semanticweb.elk.testing4.PolySuite4;
import org.semanticweb.elk.testing.PolySuite.Config;
import org.semanticweb.elk.testing.PolySuite.Configuration;
import org.semanticweb.elk.testing.TestManifestWithOutput;
import org.semanticweb.elk.testing.TestUtils;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.reasoner.ReasonerInterruptedException;

@RunWith(PolySuite4.class)
public class OwlApiClassExpressionEquivalentClassesQueryTest extends
		BaseQueryTest<OWLClassExpression, OwlEquivalentClassesTestOutput> {

	// @formatter:off
	static final String[] IGNORE_LIST = {
			ElkTestUtils.TEST_INPUT_LOCATION + "/query/class/Inconsistent.owl",// Throwing InconsistentOntologyException
			ElkTestUtils.TEST_INPUT_LOCATION + "/query/class/InconsistentInstances.owl",// Throwing InconsistentOntologyException
		};
	// @formatter:on

	static {
		Arrays.sort(IGNORE_LIST);
	}

	@Override
	protected boolean ignore(final QueryTestInput<OWLClassExpression> input) {
		return super.ignore(input) || TestUtils.ignore(input,
				ElkTestUtils.TEST_INPUT_LOCATION, IGNORE_LIST);
	}

	public OwlApiClassExpressionEquivalentClassesQueryTest(
			final QueryTestManifest<OWLClassExpression, OwlEquivalentClassesTestOutput> manifest) {
		super(manifest,
				new OwlApiReasoningTestDelegate<OwlEquivalentClassesTestOutput>(
						manifest) {

					@Override
					public OwlEquivalentClassesTestOutput getActualOutput()
							throws Exception {
						return new OwlEquivalentClassesTestOutput(getReasoner(),
								manifest.getInput().getQuery());
					}

					@Override
					public Class<? extends Exception> getInterruptionExceptionClass() {
						return ReasonerInterruptedException.class;
					}

				});
	}

	@Config
	public static Configuration getConfig()
			throws IOException, URISyntaxException {

		return ConfigurationUtils.loadFileBasedTestConfiguration(
				ElkTestUtils.TEST_INPUT_LOCATION, BaseQueryTest.class,
				new ConfigurationUtils.ManifestCreator<TestManifestWithOutput<QueryTestInput<OWLClassExpression>, OwlEquivalentClassesTestOutput>>() {

					@Override
					public Collection<? extends TestManifestWithOutput<QueryTestInput<OWLClassExpression>, OwlEquivalentClassesTestOutput>> createManifests(
							final String name, final List<URL> urls)
							throws IOException {

						if (urls == null || urls.size() < 2) {
							// Not enough inputs. Probably forgot something.
							throw new IllegalArgumentException(
									"Need at least 2 URL-s!");
						}
						if (urls.get(0) == null || urls.get(1) == null) {
							// No inputs, no manifests.
							return Collections.emptySet();
						}

						InputStream outputIS = null;
						try {
							outputIS = urls.get(1).openStream();

							return OwlExpectedTestOutputLoader.load(outputIS)
									.getEquivalentClassesManifests(name,
											urls.get(0));

						} finally {
							IOUtils.closeQuietly(outputIS);
						}

					}

				}, "owl", "classquery");

	}

}
