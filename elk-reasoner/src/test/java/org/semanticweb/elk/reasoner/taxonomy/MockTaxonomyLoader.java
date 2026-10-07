package org.semanticweb.elk.reasoner.taxonomy;
/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.iris.ElkPrefix;
import org.semanticweb.elk.owl.parsing.Owl2ParseException;
import org.semanticweb.elk.owl.parsing.Owl2Parser;
import org.semanticweb.elk.owl.parsing.Owl2ParserAxiomProcessor;
import org.semanticweb.elk.owl.predefined.PredefinedElkClassFactory;
import org.semanticweb.elk.owl.visitors.DummyElkAxiomVisitor;
import org.semanticweb.elk.reasoner.taxonomy.MockTaxonomy.MutableTaxonomyNode;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;

/**
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 *
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 */
public class MockTaxonomyLoader {

	/**
	 * Loads a class {@link Taxonomy} using the given {@link ElkObject.Factory}
	 * and {@link Owl2Parser}. The {@link ElkObject.Factory} should create equal
	 * {@link ElkClass}s for equal {@link ElkIri}s (w.r.t. to the
	 * {@link #equals(Object)} method. The {@link Owl2Parser} should use the
	 * same {@link ElkObject.Factory}. Axioms other than class declarations,
	 * {@link ElkEquivalentClassesAxiom}s and {@link ElkSubClassOfAxiom}s
	 * between classes are ignored.
	 *
	 * @param factory
	 *            the factory for creating owl:Thing and owl:Nothing
	 *
	 * @param parser
	 *            the {@link Owl2Parser} for loading the ontology representing
	 *            the taxonomy; it should use the same {@link ElkObject.Factory}
	 *
	 * @return the {@link Taxonomy} constructed from the parsed ontology
	 *
	 * @throws Owl2ParseException
	 *             if the ontology cannot be parsed by the parser
	 */
	public static Taxonomy<ElkClass> load(PredefinedElkClassFactory factory,
			Owl2Parser parser) throws Owl2ParseException {
		final MockTaxonomy<ElkClass> taxonomy = new MockTaxonomy<ElkClass>(
				factory.getOwlThing(), factory.getOwlNothing(),
				ElkClassKeyProvider.INSTANCE);
		TaxonomyInserter listener = new TaxonomyInserter(taxonomy);

		parser.accept(listener);

		listener.createNodes = true;
		// process the remaining axioms, the order is important
		process(listener, listener.classDeclarations);
		process(listener, listener.subClassOfAxioms);

		return taxonomy;
	}

	private static void process(TaxonomyInserter inserter,
			List<ElkAxiom> axioms) {
		for (ElkAxiom decl : axioms) {
			decl.accept(inserter);
		}
	}

	static class TaxonomyInserter extends DummyElkAxiomVisitor<Void>
			implements Owl2ParserAxiomProcessor {

		boolean createNodes = false;
		final MockTaxonomy<ElkClass> taxonomy;
		final List<ElkAxiom> subClassOfAxioms = new ArrayList<ElkAxiom>();
		final List<ElkAxiom> classDeclarations = new ArrayList<ElkAxiom>();

		TaxonomyInserter(final MockTaxonomy<ElkClass> taxonomy) {
			this.taxonomy = taxonomy;
		}

		@Override
		protected Void defaultLogicalVisit(ElkAxiom axiom) {
			return null;
		}

		@Override
		protected Void defaultNonLogicalVisit(ElkAxiom axiom) {
			return null;
		}

		@Override
		public Void visit(ElkEquivalentClassesAxiom elkEquivalentClassesAxiom) {
			// a new node
			Set<ElkClass> classes = new HashSet<ElkClass>();

			for (ElkClassExpression ce : elkEquivalentClassesAxiom
					.getClassExpressions()) {
				if (ce instanceof ElkClass) {
					classes.add((ElkClass) ce);
				}
			}

			taxonomy.getCreateNode(classes);

			return null;
		}

		@Override
		public Void visit(ElkSubClassOfAxiom elkSubClassOfAxiom) {
			// a subclass relationship between canonical members of two nodes
			ElkClassExpression subCE = elkSubClassOfAxiom
					.getSubClassExpression();
			ElkClassExpression superCE = elkSubClassOfAxiom
					.getSuperClassExpression();

			if (subCE instanceof ElkClass && superCE instanceof ElkClass) {
				ElkClass subClass = (ElkClass) subCE;
				ElkClass superClass = (ElkClass) superCE;
				// check if both nodes are there yet
				MutableTaxonomyNode<ElkClass> subNode = taxonomy
						.getNode(subClass);
				MutableTaxonomyNode<ElkClass> superNode = taxonomy
						.getNode(superClass);

				if ((subNode == null || superNode == null) && !createNodes) {
					// wait, maybe we'll create these nodes later
					subClassOfAxioms.add(elkSubClassOfAxiom);
				} else {
					subNode = taxonomy
							.getCreateNode(Collections.singleton(subClass));
					superNode = taxonomy
							.getCreateNode(Collections.singleton(superClass));

					if (!subNode.equals(superNode)) {
						subNode.addDirectParent(superNode);
					}
				}
			}

			return null;
		}

		@Override
		public Void visit(final ElkDeclarationAxiom elkDeclarationAxiom) {
			if (elkDeclarationAxiom.getEntity() instanceof ElkClass) {
				ElkClass elkClass = (ElkClass) elkDeclarationAxiom.getEntity();
				if (createNodes) {
					taxonomy.getCreateNode(Collections.singleton(elkClass));
				} else {
					classDeclarations.add(elkDeclarationAxiom);
				}
			}

			return null;
		}

		@Override
		public void visit(ElkAxiom elkAxiom) {
			elkAxiom.accept(this);
		}

		@Override
		public void visit(ElkPrefix elkPrefix) throws Owl2ParseException {
			// ignored
		}

		@Override
		public void finish() throws Owl2ParseException {
			// everything is processed immediately
		}

	}
}
