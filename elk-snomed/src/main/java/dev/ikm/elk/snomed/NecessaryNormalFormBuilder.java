package dev.ikm.elk.snomed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
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

import dev.ikm.elk.snomed.SnomedRoles.Role;

public class NecessaryNormalFormBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(NecessaryNormalFormBuilder.class);

	private SnomedOwlOntology ontology;

	private List<OWLClass> concepts = new ArrayList<>();

	private SnomedIsa isa;

	private HashMap<OWLObjectProperty, OWLObjectProperty> chained = new HashMap<>();

	private HashSet<OWLObjectProperty> transitive = new HashSet<>();

	private HashMap<OWLObjectProperty, Set<OWLObjectProperty>> superProps = new HashMap<>();

	private HashMap<OWLClass, List<NecessaryNormalForm>> necessaryNormalForms = new HashMap<>();

	private HashMap<OWLClass, NecessaryNormalForm> necessaryNormalForm = new HashMap<>();

	public List<OWLClass> getConcepts() {
		return concepts;
	}

	public List<NecessaryNormalForm> getNecessaryNormalForms(OWLClass con) {
		return necessaryNormalForms.get(con);
	}

	public HashMap<OWLClass, NecessaryNormalForm> getNecessaryNormalForm() {
		return necessaryNormalForm;
	}

	public NecessaryNormalFormBuilder(SnomedOwlOntology ontology) {
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
		HashSet<OWLClass> visited = new HashSet<>();
		OWLClass root = ontology.getOwlClass(SnomedOwlOntology.root);
		concepts.add(root);
		visited.add(root);
		initConcepts(root, visited);
		HashMap<Long, Set<Long>> superConcepts = new HashMap<>();
		for (OWLClass clazz : ontology.getOntology().getClassesInSignature()) {
			long id = SnomedOwlOntology.getId(clazz);
			superConcepts.put(id, ontology.getSuperClasses(id));
		}
		isa = SnomedIsa.init(superConcepts);
	}

	private void initConcepts(OWLClass con, HashSet<OWLClass> visited) {
		if (concepts.size() % 10000 == 0)
			LOG.info("Concepts: " + concepts.size());
		for (OWLClass sub : ontology.getSubClasses(con)) {
			boolean sups_visited = ontology.getSuperClasses(sub).stream().allMatch(x -> visited.contains(x));
			if (sups_visited) {
				concepts.add(sub);
				visited.add(sub);
				initConcepts(sub, visited);
			}
		}
	}

	private void initRoles() {
		for (OWLSubPropertyChainOfAxiom ax : ontology.getOntology().getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF)) {
			LOG.info("Chain: " + ax);
			if (ax.getPropertyChain().size() != 2)
				throw new UnsupportedOperationException("Unexpected: " + ax);
			OWLObjectProperty prop1 = (OWLObjectProperty) ax.getPropertyChain().get(0);
			OWLObjectProperty prop2 = (OWLObjectProperty) ax.getPropertyChain().get(1);
			OWLObjectProperty sup = (OWLObjectProperty) ax.getSuperProperty();
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
			OWLObjectProperty prop = (OWLObjectProperty) ax.getProperty();
			transitive.add(prop);
		}
		for (OWLObjectProperty prop : transitive) {
			LOG.info("Transitive: " + prop);
		}
		for (OWLObjectProperty prop : ontology.getOntology().getObjectPropertiesInSignature()) {
			superProps.put(prop, new HashSet<>());
			superProps.get(prop).add(prop);
			// TODO Why does this this cause incompleteness warnings
			ontology.getReasoner().getSuperObjectProperties(prop, false).getFlattened()
					.forEach(x -> superProps.get(prop).add((OWLObjectProperty) x));
		}
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

	public void generate(SnomedRoles roles) {
		Reasoner.processingNecessaryNormalForm = true;
		int cnt = 0;
		for (OWLClass concept : concepts) {
			if (++cnt % 1000 == 0)
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
				compare(concept, roles.getRoles(SnomedOwlOntology.getId(concept)), necessaryNormalForms.get(concept));
			}
		}
		Reasoner.processingNecessaryNormalForm = false;
		LOG.info("Mis match: " + mis_match_cnt);
		LOG.info("Mis match ungrouped: " + mis_match_roles_ungrouped_cnt + " roles " + mis_match_props_ungrouped_cnt
				+ " props");
		LOG.info("Mis match grouped: " + mis_match_roles_grouped_cnt + " roles " + mis_match_props_grouped_cnt
				+ " props");
		LOG.info("Mis match grouping issue: " + mis_match_grouping_issue_cnt);
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
		Set<OWLObjectSomeValuesFrom> props = new HashSet<>();
		switch (axiom) {
		case OWLEquivalentClassesAxiom x -> {
			Set<OWLClassExpression> class_exprs = x.getClassExpressionsMinus(concept);
			if (class_exprs.size() != 1)
				throw new UnsupportedOperationException("Unexpected: " + class_exprs.size() + " " + class_exprs);
			OWLClassExpression def = class_exprs.iterator().next();
			props = processDefinition(def);
		}
		case OWLSubClassOfAxiom x -> {
			OWLClassExpression def = x.getSuperClass();
			props = processDefinition(def);
		}
		default -> throw new UnsupportedOperationException("Unexpected: " + axiom.getAxiomType());
		}
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
	private int scoe_unchained_cnt = 0;

	final private boolean check_with_reasoner = false;

	private boolean isSubClassOfEntailed(OWLObjectSomeValuesFrom prop1, OWLObjectSomeValuesFrom prop2) {
		boolean isSubClassOf = false;
//		boolean chain_p = false;
		if (superProps.get((OWLObjectProperty) prop1.getProperty()).contains((OWLObjectProperty) prop2.getProperty())) {
			if (++scoe_cnt % 10000 == 0)
				LOG.info("SCOE: " + scoe_unchained_cnt + " of " + scoe_cnt);
			if (chained.get(prop1.getProperty()) == null && chained.get(prop2.getProperty()) == null
					&& !transitive.contains(prop1.getProperty()) && !transitive.contains(prop2.getProperty())) {
				scoe_unchained_cnt++;
				long con1 = SnomedOwlOntology.getId((OWLClass) prop1.getFiller());
				long con2 = SnomedOwlOntology.getId((OWLClass) prop2.getFiller());
				isSubClassOf = con1 == con2 || isa.hasAncestor(con1, con2);
			} else {
//				chain_p = true;
				OWLSubClassOfAxiom en = ontology.getDataFactory().getOWLSubClassOfAxiom(prop1, prop2);
				isSubClassOf = ontology.getReasoner().isEntailed(en);
			}
		}
		if (check_with_reasoner) {
			OWLSubClassOfAxiom en = ontology.getDataFactory().getOWLSubClassOfAxiom(prop1, prop2);
			if (isSubClassOf != ontology.getReasoner().isEntailed(en))
				throw new RuntimeException("");
		}
		return isSubClassOf;
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

	private int simplify_cnt = 0;

	private void simplify(Set<OWLObjectSomeValuesFrom> props) {
		if (props.isEmpty())
			return;
		if (++simplify_cnt % 10000 == 0)
			LOG.info("Simplify      : " + simplify_cnt);
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

	private int simplify_group_cnt = 0;

	private void simplifyGroup(Set<Set<OWLObjectSomeValuesFrom>> props) {
		if (props.isEmpty())
			return;
		if (++simplify_group_cnt % 1000 == 0)
			LOG.info("Simplify group: " + simplify_group_cnt);
		ArrayList<Set<OWLObjectSomeValuesFrom>> to_remove = new ArrayList<>();
		for (Set<OWLObjectSomeValuesFrom> prop1 : props) {
			for (Set<OWLObjectSomeValuesFrom> prop2 : props) {
				if (prop1 == prop2)
					continue;
				boolean isSubClassOf = prop2.stream()
						.allMatch(p2 -> prop1.stream().anyMatch(p1 -> isSubClassOfEntailed(p1, p2)));
				if (isSubClassOf)
					to_remove.add(prop2);
				if (check_with_reasoner) {
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

	private void compare(OWLClass concept, Set<Role> roles, List<NecessaryNormalForm> nnfs) {
		if (roles == null)
			roles = Set.of();
		NecessaryNormalForm nnf;
		if (nnfs.size() == 1) {
			nnf = nnfs.getFirst();
		} else {
			nnf = new NecessaryNormalForm();
			// TODO subClassOf
			nnf.setSups(new HashSet<>());
			nnf.setUngroupedProps(new HashSet<>());
			nnf.setGroupedProps(new HashSet<>());
			for (NecessaryNormalForm f : nnfs) {
				nnf.getSups().addAll(f.getSups());
				nnf.getUngroupedProps().addAll(f.getUngroupedProps());
				nnf.getGroupedProps().addAll(f.getGroupedProps());
			}
			simplify(nnf.getUngroupedProps());
			simplifyGroup(nnf.getGroupedProps());
		}
		List<Role> roles_ungrouped = roles.stream().filter(x -> x.relationshipGroup == 0).toList();
		Collection<List<Role>> roles_grouped = roles.stream().filter(x -> x.relationshipGroup != 0)
				.collect(Collectors.groupingBy(x -> x.relationshipGroup)).values();
		boolean mis_match = false;
		List<Role> mis_match_roles_grouped = new ArrayList<>();
		Set<OWLObjectSomeValuesFrom> mis_match_props_grouped = new HashSet<>();
		boolean inc;
		inc = false;
		for (Role role : roles_ungrouped) {
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
		for (List<Role> role : roles_grouped) {
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
			LOG.info("NNFs: " + nnfs.size());
			LOG.info("Roles:");
			roles.stream()
					.sorted(Comparator.comparingLong((Role x) -> x.relationshipGroup)
							.thenComparingLong((Role x) -> x.typeId).thenComparingLong((Role x) -> x.destinationId))
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

	private boolean compare(List<Role> roles, Set<OWLObjectSomeValuesFrom> props) {
		return roles.stream().allMatch(role -> props.stream().anyMatch(prop -> compare(role, prop)))
				&& props.stream().allMatch(prop -> roles.stream().anyMatch(role -> compare(role, prop)));
	}

	private boolean compare(Role role, OWLObjectSomeValuesFrom prop) {
		return role.typeId == SnomedOwlOntology.getId((OWLObjectProperty) prop.getProperty())
				&& role.destinationId == SnomedOwlOntology.getId((OWLClass) prop.getFiller());
	}

}
