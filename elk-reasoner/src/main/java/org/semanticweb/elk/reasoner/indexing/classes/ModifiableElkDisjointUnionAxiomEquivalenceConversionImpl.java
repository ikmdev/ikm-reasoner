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
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDisjointUnionAxiomEquivalenceConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEquivalentClassesAxiomInference;

/**
 * Implements {@link ModifiableElkDisjointUnionAxiomEquivalenceConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDisjointUnionAxiomEquivalenceConversionImpl extends
		AbstractModifiableIndexedEquivalentClassesAxiomInference<ElkDisjointUnionAxiom>
		implements ModifiableElkDisjointUnionAxiomEquivalenceConversion {

	ModifiableElkDisjointUnionAxiomEquivalenceConversionImpl(
			ElkDisjointUnionAxiom originalAxiom,
			ModifiableIndexedClass definedClass,
			ModifiableIndexedClassExpression definition) {
		super(originalAxiom, definedClass, definition);
	}

	@Override
	public final <O> O accept(
			IndexedEquivalentClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedEquivalentClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
