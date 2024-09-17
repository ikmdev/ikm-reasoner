package dev.ikm.elk.snomed.owl;

/*-
 * #%L
 * ELK Integration with SNOMED using OWL API
 * %%
 * Copyright (C) 2023 Integrated Knowledge Management
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
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.semanticweb.elk.reasoner.Reasoner;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.SnomedIsa;
import dev.ikm.elk.snomed.SnomedRoles;
import dev.ikm.elk.snomed.SnomedRoles.SnomedRole;

public class NecessaryNormalFormBuilderOwl {

	private static final Logger LOG = LoggerFactory.getLogger(NecessaryNormalFormBuilderOwl.class);

	private SnomedOwlOntology ontology;

	private List<OWLClass> concepts = new ArrayList<>();

	private SnomedIsa isa;

	private HashMap<OWLObjectProperty, OWLObjectProperty> chained = new HashMap<>();

	private HashSet<OWLObjectProperty> transitive = new HashSet<>();

	private HashMap<OWLObjectProperty, Set<OWLObjectProperty>> superProps = new HashMap<>();

	private HashMap<OWLClass, List<NecessaryNormalForm>> necessaryNormalForms = new HashMap<>();

	private HashMap<OWLClass, NecessaryNormalForm> necessaryNormalForm = new HashMap<>();

	final private boolean useReasonerSCOE = false;

	final private boolean checkWithReasonerSCOE = false;

	public List<OWLClass> getConcepts() {
		return concepts;
	}

	public List<NecessaryNormalForm> getNecessaryNormalForms(OWLClass con) {
		return necessaryNormalForms.get(con);
	}

	public HashMap<OWLClass, NecessaryNormalForm> getNecessaryNormalForm() {
		return necessaryNormalForm;
	}

	public NecessaryNormalFormBuilderOwl(SnomedOwlOntology ontology) {
		super();
		this.ontology = ontology;
	}

	public void init() {
		initConcepts();
		initRoles();
		for (OWLAxiom ax : ontology.getOntology().getAxioms()) {
			switch (ax.getAxiomType().getName()) {
			case "SubClassOf" -> {
			}
			case "EquivalentClasses" -> {
			}
			case "SubObjectPropertyOf" -> {
			}
			case "SubPropertyChainOf" -> {
			}
			case "TransitiveObjectProperty" -> {
			}
			case "ReflexiveObjectProperty" -> {
			}
			default -> throw new UnsupportedOperationException("Unexpected: " + ax + " " + ax.getAxiomType());
			}
		}
	}

	private void initConcepts() {
		HashMap<Long, Set<Long>> superConcepts = new HashMap<>();
		HashMap<Long, Set<Long>> dependentOnConcepts = new HashMap<>();
		for (OWLClass concept : ontology.getOntology().getClassesInSignature()) {
			long id = SnomedOwlOntology.getId(concept);
			superConcepts.put(id, ontology.getSuperClasses(id));
			dependentOnConcepts.put(id, getDependentOnConcepts(concept));
		}
		isa = SnomedIsa.init(superConcepts);
//		isa.getConcepts().stream().map(id -> ontology.getOwlClass(id)).forEach(clazz -> concepts.add(clazz));
		sortConcepts(dependentOnConcepts);
		LOG.info("Concepts: " + concepts.size());
	}

	private void sortConcepts(HashMap<Long, Set<Long>> dependentOnConcepts) {
		SnomedIsa deps = SnomedIsa.init(dependentOnConcepts);
		deps.getOrderedConcepts().stream().map(id -> ontology.getOwlClass(id)).forEach(clazz -> concepts.add(clazz));
	}

	private void initRoles() {
		for (OWLSubPropertyChainOfAxiom ax : ontology.getOntology().getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF)) {
			LOG.info("Chain: " + ax);
			if (ax.getPropertyChain().size() != 2)
				throw new UnsupportedOperationException("Unexpected: " + ax);
			OWLObjectProperty prop1 = ax.getPropertyChain().get(0).asOWLObjectProperty();
			OWLObjectProperty prop2 = ax.getPropertyChain().get(1).asOWLObjectProperty();
			OWLObjectProperty sup = ax.getSuperProperty().asOWLObjectProperty();
			if (!prop1.equals(sup))
				throw new UnsupportedOperationException("Unexpected: " + ax);
			if (chained.get(prop1) != null)
				throw new UnsupportedOperationException(
						"Two chains for: " + prop1 + " " + ax + " " + chained.get(prop1));
			chained.put(prop1, prop2);
		}
		for (Entry<OWLObjectProperty, OWLObjectProperty> es : chained.entrySet()) {
			LOG.info("Chained: " + es.getKey() + " " + es.getValue());
		}
		for (OWLTransitiveObjectPropertyAxiom ax : ontology.getOntology()
				.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY)) {
			OWLObjectProperty prop = ax.getProperty().asOWLObjectProperty();
			transitive.add(prop);
		}
		for (OWLObjectProperty prop : transitive) {
			LOG.info("Transitive: " + prop);
		}
		for (OWLObjectProperty prop : ontology.getOntology().getObjectPropertiesInSignature()) {
			superProps.put(prop, new HashSet<>());
			superProps.get(prop).add(prop);
			// TODO Why does this this cause incompleteness warnings
			ontology.getReasoner().getSuperObjectProperties(prop, false).getFlattened().stream()
					.filter(x -> !x.isOWLTopObjectProperty())
					.forEach(x -> superProps.get(prop).add(x.asOWLObjectProperty()));
		}
		for (Entry<OWLObjectProperty, Set<OWLObjectProperty>> es : superProps.entrySet()) {
			if (es.getValue().size() > 1) {
//				LOG.info("Super props: " + es.getKey());
//				for (OWLObjectProperty prop : es.getValue()) {
//					if (!prop.equals(es.getKey()))
//						LOG.info("\t" + prop);
//				}
			}
		}
	}

	private HashSet<Long> getDependentOnConcepts(OWLClass concept) {
		HashSet<Long> dependentOnConcepts = new HashSet<>();
		long id = SnomedOwlOntology.getId(concept);
		dependentOnConcepts.addAll(ontology.getSuperClasses(id));
		for (OWLClassAxiom axiom : ontology.getAxioms(concept)) {
			OWLClassExpression def = getDefinition(concept, axiom);
			Set<OWLObjectSomeValuesFrom> props = processDefinition(def);
			for (OWLObjectSomeValuesFrom prop : props) {
				if (SnomedOwlOntology.getId(prop.getProperty().getNamedProperty()) == SnomedOwlOntology.role_group) {
					OWLClassExpression gr_expr = prop.getFiller();
					Set<OWLObjectSomeValuesFrom> gr_props = processDefinition(gr_expr);
					for (OWLObjectSomeValuesFrom gr_prop : gr_props) {
						OWLClass value = gr_prop.getFiller().asOWLClass();
						dependentOnConcepts.add(SnomedOwlOntology.getId(value));
					}
				} else {
					OWLClass value = prop.getFiller().asOWLClass();
					dependentOnConcepts.add(SnomedOwlOntology.getId(value));
				}
			}
		}
		return dependentOnConcepts;
	}

	public void generate() {
		generate(null);
	}

	int mis_match_cnt = 0;
	int mis_match_roles_ungrouped_cnt = 0;
	int mis_match_props_ungrouped_cnt = 0;
	int mis_match_roles_grouped_cnt = 0;
	int mis_match_props_grouped_cnt = 0;
	int mis_match_grouping_issue_cnt = 0;

	public int getMisMatchCount() {
		return mis_match_cnt;
	}

	public void generate(SnomedRoles roles) {
		Reasoner.processingNecessaryNormalForm = true;
		int cnt = 0;
		for (OWLClass concept : concepts) {
			if (++cnt % 50000 == 0)
				LOG.info("Generate: " + cnt);
			necessaryNormalForms.put(concept, new ArrayList<>());
			for (OWLClassAxiom axiom : ontology.getAxioms(concept)) {
//				LOG.info(concept + " " + axiom);
				NecessaryNormalForm expr = createExpression(concept, axiom);
				necessaryNormalForms.get(concept).add(expr);
				simplify(expr.getUngroupedProps());
				simplifyGroup(expr.getGroupedProps());
			}
			necessaryNormalForm.put(concept, mergeNNFs(necessaryNormalForms.get(concept)));
			if (roles != null) {
				compare(concept, roles.getRoles(SnomedOwlOntology.getId(concept)), necessaryNormalForm.get(concept));
			}
		}
		LOG.info("Generate: " + cnt);
		Reasoner.processingNecessaryNormalForm = false;
		LOG.info("Mis match: " + mis_match_cnt);
		LOG.info("Mis match ungrouped: " + mis_match_roles_ungrouped_cnt + " roles " + mis_match_props_ungrouped_cnt
				+ " props");
		LOG.info("Mis match grouped: " + mis_match_roles_grouped_cnt + " roles " + mis_match_props_grouped_cnt
				+ " props");
		LOG.info("Mis match grouping issue: " + mis_match_grouping_issue_cnt);
		LOG.info("SCOE: " + scoe_cnt);
		LOG.info("SCOE chained: " + scoe_chained_cnt + " " + (scoe_chained_cnt * 100 / scoe_cnt) + "%");
	}

	private NecessaryNormalForm mergeNNFs(List<NecessaryNormalForm> nnfs) {
		NecessaryNormalForm merged_nnf = new NecessaryNormalForm();
		// TODO subClassOf
		merged_nnf.setSups(new HashSet<>());
		merged_nnf.setUngroupedProps(new HashSet<>());
		merged_nnf.setGroupedProps(new HashSet<>());
		for (NecessaryNormalForm nnf : nnfs) {
			merged_nnf.getSups().addAll(nnf.getSups());
			merged_nnf.getUngroupedProps().addAll(nnf.getUngroupedProps());
			merged_nnf.getGroupedProps().addAll(nnf.getGroupedProps());
		}
		simplifySups(merged_nnf.getSups());
		simplify(merged_nnf.getUngroupedProps());
		simplifyGroup(merged_nnf.getGroupedProps());
		return merged_nnf;
	}

	private OWLClassExpression getDefinition(OWLClass concept, OWLClassAxiom axiom) {
		switch (axiom) {
		case OWLEquivalentClassesAxiom x -> {
			Set<OWLClassExpression> class_exprs = x.getClassExpressionsMinus(concept);
			if (class_exprs.size() != 1)
				throw new UnsupportedOperationException("Unexpected: " + class_exprs.size() + " " + class_exprs);
			OWLClassExpression def = class_exprs.iterator().next();
			return def;
		}
		case OWLSubClassOfAxiom x -> {
			OWLClassExpression def = x.getSuperClass();
			return def;
		}
		default -> throw new UnsupportedOperationException("Unexpected: " + axiom.getAxiomType());
		}
	}

	private NecessaryNormalForm createExpression(OWLClass concept, OWLClassAxiom axiom) {
		NecessaryNormalForm expr = new NecessaryNormalForm();
		{
			switch (axiom) {
			case OWLEquivalentClassesAxiom x -> expr.setSubClassOf(false);
			case OWLSubClassOfAxiom x -> expr.setSubClassOf(true);
			default -> throw new UnsupportedOperationException("Unexpected: " + axiom.getAxiomType());
			}
		}
		expr.setSups(ontology.getSuperClasses(concept));
		{
			Set<OWLObjectSomeValuesFrom> exprs = expr.getSups().stream().map(x -> necessaryNormalForm.get(x))
					.map(x -> x.getUngroupedProps()).flatMap(Set::stream)
					.collect(Collectors.toCollection(HashSet::new));
			expr.setUngroupedProps(exprs);
		}
		{
			Set<Set<OWLObjectSomeValuesFrom>> exprs = expr.getSups().stream().map(x -> necessaryNormalForm.get(x))
					.map(x -> x.getGroupedProps()).flatMap(Set::stream).collect(Collectors.toCollection(HashSet::new));
			expr.setGroupedProps(exprs);
		}
		OWLClassExpression def = getDefinition(concept, axiom);
		Set<OWLObjectSomeValuesFrom> props = processDefinition(def);
		for (OWLObjectSomeValuesFrom prop : props) {
			if (SnomedOwlOntology.getId(prop.getProperty().getNamedProperty()) == SnomedOwlOntology.role_group) {
				OWLClassExpression gr_expr = prop.getFiller();
				Set<OWLObjectSomeValuesFrom> gr_props = processDefinition(gr_expr);
				simplify(gr_props);
				expr.getGroupedProps().add(gr_props);
			} else {
				expr.getUngroupedProps().add(prop);
			}
		}
		return expr;
	}

	private Set<OWLObjectSomeValuesFrom> processDefinition(OWLClassExpression class_expr) {
		Set<OWLObjectSomeValuesFrom> ret = new HashSet<>();
		switch (class_expr) {
		case OWLClass x -> {
		}
		case OWLObjectIntersectionOf x -> ret.addAll(processIntersection(x.getOperands()));
		// This is just for role groups
		case OWLObjectSomeValuesFrom x -> ret.add(x);
		default -> throw new UnsupportedOperationException(
				"Unexpected: " + class_expr + " " + class_expr.getClassExpressionType());
		}
		return ret;
	}

	private Set<OWLObjectSomeValuesFrom> processIntersection(Set<OWLClassExpression> class_exprs) {
		Set<OWLObjectSomeValuesFrom> ret = new HashSet<>();
		for (OWLClassExpression class_expr : class_exprs) {
			switch (class_expr) {
			case OWLClass x -> {
			}
			case OWLObjectSomeValuesFrom x -> ret.add(x);
			default -> throw new UnsupportedOperationException(
					"Unexpected: " + class_expr + " " + class_expr.getClassExpressionType());
			}
		}
		return ret;
	}

	private int scoe_cnt = 0;
	private int scoe_chained_cnt = 0;

	private boolean isSubClassOfEntailed(OWLObjectSomeValuesFrom prop1, OWLObjectSomeValuesFrom prop2) {
		if (useReasonerSCOE)
			return isSubClassOfEntailedReasoner(prop1, prop2);
		return isSubClassOfEntailedStructural(prop1, prop2);
	}

	private boolean isSubClassOfEntailedReasoner(OWLObjectSomeValuesFrom prop1, OWLObjectSomeValuesFrom prop2) {
		boolean isSubClassOf = false;
		if (superProps.get(prop1.getProperty().asOWLObjectProperty())
				.contains(prop2.getProperty().asOWLObjectProperty())) {
			scoe_cnt++;
			if (chained.get(prop1.getProperty()) == null && chained.get(prop2.getProperty()) == null
					&& !transitive.contains(prop1.getProperty()) && !transitive.contains(prop2.getProperty())) {
				long con1 = SnomedOwlOntology.getId(prop1.getFiller().asOWLClass());
				long con2 = SnomedOwlOntology.getId(prop2.getFiller().asOWLClass());
				isSubClassOf = con1 == con2 || isa.hasAncestor(con1, con2);
			} else {
				scoe_chained_cnt++;
				OWLSubClassOfAxiom en = ontology.getDataFactory().getOWLSubClassOfAxiom(prop1, prop2);
				isSubClassOf = ontology.getReasoner().isEntailed(en);
			}
		}
		if (checkWithReasonerSCOE) {
			OWLSubClassOfAxiom en = ontology.getDataFactory().getOWLSubClassOfAxiom(prop1, prop2);
			if (isSubClassOf != ontology.getReasoner().isEntailed(en))
				throw new RuntimeException("");
		}
		return isSubClassOf;
	}

	private static class SVF {
		private OWLObjectProperty prop;
		private OWLClass filler;

		public SVF(OWLObjectProperty prop, OWLClass filler) {
			super();
			this.prop = prop;
			this.filler = filler;
		}

		@Override
		public int hashCode() {
			return Objects.hash(filler, prop);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SVF other = (SVF) obj;
			return Objects.equals(filler, other.filler) && Objects.equals(prop, other.prop);
		}

		@Override
		public String toString() {
			return "SVF(" + prop + " " + filler + ")";
		}

	}

	private HashSet<SVF> expandChain(OWLObjectProperty prop, OWLClass filler) {
		HashSet<SVF> svfs = new HashSet<>();
		svfs.add(new SVF(prop, filler));
		while (true) {
			ArrayList<SVF> svfs_l = new ArrayList<>(svfs);
			for (SVF svf : svfs_l) {
				List<SVF> chain_exps = expandChain1(svf);
				svfs.addAll(chain_exps);
				List<SVF> prop_exps = expandSuperProps(svf);
				svfs.addAll(prop_exps);
			}
			if (svfs.size() == svfs_l.size())
				break;
		}
		return svfs;
	}

	private List<SVF> expandChain1(SVF svf) {
		ArrayList<OWLObjectProperty> chained_props = new ArrayList<>();
		if (chained.get(svf.prop) != null) {
			chained_props.add(chained.get(svf.prop));
		}
		if (transitive.contains(svf.prop))
			chained_props.add(svf.prop);
		List<OWLClass> chained_cons = necessaryNormalForm.get(svf.filler).getUngroupedProps().stream()
				.filter(x -> chained_props.contains(x.getProperty().asOWLObjectProperty()))
				.map(x -> x.getFiller().asOWLClass()).distinct().toList();
		return chained_cons.stream().map(x -> new SVF(svf.prop, x)).toList();
	}

	private List<SVF> expandSuperProps(SVF svf) {
		return superProps.get(svf.prop).stream().filter(x -> !x.equals(svf.prop)).map(x -> new SVF(x, svf.filler))
				.toList();
	}

	private boolean isSubsumedBy(OWLClass con1, OWLClass con2) {
		if (con1.equals(con2))
			return true;
		return isa.hasAncestor(SnomedOwlOntology.getId(con1), SnomedOwlOntology.getId(con2));
	}

	private boolean isSubsumedBy(SVF svf1, SVF svf2) {
		return superProps.get(svf1.prop).contains(svf2.prop) && isSubsumedBy(svf1.filler, svf2.filler);
	}

	private boolean isSubClassOfEntailedStructural(OWLObjectSomeValuesFrom prop1, OWLObjectSomeValuesFrom prop2) {
		if (superProps.get(prop1.getProperty().asOWLObjectProperty())
				.contains(prop2.getProperty().asOWLObjectProperty())) {
			scoe_cnt++;
			{
				OWLClass con1 = prop1.getFiller().asOWLClass();
				OWLClass con2 = prop2.getFiller().asOWLClass();
				if (isSubsumedBy(con1, con2))
					return true;
			}
			HashSet<SVF> chain1 = expandChain(prop1.getProperty().asOWLObjectProperty(),
					prop1.getFiller().asOWLClass());
			HashSet<SVF> chain2 = expandChain(prop2.getProperty().asOWLObjectProperty(),
					prop2.getFiller().asOWLClass());
			boolean isSubsumedBy = chain2.stream()
					.allMatch(svf2 -> chain1.stream().anyMatch(svf1 -> isSubsumedBy(svf1, svf2)));
			return isSubsumedBy;
		}
		return false;
	}

	private void simplifySups(Set<OWLClass> cons) {
		if (cons.isEmpty())
			return;
		ArrayList<OWLClass> to_remove = new ArrayList<>();
		for (OWLClass con1 : cons) {
			for (OWLClass con2 : cons) {
				if (con1 == con2)
					continue;
				if (isa.hasAncestor(SnomedOwlOntology.getId(con1), SnomedOwlOntology.getId(con2)))
					to_remove.add(con2);
			}
		}
		cons.removeAll(to_remove);
	}

	private void simplify(Set<OWLObjectSomeValuesFrom> props) {
		if (props.isEmpty())
			return;
		ArrayList<OWLObjectSomeValuesFrom> to_remove = new ArrayList<>();
		for (OWLObjectSomeValuesFrom prop1 : props) {
			for (OWLObjectSomeValuesFrom prop2 : props) {
				if (prop1 == prop2)
					continue;
				if (isSubClassOfEntailed(prop1, prop2))
					to_remove.add(prop2);
			}
		}
		props.removeAll(to_remove);
	}

	private void simplifyGroup(Set<Set<OWLObjectSomeValuesFrom>> props) {
		if (props.isEmpty())
			return;
		ArrayList<Set<OWLObjectSomeValuesFrom>> to_remove = new ArrayList<>();
		for (Set<OWLObjectSomeValuesFrom> prop1 : props) {
			for (Set<OWLObjectSomeValuesFrom> prop2 : props) {
				if (prop1 == prop2)
					continue;
				boolean isSubClassOf = prop2.stream()
						.allMatch(p2 -> prop1.stream().anyMatch(p1 -> isSubClassOfEntailed(p1, p2)));
				if (isSubClassOf)
					to_remove.add(prop2);
				if (checkWithReasonerSCOE) {
					OWLObjectIntersectionOf and1 = ontology.getDataFactory().getOWLObjectIntersectionOf(prop1);
					OWLObjectIntersectionOf and2 = ontology.getDataFactory().getOWLObjectIntersectionOf(prop2);
					OWLSubClassOfAxiom en = ontology.getDataFactory().getOWLSubClassOfAxiom(and1, and2);
					if (isSubClassOf != ontology.getReasoner().isEntailed(en))
						throw new RuntimeException("");
				}
			}
		}
		props.removeAll(to_remove);
	}

	HashSet<OWLClass> grouping_issue_concepts = new HashSet<>();

	private void compare(OWLClass concept, Set<SnomedRole> roles, NecessaryNormalForm nnf) {
		if (roles == null)
			roles = Set.of();
		List<SnomedRole> roles_ungrouped = roles.stream().filter(x -> x.relationshipGroup == 0).toList();
		Collection<List<SnomedRole>> roles_grouped = roles.stream().filter(x -> x.relationshipGroup != 0)
				.collect(Collectors.groupingBy(x -> x.relationshipGroup)).values();
		boolean mis_match = false;
		List<SnomedRole> mis_match_roles_grouped = new ArrayList<>();
		Set<OWLObjectSomeValuesFrom> mis_match_props_grouped = new HashSet<>();
		boolean inc;
		inc = false;
		for (SnomedRole role : roles_ungrouped) {
			boolean match = nnf.getUngroupedProps().stream().anyMatch(prop -> compare(role, prop));
			if (!match) {
				LOG.error("No propU for " + concept + " " + role);
				mis_match = true;
				inc = true;
			}
		}
		if (inc)
			mis_match_roles_ungrouped_cnt++;
		inc = false;
		for (OWLObjectSomeValuesFrom prop : nnf.getUngroupedProps()) {
			boolean match = roles_ungrouped.stream().anyMatch(role -> compare(role, prop));
			if (!match) {
				LOG.error("No roleU for " + concept + " " + prop);
				mis_match = true;
				inc = true;
			}
		}
		if (inc)
			mis_match_props_ungrouped_cnt++;
		inc = false;
		for (List<SnomedRole> role : roles_grouped) {
			boolean match = nnf.getGroupedProps().stream().anyMatch(prop -> compare(role, prop));
			if (!match) {
				LOG.error("No propG for " + concept + " " + role);
				mis_match = true;
				mis_match_roles_grouped.addAll(role);
				inc = true;
			}
		}
		if (inc)
			mis_match_roles_grouped_cnt++;
		inc = false;
		for (Set<OWLObjectSomeValuesFrom> prop : nnf.getGroupedProps()) {
			boolean match = roles_grouped.stream().anyMatch(role -> compare(role, prop));
			if (!match) {
				LOG.error("No roleG for " + concept + " " + prop);
				mis_match = true;
				mis_match_props_grouped.addAll(prop);
				inc = true;
			}
		}
		if (inc)
			mis_match_props_grouped_cnt++;
		inc = false;
		if (mis_match) {
			mis_match_cnt++;
//			LOG.info("NNFs: " + nnfs.size());
			LOG.info("Roles:");
			roles.stream()
					.sorted(Comparator.comparingLong((SnomedRole x) -> x.relationshipGroup)
							.thenComparingLong((SnomedRole x) -> x.typeId).thenComparingLong((SnomedRole x) -> x.destinationId))
					.forEach(x -> LOG.info("\t" + x));
			LOG.info("PropsU:");
			nnf.getUngroupedProps().forEach(x -> LOG.info("\t" + x));
			LOG.info("PropsG:");
			nnf.getGroupedProps().forEach(x -> {
				LOG.info("Group:");
				x.forEach(y -> LOG.info("\t" + y));
			});
			if (mis_match_roles_grouped.size() > 0 && mis_match_props_grouped.size() > 0) {
				if (compare(mis_match_roles_grouped, mis_match_props_grouped)) {
					LOG.warn("Grouping issue");
					mis_match_grouping_issue_cnt++;
					grouping_issue_concepts.add(concept);
				} else {
					LOG.error("Not a grouping issue - compare");
					boolean ancestor_grouping_issue = grouping_issue_concepts.stream().anyMatch(
							x -> isa.hasAncestor(SnomedOwlOntology.getId(concept), SnomedOwlOntology.getId(x)));
					if (ancestor_grouping_issue)
						LOG.warn("Grouping issue with ancestor");
				}
			} else {
				LOG.error("Not a grouping issue - size");
			}
		}
	}

	private boolean compare(List<SnomedRole> roles, Set<OWLObjectSomeValuesFrom> props) {
		return roles.stream().allMatch(role -> props.stream().anyMatch(prop -> compare(role, prop)))
				&& props.stream().allMatch(prop -> roles.stream().anyMatch(role -> compare(role, prop)));
	}

	private boolean compare(SnomedRole role, OWLObjectSomeValuesFrom prop) {
		return role.typeId == SnomedOwlOntology.getId(prop.getProperty().asOWLObjectProperty())
				&& role.destinationId == SnomedOwlOntology.getId(prop.getFiller().asOWLClass());
	}

}
