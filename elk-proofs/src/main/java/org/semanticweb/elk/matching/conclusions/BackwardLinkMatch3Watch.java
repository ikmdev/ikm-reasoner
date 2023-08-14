/*
 * This product is dual-licensed under Apache 2.0 License for two organizations due to forking.
 *
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
 *
 * ======================================================================
 *
 * Copyright © 2011 - 2023 Department of Computer Science, University of Oxford
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
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch8;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyPropagatedMatch3;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch7;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectSomeValuesFromMatch3;



public interface BackwardLinkMatch3Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkCompositionMatch8.Visitor<O>,
			ClassInconsistencyPropagatedMatch3.Visitor<O>,
			ForwardLinkCompositionMatch7.Visitor<O>,
			SubClassInclusionComposedObjectSomeValuesFromMatch3.Visitor<O> {

		// combined interface

	}

}
