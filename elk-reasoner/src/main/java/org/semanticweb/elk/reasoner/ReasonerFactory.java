
package org.semanticweb.elk.reasoner;

import org.semanticweb.elk.loading.AxiomLoader;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.managers.ElkObjectEntityRecyclingFactory;
import org.semanticweb.elk.reasoner.config.ReasonerConfiguration;
import org.semanticweb.elk.reasoner.stages.LoggingStageExecutor;
import org.semanticweb.elk.reasoner.stages.ReasonerStageExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main factory to instantiate {@link Reasoner}
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Yevgeny Kazakov
 * @author Peter Skocovsky
 */
public class ReasonerFactory {

	final static Logger LOGGER_ = LoggerFactory
			.getLogger(ReasonerFactory.class);

	final static ReasonerStageExecutor DEFAULT_STAGE_EXECUTOR = new LoggingStageExecutor();

	final static ReasonerInterrupter DEFAULT_INTERRUPTER = new ReasonerInterrupter();

	public Reasoner createReasoner(AxiomLoader.Factory axiomLoaderFactory) {
		return createReasoner(axiomLoaderFactory,
				ReasonerConfiguration.getConfiguration());
	}

	public Reasoner createReasoner(AxiomLoader.Factory axiomLoaderFactory,
			ReasonerConfiguration config) {
		return createReasoner(axiomLoaderFactory, DEFAULT_INTERRUPTER,
				DEFAULT_STAGE_EXECUTOR, config);
	}

	Reasoner createReasoner(AxiomLoader.Factory axiomLoaderFactory,
			final ReasonerInterrupter interrupter,
			ReasonerStageExecutor stageExecutor, ReasonerConfiguration config) {
		return createReasoner(new ElkObjectEntityRecyclingFactory(),
				axiomLoaderFactory, interrupter, stageExecutor, config);
	}

	@SuppressWarnings("static-method")
	Reasoner createReasoner(ElkObject.Factory elkFactory,
			AxiomLoader.Factory axiomLoaderFactory,
			final ReasonerInterrupter interrupter,
			ReasonerStageExecutor stageExecutor, ReasonerConfiguration config) {
		final Reasoner reasoner = createReasoner(elkFactory, interrupter,
				stageExecutor, config);
		reasoner.registerAxiomLoader(axiomLoaderFactory);
		return reasoner;
	}

	@SuppressWarnings("static-method")
	public Reasoner createReasoner(final Reasoner reasoner,
			final ElkObject.Factory elkFactory,
			final ReasonerConfiguration config) {
		return createReasoner(elkFactory, reasoner.getInterrupter(),
				reasoner.getStageExecutor(), config);
	}

	@SuppressWarnings("static-method")
	public Reasoner createReasoner(final ReasonerConfiguration config) {
		return createReasoner(DEFAULT_INTERRUPTER, DEFAULT_STAGE_EXECUTOR,
				config);
	}

	static Reasoner createReasoner(final ReasonerInterrupter interrupter,
			final ReasonerStageExecutor stageExecutor,
			final ReasonerConfiguration config) {
		return createReasoner(new ElkObjectEntityRecyclingFactory(),
				interrupter, stageExecutor, config);
	}

	static Reasoner createReasoner(final ElkObject.Factory elkFactory,
			final ReasonerInterrupter interrupter,
			final ReasonerStageExecutor stageExecutor,
			final ReasonerConfiguration config) {
		return new Reasoner(elkFactory, interrupter, stageExecutor, config);
	}

}
