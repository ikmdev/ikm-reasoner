/*
 * #%L
 * elk-reasoner
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 Oxford University Computing Laboratory
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
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.syntax;

/**
 * Corresponds to a <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Functional_Object_Properties">Functional
 * Object Property Axiom<a> in the OWL 2 specification.
 * 
 * @author Yevgeny Kazakov
 * 
 */
public class ElkFunctionalObjectPropertyAxiom extends ElkObjectPropertyAxiom {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.semanticweb.elk.reasoner.ELKObjectPropertyAxiom#accept(org.semanticweb
	 * .elk.reasoner.ELKObjectPropertyAxiomVisitor)
	 */
	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {

		return visitor.visit(this);

	}

	/* (non-Javadoc)
	 * @see org.semanticweb.elk.reasoner.ElkObject#equals(java.lang.Object)
	 */
	@Override
	public boolean structuralEquals(ElkObject obj) {
		// TODO Auto-generated method stub
		return false;
	}

}