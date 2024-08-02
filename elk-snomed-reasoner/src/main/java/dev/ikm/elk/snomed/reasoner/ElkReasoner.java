/*
 * #%L
 * elk-reasoner
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 Department of Computer Science, University of Oxford
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
 * @author Yevgeny Kazakov, Jun 28, 2011
 */
package dev.ikm.elk.snomed.reasoner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.exceptions.ElkRuntimeException;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.reasoner.DummyProgressMonitor;
import org.semanticweb.elk.reasoner.ProgressMonitor;
import org.semanticweb.elk.reasoner.Reasoner;
import org.semanticweb.elk.reasoner.ReasonerFactory;
import org.semanticweb.elk.reasoner.completeness.IncompleteResult;
import org.semanticweb.elk.reasoner.completeness.Incompleteness;
import org.semanticweb.elk.reasoner.config.ReasonerConfiguration;
import org.semanticweb.elk.reasoner.query.EntailmentQueryConverter;
import org.semanticweb.elk.reasoner.query.QueryResult;
import org.semanticweb.elk.reasoner.query.VerifiableQueryResult;
import org.semanticweb.elk.reasoner.stages.ElkInterruptedException;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNode;
import org.semanticweb.elk.util.logging.LogLevel;
import org.semanticweb.elk.util.logging.LoggerWrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import dev.ikm.elk.snomed.owlapix.model.OWLOntology;
import dev.ikm.elk.snomed.owlapix.model.OWLOntologyChange;
import dev.ikm.elk.snomed.owlapix.model.OWLOntologyChangeListener;
import dev.ikm.elk.snomed.owlapix.model.OWLOntologyChangeProgressListener;
import dev.ikm.elk.snomed.owlapix.model.OWLOntologyManager;
import dev.ikm.elk.snomed.owlapix.reasoner.BufferingMode;
import dev.ikm.elk.snomed.owlapix.reasoner.FreshEntityPolicy;
import dev.ikm.elk.snomed.owlapix.reasoner.IndividualNodeSetPolicy;
import dev.ikm.elk.snomed.owlapix.reasoner.InferenceType;

/**
 * Derived from the OWLReasoner interface implementation for ELK
 * {@link Reasoner}.
 * 
 * Implemented using Elk objects and some OWL API derived classes, eliminating
 * use of the full OWL API implementation.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * @author Peter Skocovsky
 */
public class ElkReasoner {
	// logger for this class
	private static final Logger LOGGER_ = LoggerFactory.getLogger(ElkReasoner.class);
	private static final Marker MARKER_UNSUPPORTED_METHOD_ = MarkerFactory.getMarker("owlapi.unsupportedMethod");

	// OWL API related objects
	private final OWLOntology owlOntology_;
	private final OWLOntologyManager owlOntologymanager_;
	/**
	 * ELK progress monitor to display progress of main reasoning tasks, e.g.,
	 * classification
	 */
	private final ProgressMonitor mainProgressMonitor_;
	/**
	 * ELK progress monitor to display progress of other reasoning tasks, e.g.,
	 * satisfiability checking
	 */
	private final ProgressMonitor secondaryProgressMonitor_;
	/**
	 * {@code true} iff the buffering mode for reasoner is
	 * {@link BufferingMode#BUFFERING}
	 */
	private final boolean isBufferingMode_;
	/** listener to implement addition and removal of axioms */
	private final OntologyChangeListener ontologyChangeListener_;
	/** listener to keep track of when changes are applied to the ontology */
	private final OntologyChangeProgressListener ontologyChangeProgressListener_;
	/** ELK object factory used to create any ElkObjects */
	private final ElkObject.Factory objectFactory_;
	/** this object is used to load pending changes */
	private volatile OwlChangesLoaderFactory bufferedChangesLoader_;
	/** configurations required for ELK reasoner */
	private ReasonerConfiguration config_;
	private final boolean isAllowFreshEntities;
	/** the ELK reasoner instance used for reasoning */
	private Reasoner reasoner_;
	/** listeners that monitor changes in some of the methods */
	private final List<ChangeListener> changeListeners_ = new ArrayList<ChangeListener>();

	/**
	 * {@code true} if the ontology should be loaded before any changes are applied
	 * to the ontology (used only for buffering mode)
	 */
	private boolean loadBeforeChanges_ = true;
	/**
	 * {@code true} if it is required to reload the whole ontology next time the
	 * changes should be flushed
	 */
	private boolean ontologyReloadRequired_;

	private ElkReasoner(OWLOntology ontology, boolean isBufferingMode, ElkReasonerConfiguration elkConfig,
			final Reasoner internalReasoner) {
		this.owlOntology_ = ontology;
		this.owlOntologymanager_ = ontology.getOWLOntologyManager();
		this.mainProgressMonitor_ = elkConfig.getProgressMonitor() == null ? new DummyProgressMonitor()
				: new ElkReasonerProgressMonitor(elkConfig.getProgressMonitor());
		this.secondaryProgressMonitor_ = new DummyProgressMonitor();
		this.isBufferingMode_ = isBufferingMode;
		this.ontologyChangeListener_ = new OntologyChangeListener();
		this.owlOntologymanager_.addOntologyChangeListener(ontologyChangeListener_);
		this.ontologyChangeProgressListener_ = new OntologyChangeProgressListener();
		this.owlOntologymanager_.addOntologyChangeProgessListener(ontologyChangeProgressListener_);
		this.objectFactory_ = internalReasoner.getElkFactory();

		this.config_ = elkConfig.getElkConfiguration();
		this.isAllowFreshEntities = elkConfig.getFreshEntityPolicy() == FreshEntityPolicy.ALLOW;

		initReasoner(internalReasoner);
		this.bufferedChangesLoader_ = new OwlChangesLoaderFactory(this.mainProgressMonitor_);
		if (!isBufferingMode_) {
			// register the change loader only in non-buffering mode;
			// in buffering mode the loader is registered only when
			// changes are flushed
			reasoner_.registerAxiomLoader(bufferedChangesLoader_);
		}
		this.ontologyReloadRequired_ = false;
	}

//	ElkReasoner(OWLOntology ontology, boolean isBufferingMode, ElkReasonerConfiguration elkConfig) {
//		// this(ontology, isBufferingMode, elkConfig,
//		// new ReasonerFactory().createReasoner(elkConfig.getElkConfiguration()));
//		this(ontology, isBufferingMode, elkConfig, new ReasonerFactory()
//				.createReasoner(((OwlxOntology) ontology).getObjectFactory(), elkConfig.getElkConfiguration()));
//	}

	public static ElkReasoner createReasoner(OWLOntology ontology, ElkObject.Factory factory) {
		ElkReasonerConfiguration elkConfig = new ElkReasonerConfiguration();
		Reasoner reasoner = new ReasonerFactory().createReasoner(factory, elkConfig.getElkConfiguration());
		return new ElkReasoner(ontology, true, elkConfig, reasoner);
	}

	OWLOntology getOWLOntology() {
		return owlOntology_;
	}

	ElkObject.Factory getElkObjectFactory() {
		return objectFactory_;
	}

	private void initReasoner(final Reasoner reasoner) {
		this.reasoner_ = reasoner;
		this.reasoner_.registerAxiomLoader(new OwlOntologyLoader.Factory(owlOntology_, this.mainProgressMonitor_));
		this.reasoner_.setAllowFreshEntities(isAllowFreshEntities);
		// use the secondary progress monitor by default, when necessary, we
		// switch to the primary progress monitor; this is to avoid bugs with
		// progress monitors in Protege
		this.reasoner_.setProgressMonitor(this.secondaryProgressMonitor_);
	}

	/**
	 * @return the ELK reasoner used internally in this OWL API wrapper.
	 */
	public Reasoner getInternalReasoner() {
		return reasoner_;
	}

	public ReasonerConfiguration getConfigurationOptions() {
		return config_;
	}

	public void setConfigurationOptions(ReasonerConfiguration config) {
		this.config_ = config;
		reasoner_.setConfigurationOptions(config);
	}

	/**
	 * Logs a warning message for unsupported OWL API method
	 * 
	 * @param method the method which is not supported
	 * @return the {@link UnsupportedOperationException} saying that this method is
	 *         not supported
	 */
	private static UnsupportedOperationException unsupportedOwlApiMethod(String method) {
		String message = "OWL API reasoner method is not implemented: " + method + ".";
		/*
		 * TODO: The method String can be used to create more specific message types,
		 * but with the current large amount of unsupported methods and non-persistent
		 * settings for ignoring them, we better use only one message type to make it
		 * easier to ignore them.
		 */
		LoggerWrap.log(LOGGER_, LogLevel.WARN, MARKER_UNSUPPORTED_METHOD_, message);
		return new UnsupportedOperationException(message);
	}

	/**
	 * Logs a warning message for unsupported case of an OWL API method
	 * 
	 * @param method the method which some case is not supported
	 * @param reason the reason why the case is not supported
	 * @return the {@link UnsupportedOperationException} saying that this method is
	 *         not supported
	 */
	@SuppressWarnings("unused")
	private static UnsupportedOperationException unsupportedOwlApiMethod(String method, String reason) {
		String message = "OWL API reasoner method is not fully implemented: " + method + ": " + reason;
		LoggerWrap.log(LOGGER_, LogLevel.WARN, MARKER_UNSUPPORTED_METHOD_, message);
		return new UnsupportedOperationException(message);
	}

	private IncompleteResult<? extends Node<ElkClass>> getClassNode(ElkClass elkClass) throws ElkException {
		return reasoner_.getEquivalentClasses(elkClass);
	}

	private IncompleteResult<? extends TaxonomyNode<ElkObjectProperty>> getObjectPropertyNode(
			final ElkObjectProperty elkClass) throws ElkException {
		return reasoner_.getObjectPropertyNode(elkClass);
	}

	/**
	 * 
	 * @throws ElkInterruptedException if the reasoner is in the interrupted state
	 */
	private void checkInterrupted() throws ElkInterruptedException {
		reasoner_.checkInterrupt();
	}

	/* Methods required by the OWLReasoner interface */

//	@Override
	public void dispose() {
		LOGGER_.trace("dispose()");
		owlOntologymanager_.removeOntologyChangeListener(ontologyChangeListener_);
		owlOntologymanager_.removeOntologyChangeProgessListener(ontologyChangeProgressListener_);
		for (;;) {
			try {
				if (!reasoner_.shutdown())
					throw new ElkRuntimeException("Failed to shut down ELK!");
				break;
			} catch (InterruptedException e) {
				continue;
			}
		}

	}

//	@Override
	public void flush() {
		LOGGER_.trace("flush()");
		if (ontologyReloadRequired_) {
			/**
			 * re-creates a new instance of reasoner for the parameters, since a reasoner
			 * can do initial load only once
			 */
			initReasoner(new ReasonerFactory().createReasoner(reasoner_, objectFactory_, config_));
			bufferedChangesLoader_ = new OwlChangesLoaderFactory(this.secondaryProgressMonitor_);
			ontologyReloadRequired_ = false;
		} else if (bufferedChangesLoader_.isLoadingFinished()) {
			// no changes
			return;
		} else if (isBufferingMode_) {
			// in buffering mode, new changes need to be buffered
			// separately in order not to mix them with the flushed
			// changes that now need to be loaded
			reasoner_.registerAxiomLoader(bufferedChangesLoader_);
			bufferedChangesLoader_ = new OwlChangesLoaderFactory(this.secondaryProgressMonitor_);
		}
		// notify about the changes
		for (ChangeListener listener : changeListeners_) {
			listener.ontologyChanged();
		}
	}

	public IncompleteResult<? extends Node<ElkClass>> computeBottomClassNode() throws ElkException {
		checkInterrupted();
		return getClassNode(objectFactory_.getOwlNothing());
	}

//	@Override
	public Node<ElkClass> getBottomClassNode() throws ElkException {
		LOGGER_.trace("getBottomClassNode()");
		checkInterrupted();
		return Incompleteness.getValue(computeBottomClassNode());
	}

//	@Override
	public Node<ElkDataProperty> getBottomDataPropertyNode() throws ElkException {
		LOGGER_.trace("getBottomDataPropertyNode()");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getBottomDataPropertyNode()");
	}

	public IncompleteResult<? extends TaxonomyNode<ElkObjectProperty>> computeBottomObjectPropertyNode()
			throws ElkException {
		checkInterrupted();
		return getObjectPropertyNode(objectFactory_.getOwlBottomObjectProperty());
	}

//	@Override
	public TaxonomyNode<ElkObjectProperty> getBottomObjectPropertyNode() throws ElkException {
		LOGGER_.trace("getBottomObjectPropertyNode()");
		return Incompleteness.getValue(computeBottomObjectPropertyNode());
	}

//	@Override
	public BufferingMode getBufferingMode() {
		LOGGER_.trace("getBufferingMode()");
		return isBufferingMode_ ? BufferingMode.BUFFERING : BufferingMode.NON_BUFFERING;
	}

//	@Override
	public Set<ElkClass> getDataPropertyDomains(ElkDataProperty arg0, boolean arg1) throws ElkException {
		LOGGER_.trace("getDataPropertyDomains(ElkDataProperty, boolean)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getDataPropertyDomains(ElkDataProperty, boolean)");
	}

//	@Override
	public Set<ElkLiteral> getDataPropertyValues(ElkNamedIndividual arg0, ElkDataProperty arg1) throws ElkException {
		LOGGER_.trace("getDataPropertyValues(ElkNamedIndividual, ElkDataProperty)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getDataPropertyValues(ElkNamedIndividual, ElkDataProperty)");
	}

//	@Override
	public Set<ElkNamedIndividual> getDifferentIndividuals(ElkNamedIndividual arg0) throws ElkException {
		LOGGER_.trace("getDifferentIndividuals(ElkNamedIndividual)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getDifferentIndividuals(ElkNamedIndividual)");
	}

//	@Override
	public Set<ElkClass> getDisjointClasses(ElkClassExpression arg0) throws ElkException {
		LOGGER_.trace("getDisjointClasses(ElkClassExpression)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getDisjointClasses(ElkClassExpression)");
	}

//	@Override
	public Set<ElkDataProperty> getDisjointDataProperties(ElkDataPropertyExpression arg0) throws ElkException {
		LOGGER_.trace("getDisjointDataProperties(ElkDataPropertyExpression)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getDisjointDataProperties(ElkDataPropertyExpression)");
	}

//	@Override
	public Set<ElkObjectPropertyExpression> getDisjointObjectProperties(ElkObjectPropertyExpression arg0)
			throws ElkException {
		LOGGER_.trace("getDisjointObjectProperties(ElkObjectPropertyExpression)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getDisjointObjectProperties(ElkObjectPropertyExpression)");
	}

	public IncompleteResult<? extends Node<ElkClass>> computeEquivalentClasses(ElkClassExpression ce)
			throws ElkException {
		checkInterrupted();
		return reasoner_.getEquivalentClasses(ce);
	}

//	@Override
	public Node<ElkClass> getEquivalentClasses(ElkClassExpression ce) throws ElkException {
		LOGGER_.trace("getEquivalentClasses(ElkClassExpression)");
		return Incompleteness.getValue(computeEquivalentClasses(ce));
	}

//	@Override
	public Node<ElkDataProperty> getEquivalentDataProperties(ElkDataProperty arg0) throws ElkException {
		LOGGER_.trace("getEquivalentDataProperties(ElkDataProperty)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getEquivalentDataProperties(ElkDataProperty)");
	}

	public IncompleteResult<? extends TaxonomyNode<ElkObjectProperty>> computeEquivalentObjectProperties(
			final ElkObjectProperty pe) throws ElkException {
		LOGGER_.trace("getEquivalentObjectProperties(ElkObjectPropertyExpression)");
		checkInterrupted();
		return reasoner_.getObjectPropertyNode(pe);
	}

//	@Override
	public TaxonomyNode<ElkObjectProperty> getEquivalentObjectProperties(final ElkObjectPropertyExpression pe)
			throws ElkException {
		LOGGER_.trace("getEquivalentObjectProperties(ElkObjectPropertyExpression)");
		if (pe instanceof ElkObjectProperty) {
			return Incompleteness.getValue(computeEquivalentObjectProperties((ElkObjectProperty) pe));
		}
		throw unsupportedOwlApiMethod("getEquivalentObjectProperties(ElkObjectPropertyExpression)");
	}

//	@Override
	public FreshEntityPolicy getFreshEntityPolicy() {
		LOGGER_.trace("getFreshEntityPolicy()");
		return reasoner_.getAllowFreshEntities() ? FreshEntityPolicy.ALLOW : FreshEntityPolicy.DISALLOW;
	}

//	@Override
	public IndividualNodeSetPolicy getIndividualNodeSetPolicy() {
		LOGGER_.trace("getIndividualNodeSetPolicy()");
		return IndividualNodeSetPolicy.BY_NAME;
	}

	public IncompleteResult<? extends Set<? extends Node<ElkNamedIndividual>>> computeInstances(ElkClassExpression ce,
			boolean direct) throws ElkException {
		checkInterrupted();
		return reasoner_.getInstances(ce, direct);
	}

//	@Override
	public Set<? extends Node<ElkNamedIndividual>> getInstances(ElkClassExpression ce, boolean direct)
			throws ElkException {
		LOGGER_.trace("getInstances(ElkClassExpression, boolean)");
		checkInterrupted();
		return Incompleteness.getValue(computeInstances(ce, direct));
	}

//	@Override
	public Node<ElkObjectPropertyExpression> getInverseObjectProperties(ElkObjectPropertyExpression arg0)
			throws ElkException {
		LOGGER_.trace("getInverseObjectProperties(ElkObjectPropertyExpression)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getInverseObjectProperties(ElkObjectPropertyExpression)");
	}

//	@Override
	public Set<ElkClass> getObjectPropertyDomains(ElkObjectPropertyExpression arg0, boolean arg1) throws ElkException {
		LOGGER_.trace("getObjectPropertyDomains(ElkObjectPropertyExpression, boolean)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getObjectPropertyDomains(ElkObjectPropertyExpression, boolean)");
	}

//	@Override
	public Set<ElkClass> getObjectPropertyRanges(ElkObjectPropertyExpression arg0, boolean arg1) throws ElkException {
		LOGGER_.trace("getObjectPropertyRanges(ElkObjectPropertyExpression, boolean)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getObjectPropertyRanges(ElkObjectPropertyExpression, boolean)");
	}

//	@Override
	public Set<ElkNamedIndividual> getObjectPropertyValues(ElkNamedIndividual arg0, ElkObjectPropertyExpression arg1)
			throws ElkException {
		LOGGER_.trace("getObjectPropertyValues(ElkNamedIndividual, ElkObjectPropertyExpression)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getObjectPropertyValues(ElkNamedIndividual, ElkObjectPropertyExpression)");
	}

//	@Override
	public Set<ElkAxiom> getPendingAxiomAdditions() {
		LOGGER_.trace("getPendingAxiomAdditions()");
		return bufferedChangesLoader_.getPendingAxiomAdditions();
	}

//	@Override
	public Set<ElkAxiom> getPendingAxiomRemovals() {
		LOGGER_.trace("getPendingAxiomRemovals()");
		return bufferedChangesLoader_.getPendingAxiomRemovals();
	}

//	@Override
	public List<OWLOntologyChange> getPendingChanges() {
		LOGGER_.trace("getPendingChanges()");
		return bufferedChangesLoader_.getPendingChanges();
	}

//	@Override
	public Set<InferenceType> getPrecomputableInferenceTypes() {
		LOGGER_.trace("getPrecomputableInferenceTypes()");
		return new HashSet<InferenceType>(Arrays.asList(InferenceType.CLASS_ASSERTIONS, InferenceType.CLASS_HIERARCHY,
				InferenceType.OBJECT_PROPERTY_HIERARCHY));
	}

//	@Override
	public String getReasonerName() {
		LOGGER_.trace("getReasonerName()");
		return ElkReasoner.class.getPackage().getImplementationTitle();
	}

//	@Override
	public String getReasonerVersion() {
		LOGGER_.trace("getReasonerVersion()");
		String versionString = ElkReasoner.class.getPackage().getImplementationVersion();
//		String[] splitted;
//		int filled = 0;
//		int version[] = new int[4];
//		if (versionString != null) {
//			splitted = versionString.replaceAll("[^\\d.]", "").split("\\.");
//			while (filled < splitted.length && filled < version.length) {
//				String part = splitted[filled];
//				if (part.length() > 8) {
//					part = part.substring(0, 8);
//				}
//				version[filled] = Integer.parseInt(part);
//				filled++;
//			}
//		}
//		while (filled < version.length) {
//			version[filled] = 0;
//			filled++;
//		}
//		return new Version(version[0], version[1], version[2], version[3]);
		return versionString;
	}

//	@Override
	public OWLOntology getRootOntology() {
		LOGGER_.trace("getRootOntology()");
		return owlOntology_;
	}

//	@Override
	public ElkNamedIndividual getSameIndividuals(ElkNamedIndividual arg0) throws ElkException {
		LOGGER_.trace("getSameIndividuals(ElkNamedIndividual)");
		checkInterrupted();
		// TODO This needs to be updated when we support nominals
//		return new OWLNamedIndividualNode(arg0);
		return arg0;
	}

	public IncompleteResult<? extends Set<? extends Node<ElkClass>>> computeSubClasses(ElkClassExpression ce,
			boolean direct) throws ElkException {
		checkInterrupted();
		return reasoner_.getSubClasses(ce, direct);
	}

//	@Override
	public Set<? extends Node<ElkClass>> getSubClasses(ElkClassExpression ce, boolean direct) throws ElkException {
		LOGGER_.trace("getSubClasses(ElkClassExpression, boolean)");
		return Incompleteness.getValue(computeSubClasses(ce, direct));
	}

//	@Override
	public Set<ElkDataProperty> getSubDataProperties(ElkDataProperty arg0, boolean arg1) throws ElkException {
		LOGGER_.trace("getSubDataProperties(ElkDataProperty, boolean)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getSubDataProperties(ElkDataProperty, boolean)");
	}

	public IncompleteResult<? extends Set<? extends Node<ElkObjectProperty>>> computeSubObjectProperties(
			final ElkObjectProperty pe, final boolean direct) throws ElkException {
		checkInterrupted();
		return reasoner_.getSubObjectProperties(pe, direct);
	}

//	@Override
	public Set<? extends Node<ElkObjectProperty>> getSubObjectProperties(final ElkObjectPropertyExpression pe,
			final boolean direct) throws ElkException {
		LOGGER_.trace("getSubObjectProperties(ElkObjectPropertyExpression, boolean)");
		if (pe instanceof ElkObjectProperty) {
			return Incompleteness.getValue(computeSubObjectProperties((ElkObjectProperty) pe, direct));
		}
		throw unsupportedOwlApiMethod("getSubObjectProperties(ElkObjectPropertyExpression, boolean)");
	}

	public IncompleteResult<? extends Set<? extends Node<ElkClass>>> computeSuperClasses(ElkClassExpression ce,
			boolean direct) throws ElkException {
		checkInterrupted();
		return reasoner_.getSuperClasses(ce, direct);
	}

//	@Override
	public Set<? extends Node<ElkClass>> getSuperClasses(ElkClassExpression ce, boolean direct) throws ElkException {
		LOGGER_.trace("getSuperClasses(ElkClassExpression, boolean)");
		return Incompleteness.getValue(computeSuperClasses(ce, direct));
	}

//	@Override
	public Set<ElkDataProperty> getSuperDataProperties(ElkDataProperty arg0, boolean arg1) throws ElkException {
		LOGGER_.trace("getSuperDataProperties(ElkDataProperty, boolean)");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getSuperDataProperties(ElkDataProperty, boolean)");
	}

	public IncompleteResult<? extends Set<? extends Node<ElkObjectProperty>>> computeSuperObjectProperties(
			final ElkObjectProperty pe, final boolean direct) throws ElkException {
		checkInterrupted();
		return reasoner_.getSuperObjectProperties(pe, direct);
	}

//	@Override
	public Set<? extends Node<ElkObjectProperty>> getSuperObjectProperties(final ElkObjectPropertyExpression pe,
			final boolean direct) throws ElkException {
		LOGGER_.trace("getSuperObjectProperties(ElkObjectPropertyExpression, boolean)");
		if (pe instanceof ElkObjectProperty) {
			return Incompleteness.getValue(computeSuperObjectProperties((ElkObjectProperty) pe, direct));
		}
		throw unsupportedOwlApiMethod("getSuperObjectProperties(ElkObjectPropertyExpression, boolean)");
	}

//	@Override
	public long getTimeOut() {
		LOGGER_.trace("getTimeOut()");
		// TODO Auto-generated method stub
		return 0;
	}

	public IncompleteResult<? extends Node<ElkClass>> computeTopClassNode() throws ElkException {
		checkInterrupted();
		return getClassNode(objectFactory_.getOwlThing());
	}

//	@Override
	public Node<ElkClass> getTopClassNode() throws ElkException {
		LOGGER_.trace("getTopClassNode()");
		checkInterrupted();
		return Incompleteness.getValue(computeTopClassNode());
	}

//	@Override
	public Node<ElkDataProperty> getTopDataPropertyNode() throws ElkException {
		LOGGER_.trace("getTopDataPropertyNode()");
		checkInterrupted();
		// TODO Provide implementation
		throw unsupportedOwlApiMethod("getTopDataPropertyNode()");
	}

	public IncompleteResult<? extends TaxonomyNode<ElkObjectProperty>> computeTopObjectPropertyNode()
			throws ElkException {
		checkInterrupted();
		return getObjectPropertyNode(objectFactory_.getOwlTopObjectProperty());
	}

//	@Override
	public TaxonomyNode<ElkObjectProperty> getTopObjectPropertyNode() throws ElkException {
		LOGGER_.trace("getTopObjectPropertyNode()");
		return Incompleteness.getValue(computeTopObjectPropertyNode());
	}

	public IncompleteResult<? extends Set<? extends Node<ElkClass>>> computeTypes(ElkNamedIndividual ind,
			boolean direct) throws ElkException {
		checkInterrupted();
		return reasoner_.getTypes(ind, direct);
	}

//	@Override
	public Set<? extends Node<ElkClass>> getTypes(ElkNamedIndividual ind, boolean direct) throws ElkException {
		LOGGER_.trace("getTypes(ElkNamedIndividual, boolean)");
		return Incompleteness.getValue(computeTypes(ind, direct));
	}

	public IncompleteResult<? extends Node<ElkClass>> computeUnsatisfiableClasses() throws ElkException {
		checkInterrupted();
		return getClassNode(objectFactory_.getOwlNothing());
	}

//	@Override
	public Node<ElkClass> getUnsatisfiableClasses() throws ElkException {
		LOGGER_.trace("getUnsatisfiableClasses()");
		checkInterrupted();
		return Incompleteness.getValue(computeUnsatisfiableClasses());
	}

//	@Override
	public void interrupt() {
		LOGGER_.trace("interrupt()");
		reasoner_.interrupt();
	}

	public IncompleteResult<Boolean> checkIsConsistent() throws ElkException {
		return reasoner_.isInconsistent().map(res -> !res);
	}

//	@Override
	public boolean isConsistent() throws ElkException {
		LOGGER_.trace("isConsistent()");
		return Incompleteness.getValue(checkIsConsistent());
	}

	public IncompleteResult<Boolean> checkEntailment(final ElkAxiom elkAxiom) throws ElkException {
		QueryResult entailment = reasoner_.checkEntailment(elkAxiom);
		return new IncompleteResult<Boolean>(entailment.entailmentProved(), entailment.getIncompletenessMonitor());
	}

//	@Override
	public boolean isEntailed(final ElkAxiom owlAxiom) throws ElkException {
		LOGGER_.trace("isEntailed(ElkAxiom)");
		checkInterrupted();
		return Incompleteness.getValue(checkEntailment(owlAxiom));
	}

//	@Override
	public boolean isEntailed(final Set<? extends ElkAxiom> owlAxioms) throws ElkException {
		LOGGER_.trace("isEntailed(Set<? extends ElkAxiom>)");
		checkInterrupted();
		Map<ElkAxiom, VerifiableQueryResult> results = reasoner_.checkEntailment(owlAxioms);
		for (final VerifiableQueryResult result : results.values()) {
			if (!Incompleteness.getValue(result)) {
				return false;
			}
		}
		return true;
	}

//	@Override
//	public boolean isEntailmentCheckingSupported(final AxiomType<?> axiomType) {
	public boolean isEntailmentCheckingSupported(Class<? extends ElkObject> elkAxiomClass) {
		if (elkAxiomClass == null || !ElkAxiom.class.isAssignableFrom(elkAxiomClass)) {
			// not supported
			return false;
		}
		// else
		return EntailmentQueryConverter.isEntailmentCheckingSupported(elkAxiomClass.asSubclass(ElkAxiom.class));
	}

//	@Override
	public boolean isPrecomputed(InferenceType inferenceType) {
		LOGGER_.trace("isPrecomputed(InferenceType)");
		if (inferenceType.equals(InferenceType.CLASS_HIERARCHY))
			return reasoner_.doneTaxonomy();
		if (inferenceType.equals(InferenceType.CLASS_ASSERTIONS))
			return reasoner_.doneInstanceTaxonomy();
		if (inferenceType.equals(InferenceType.OBJECT_PROPERTY_HIERARCHY)) {
			return reasoner_.doneObjectPropertyTaxonomy();
		}

		return false;
	}

	public IncompleteResult<? extends Boolean> checkSatisfiability(ElkClassExpression classExpression)
			throws ElkException {
		checkInterrupted();
		return reasoner_.isSatisfiable(classExpression);
	}

//	@Override
	public boolean isSatisfiable(ElkClassExpression classExpression) throws ElkException {
		LOGGER_.trace("isSatisfiable(ElkClassExpression)");
		return Incompleteness.getValue(checkSatisfiability(classExpression));
	}

//	@Override
	public void precomputeInferences(InferenceType... inferenceTypes) throws ElkException {
		LOGGER_.trace("precomputeInferences(InferenceType...)");
		checkInterrupted();
		// we use the main progress monitor only here
		this.reasoner_.setProgressMonitor(this.mainProgressMonitor_);
		try {
			for (InferenceType inferenceType : inferenceTypes) {
				if (inferenceType.equals(InferenceType.CLASS_HIERARCHY))
					reasoner_.getTaxonomy();
				else if (inferenceType.equals(InferenceType.CLASS_ASSERTIONS))
					reasoner_.getInstanceTaxonomy();
				else if (inferenceType.equals(InferenceType.OBJECT_PROPERTY_HIERARCHY)) {
					reasoner_.getObjectPropertyTaxonomy();
				}
			}
		} finally {
			this.reasoner_.setProgressMonitor(this.secondaryProgressMonitor_);
		}

	}

	public void addListener(ChangeListener listener) {
		changeListeners_.add(listener);
	}

	public void removeListener(ChangeListener listener) {
		changeListeners_.remove(listener);
	}

	protected class OntologyChangeListener implements OWLOntologyChangeListener {
		@Override
		public void ontologiesChanged(List<? extends OWLOntologyChange> changes) {
			Set<OWLOntology> importClosure = null;
			for (OWLOntologyChange change : changes) {
				OWLOntology changedOntology = change.getOntology();
				if (!changedOntology.equals(owlOntology_)) {
					if (importClosure == null) {
						importClosure = owlOntology_.getImportsClosure();
					}
					if (!importClosure.contains(changedOntology)) {
						LOGGER_.trace("Ignoring the change not applicable to the current ontology: {}" + change);
						continue;
					}
				}

				if (!change.isAxiomChange()) {
					LOGGER_.trace("Non-axiom change: {}\n The ontology will be reloaded.", change);
					// cannot handle non-axiom changes incrementally
					ontologyReloadRequired_ = true;
				} else {
					bufferedChangesLoader_.registerChange(change);
				}
			}
			if (!isBufferingMode_)
				flush();
		}

	}

	private class OntologyChangeProgressListener implements OWLOntologyChangeProgressListener {

		private static final long serialVersionUID = -609834181047406971L;

		@Override
		public void begin(int size) {
			if (isBufferingMode_ && loadBeforeChanges_) {
				try {
					LOGGER_.trace("force initial loading");
					reasoner_.ensureLoading();
					loadBeforeChanges_ = false;
				} catch (ElkException e) {
					throw new ElkRuntimeException(e);
				}
			}
		}

		@Override
		public void appliedChange(OWLOntologyChange change) {
			// nothing to do

		}

		@Override
		public void end() {
			// nothing to do
		}
	}

	/**
	 * A listener to monitor changes for {@link ElkReasoner}
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public static interface ChangeListener {

		/**
		 * Called when the ontology maintained by the reasoner has changed and the
		 * changes were made visible to the reasoner (e.g., by calling
		 * {@link flush()}). This signals that the logical entailments
		 * produced by the reasoner may change. Note that if the reasoner buffering mode
		 * is {@link BufferingMode#NON_BUFFERING} then this method is called only after
		 * {@link flush()} is called.
		 */
		void ontologyChanged();

	}

}
