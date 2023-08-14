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
package org.semanticweb.elk.owl.inferences;



import org.semanticweb.elk.owl.inferences.ElkInference.Factory;

/**
 * A {@link ElkInference.Factory} which methods return {@code null} and instead
 * produce the constructed inferences using the provided
 * {@link ElkInferenceProducer}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkInferenceProducingFactory
		extends ElkInferenceDelegatingFactory {

	private final ElkInferenceProducer inferenceProducer_;

	public ElkInferenceProducingFactory(Factory mainFactory,
			ElkInferenceProducer inferenceProducer) {
		super(mainFactory);
		this.inferenceProducer_ = inferenceProducer;
	}

	public ElkInferenceProducingFactory(
			ElkInferenceProducer inferenceProducer) {
		this(new ElkInferenceBaseFactory(), inferenceProducer);
	}

	@Override
	protected <I extends ElkInference> I filter(I inference) {
		inferenceProducer_.produce(inference);
		return null;
	}

}
