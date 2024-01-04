
package org.semanticweb.elk.reasoner.completeness;



/**
 * Represents the possibly incomplete result of a reasoning task, such as
 * computing a class taxonomy. The reasons for incompleteness (if any) can be
 * explained using the {@link IncompletenessMonitor} that can be returned using
 * {@link #getIncompletenessMonitor()}. The return value of the
 * {@link IncompleteResult} cannot be obtained directly to prevent ignoring the
 * incompleteness. Use {@link Incompleteness#getValue(IncompleteResult)}. to
 * obtain this value.
 * 
 * @author Yevgeny Kazakov
 *
 * @param <R>
 */
public class IncompleteResult<R> {

	private final R value_;

	private final IncompletenessMonitor monitor_;

	public IncompleteResult(R result, IncompletenessMonitor monitor) {
		this.value_ = result;
		this.monitor_ = monitor;
	}

	R getValue() {
		return value_;
	}

	public IncompletenessMonitor getIncompletenessMonitor() {
		return monitor_;
	}

	public <O, E extends Throwable> IncompleteResult<O> map(
			CheckedFunction<R, O, E> fn) throws E {
		return new IncompleteResult<O>(fn.apply(value_), monitor_);
	}

	@FunctionalInterface
	public interface CheckedFunction<I, O, E extends Throwable> {
		O apply(I input) throws E;
	}

}
