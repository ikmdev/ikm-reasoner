
package org.semanticweb.elk.loading;

import org.semanticweb.elk.owl.visitors.ElkAxiomProcessor;

/**
 * A {@link AxiomLoader} that consists of two given {@link AxiomLoader}s. When
 * changes are loaded, they are first loaded using the first {@link AxiomLoader}
 * , and then using the second one.
 * 
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 */
public class ComposedAxiomLoader implements AxiomLoader {

	private final AxiomLoader firstLoader_, secondLoader_;

	public ComposedAxiomLoader(AxiomLoader firstLoader,
			AxiomLoader secondLoader) {
		this.firstLoader_ = firstLoader;
		this.secondLoader_ = secondLoader;
	}

	@Override
	public void load(ElkAxiomProcessor axiomInserter,
			ElkAxiomProcessor axiomDeleter) throws ElkLoadingException {
		if (!firstLoader_.isLoadingFinished()) {
			firstLoader_.load(axiomInserter, axiomDeleter);
		}	
		if (firstLoader_.isLoadingFinished()
				&& !secondLoader_.isLoadingFinished()) {
			secondLoader_.load(axiomInserter, axiomDeleter);
		}
	}

	@Override
	public void dispose() {
		firstLoader_.dispose();
		secondLoader_.dispose();
	}

	@Override
	public boolean isLoadingFinished() {
		return firstLoader_.isLoadingFinished()
				&& secondLoader_.isLoadingFinished();
	}

	@Override
	public boolean isInterrupted() {
		// Does not need to check for interruptions.
		return false;
	}

}
