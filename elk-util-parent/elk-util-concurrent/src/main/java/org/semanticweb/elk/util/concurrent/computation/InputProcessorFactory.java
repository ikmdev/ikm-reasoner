
package org.semanticweb.elk.util.concurrent.computation;

/**
 * A {@link ProcessorFactory} that produce {@link InputProcessor}s of a given
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <I>
 *            the type of the input processed by the {@link InputProcessor}s
 * @param <P>
 *            the type of the {@link InputProcessor} produced by this
 *            {@link InputProcessorFactory}
 */
public interface InputProcessorFactory<I, P extends InputProcessor<I>> extends
		ProcessorFactory<P> {

}
