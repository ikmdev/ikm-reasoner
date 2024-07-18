/*
 * #%L
 * ELK Reasoner
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

import java.net.URL;

import org.semanticweb.elk.testing.BasicTestManifest;
import org.semanticweb.elk.testing.DiffableOutput;

/**
 * Test manifest for query tests.
 * 
 * @author Peter Skocovsky
 * @author Yevgeny Kazakov
 *
 * @param <Q>
 *            Type of the query.
 * @param <O>
 *            Type of the test output.
 */
public class QueryTestManifest<Q, O extends DiffableOutput<?, O>>
		extends BasicTestManifest<QueryTestInput<Q>, O> {

	public QueryTestManifest(final String name, final URL input, final Q query,
			final O expectedOutput) {
		super(new QueryTestInput<Q>() {

			@Override
			public String getName() {
				return name + "(" + query + ")";
			}

			@Override
			public URL getUrl() {
				return input;
			}

			@Override
			public Q getQuery() {
				return query;
			}

		}, expectedOutput);
	}

}
