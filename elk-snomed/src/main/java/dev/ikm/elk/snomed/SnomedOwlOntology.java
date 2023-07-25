package dev.ikm.elk.snomed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.owlapi.OWLAPIConfigProvider;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.functional.parser.OWLFunctionalSyntaxOWLParser;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

public class SnomedOwlOntology {

	private OWLOntology ontology;

	private OWLReasoner reasoner;

	public OWLOntology getOntology() {
		return ontology;
	}

	public OWLReasoner getReasoner() {
		return reasoner;
	}

	private SnomedOwlOntology(OWLOntology ontology) {
		this.ontology = ontology;
	}

	public static List<String> readAxioms(Path file) throws IOException {
		// id effectiveTime active moduleId refsetId referencedComponentId owlExpression
		return Files.lines(file).skip(1).map(line -> line.split("\\t")) //
				.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
				.map(fields -> fields[6]) //
				.collect(Collectors.toList());
	}

	public static HashMap<Long, Set<Long>> readIsa(Path file) throws IOException {
		HashMap<Long, Set<Long>> isas = new HashMap<>();
		// id effectiveTime active moduleId sourceId destinationId relationshipGroup
		// typeId characteristicTypeId modifierId
		//
		// 116680003 |Is a (attribute)|
		Files.lines(file).skip(1).map(line -> line.split("\\t")) //
		.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
		.filter(fields -> Long.parseLong(fields[7]) == 116680003) // typeId
		.forEach(fields -> {
			long con = Long.parseLong(fields[4]); // sourceId
			long par = Long.parseLong(fields[5]); // destinationId
			isas.computeIfAbsent(con, x -> new HashSet<>());
			isas.get(con).add(par);
		});
		return isas;
	}

	public static List<String> getPrefixDeclaration(List<String> lines) {
		return lines.stream().filter(line -> line.startsWith("Prefix")).collect(Collectors.toList());
	}

	public static String getOntologyDeclaration(List<String> lines) {
		return lines.stream().filter(line -> line.startsWith("Ontology")).findFirst().get();
	}

	public static List<String> readOntology(Path file) throws IOException {
		List<String> lines = readAxioms(file);
		List<String> prefix = getPrefixDeclaration(lines);
		lines.removeAll(prefix);
		String ontology = getOntologyDeclaration(lines);
		lines.remove(ontology);
		ontology = ontology.replace(")", "");
		lines.add(0, ontology);
		lines.addAll(0, prefix);
		lines.add(")");
		return lines;
	}

	public static SnomedOwlOntology createOntology() throws Exception {
		OWLOntologyManager mgr = OWLManager.createOWLOntologyManager();
		return new SnomedOwlOntology(mgr.createOntology());
	}

	public void loadOntology(Path file) throws Exception {
		List<String> lines = readOntology(file);
		OWLOntologyLoaderConfiguration config = new OWLAPIConfigProvider().get();
		new OWLFunctionalSyntaxOWLParser().parse(new StringDocumentSource(String.join("\n", lines)), ontology, config);
	}

	public void classify() {
		OWLReasonerFactory rf = (OWLReasonerFactory) new ElkReasonerFactory();
		reasoner = rf.createReasoner(ontology);
		reasoner.flush();
		reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
	}

}
