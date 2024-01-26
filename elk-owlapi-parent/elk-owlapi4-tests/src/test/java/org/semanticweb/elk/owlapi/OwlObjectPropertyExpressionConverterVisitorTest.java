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
package org.semanticweb.elk.owlapi;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.semanticweb.elk.owl.interfaces.ElkObjectInverseOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owlapi.wrapper.OwlObjectPropertyExpressionConverterVisitor;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLPropertyExpressionVisitorEx;

import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;

/**
 * Testing correctness of {@link OwlObjectPropertyExpressionConverterVisitor}
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 * 
 */
public class OwlObjectPropertyExpressionConverterVisitorTest {
	/**
	 * Testing correctness of converting nested inverses
	 */
	@Test
	public void testNestedInverses() {
		
		
		OWLDataFactory factory = new OWLDataFactoryImpl();
		OWLObjectProperty r = factory.getOWLObjectProperty(IRI.create("R"));
		OWLObjectPropertyExpression ri = factory.getOWLObjectInverseOf(r);
		
		OWLPropertyExpressionVisitorEx<ElkObjectPropertyExpression> converter = OwlObjectPropertyExpressionConverterVisitor
				.getInstance();
	
		ElkObjectPropertyExpression s = r.accept(converter);
		ElkObjectPropertyExpression si = ri.accept(converter);
	
		assertTrue(s instanceof ElkObjectProperty);
		assertTrue(si instanceof ElkObjectInverseOf);
	
		ElkIri expectedIri = ((ElkObjectProperty) s).getIri();
	
		assertEquals(expectedIri, ((ElkObjectInverseOf) si).getObjectProperty()
				.getIri());
			
	}
}
