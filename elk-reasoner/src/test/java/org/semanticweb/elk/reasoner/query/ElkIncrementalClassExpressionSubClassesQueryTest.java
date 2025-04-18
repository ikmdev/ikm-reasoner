/*
 * #%L
 * ELK OWL API Binding
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2016 Department of Computer Science, University of Oxford
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
package org.semanticweb.elk.reasoner.query;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.runner.RunWith;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.reasoner.config.ReasonerConfiguration;
import org.semanticweb.elk.reasoner.incremental.ElkIncrementalReasoningTestDelegate;
import org.semanticweb.elk.testing.PolySuite.Config;
import org.semanticweb.elk.testing.PolySuite.Configuration;
import org.semanticweb.elk.testing.TestManifest;
import org.semanticweb.elk.testing4.PolySuite4;
import org.semanticweb.elk.util.collections.ImmutableMapBuilder;

@RunWith(PolySuite4.class)
public class ElkIncrementalClassExpressionSubClassesQueryTest extends
		ElkIncrementalClassExpressionQueryTest<ElkDirectSubClassesTestOutput> {

	public ElkIncrementalClassExpressionSubClassesQueryTest(
			final TestManifest<QueryTestInput<ElkClassExpression>> manifest) {
		super(manifest,
				new ElkIncrementalReasoningTestDelegate<ElkDirectSubClassesTestOutput>(
						manifest) {

					@Override
					public ElkDirectSubClassesTestOutput getExpectedOutput()
							throws Exception {
						return new ElkDirectSubClassesTestOutput(
								getStandardReasoner(),
								manifest.getInput().getQuery());
					}

					@Override
					public ElkDirectSubClassesTestOutput getActualOutput()
							throws Exception {
						return new ElkDirectSubClassesTestOutput(
								getIncrementalReasoner(),
								manifest.getInput().getQuery());
					}

					@Override
					protected Map<String, String> additionalConfigIncremental() {
						return ImmutableMapBuilder.<String, String> builder().put(
								ReasonerConfiguration.CLASS_EXPRESSION_QUERY_EVICTOR,
								"NQEvictor(0, 0.75)").build();
					}

					@Override
					protected Map<String, String> additionalConfigWithInterrupts() {
						return ImmutableMapBuilder.<String, String> builder().put(
								ReasonerConfiguration.CLASS_EXPRESSION_QUERY_EVICTOR,
								"NQEvictor(0, 0.75)").build();
					}

				});
	}

	@Config
	public static Configuration getConfig()
			throws IOException, URISyntaxException {
		return getConfig("getDirectSubClasses");
	}

}
