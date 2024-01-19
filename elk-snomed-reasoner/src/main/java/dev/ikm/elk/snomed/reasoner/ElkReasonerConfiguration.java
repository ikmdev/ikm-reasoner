/*
 * #%L
 * ELK OWL API Binding
 * 
 * $Id$
 * $HeadURL$
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
/**
 * 
 */
package dev.ikm.elk.snomed.reasoner;

import org.semanticweb.elk.reasoner.config.ReasonerConfiguration;

import dev.ikm.elk.snomed.owlapix.reasoner.FreshEntityPolicy;
import dev.ikm.elk.snomed.owlapix.reasoner.IndividualNodeSetPolicy;
import dev.ikm.elk.snomed.owlapix.reasoner.NullReasonerProgressMonitor;
import dev.ikm.elk.snomed.owlapix.reasoner.OwlReasonerConfiguration;
import dev.ikm.elk.snomed.owlapix.reasoner.ReasonerProgressMonitor;
import dev.ikm.elk.snomed.owlapix.reasoner.SimpleConfiguration;

/**
 * A composition of the generic OWL API configuration and the ELK's
 * configuration. This is also where the default configuration settings of OWL
 * API reasoner parameters in ELK are defined. The default configuration of ELK
 * reasoner parameters is in {@link ReasonerConfiguration}.
 * 
 * @author Pavel Klinov pavel.klinov@uni-ulm.de
 * @author Markus Kroetzsch
 */
public class ElkReasonerConfiguration {

	private static final long serialVersionUID = 4747024112171682291L;	
	private final ReasonerConfiguration elkConfig;
	private final OwlReasonerConfiguration owlConfig;

	public ElkReasonerConfiguration(OwlReasonerConfiguration owlConfig,
			ReasonerConfiguration elkConfig) {
		this.elkConfig = elkConfig;
		this.owlConfig = owlConfig;
	}

	public ElkReasonerConfiguration() {
		this(getDefaultOwlReasonerConfiguration(), ReasonerConfiguration
				.getConfiguration());
	}

	public ElkReasonerConfiguration(ReasonerProgressMonitor monitor) {
		this(getDefaultOwlReasonerConfiguration(monitor), ReasonerConfiguration
				.getConfiguration());
	}

	public ElkReasonerConfiguration(OwlReasonerConfiguration genConfig) {
		this(genConfig, ReasonerConfiguration.getConfiguration());
	}

	public static OwlReasonerConfiguration getDefaultOwlReasonerConfiguration(
			ReasonerProgressMonitor monitor) {
		return new SimpleConfiguration(monitor, FreshEntityPolicy.ALLOW, 0,
				IndividualNodeSetPolicy.BY_NAME);
	}
	
	public static OwlReasonerConfiguration getDefaultOwlReasonerConfiguration() {
		return getDefaultOwlReasonerConfiguration(
				new NullReasonerProgressMonitor());
	}

//	@Override
	public FreshEntityPolicy getFreshEntityPolicy() {
		return owlConfig.getFreshEntityPolicy();
	}

//	@Override
	public IndividualNodeSetPolicy getIndividualNodeSetPolicy() {
		return owlConfig.getIndividualNodeSetPolicy();
	}

//	@Override
	public ReasonerProgressMonitor getProgressMonitor() {
		return owlConfig.getProgressMonitor();
	}

//	@Override
	public long getTimeOut() {
		return owlConfig.getTimeOut();
	}

	public ReasonerConfiguration getElkConfiguration() {
		return elkConfig;
	}
}