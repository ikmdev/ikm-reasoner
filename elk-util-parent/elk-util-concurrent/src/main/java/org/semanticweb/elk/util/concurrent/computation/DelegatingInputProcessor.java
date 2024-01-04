
package org.semanticweb.elk.util.concurrent.computation;

/**
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @param <J>
 */
public class DelegatingInputProcessor<J> implements InputProcessor<J> {

	private final InputProcessor<J> processor_;

	public DelegatingInputProcessor(InputProcessor<J> p) {
		processor_ = p;
	}

	@Override
	public void submit(J job) {
		preSubmit(job);
		processor_.submit(job);
		postSubmit(job);
	}

	@Override
	public void process() throws InterruptedException {
		preProcess();
		processor_.process();
		postProcess();
	}

	@Override
	public void finish() {
		processor_.finish();
	}

	protected void preSubmit(J job) {
	}

	protected void postSubmit(J job) {
	}

	protected void preProcess() {
	}

	protected void postProcess() {
	}
}
