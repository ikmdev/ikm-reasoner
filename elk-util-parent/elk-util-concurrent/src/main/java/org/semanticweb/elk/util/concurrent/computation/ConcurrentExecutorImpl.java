
package org.semanticweb.elk.util.concurrent.computation;



import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A simple implementation of {@link ConcurrentExecutor} that uses the standard
 * java {@link Executor} to run several copies of jobs.
 * 
 * @author Yevgeny Kazakov
 *
 */
class ConcurrentExecutorImpl implements ConcurrentExecutor {

	private final Executor executor_;

	ConcurrentExecutorImpl(Executor executor) {
		this.executor_ = executor;
	}

	ConcurrentExecutorImpl(String prefix, long timeout, TimeUnit unit) {
		int cores = Runtime.getRuntime().availableProcessors();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(cores,
				Integer.MAX_VALUE, timeout, unit,
				new LinkedBlockingQueue<Runnable>());
		executor.setThreadFactory(new JobThreadFactory(prefix));
		this.executor_ = executor;
	}

	@Override
	public synchronized JobMonitor submit(Runnable job, int noInstances) {
		ThisJobMonitor result = new ThisJobMonitor(job, noInstances);
		for (int i = 0; i < noInstances; i++) {
			executor_.execute(result);
		}
		return result;
	}

	private static class ThisJobMonitor implements JobMonitor, Runnable {

		private final Runnable job_;
		private int runsNo_;
		private Throwable exception_ = null;

		ThisJobMonitor(Runnable job, int noInstances) {
			this.job_ = job;
			if (noInstances <= 0) {
				throw new IllegalArgumentException(
						"number of instances should be positive: "
								+ noInstances);
			}
			this.runsNo_ = noInstances;
		}

		synchronized void setException(Throwable exception) {
			this.exception_ = exception;
			notifyAll();
		}

		@Override
		public synchronized void waitDone() throws InterruptedException {
			for (;;) {
				if (runsNo_ == 0 || exception_ != null) {
					break;
				}
				// else
				wait();
			}
			if (exception_ != null) {
				throw new ComputationRuntimeException(exception_);
			}
		}

		@Override
		public void run() {
			try {
				job_.run();
			} catch (Throwable e) {
				setException(e);
			}
			synchronized (this) {
				if (--runsNo_ == 0) {
					notifyAll();
				}
			}
		}

	}

	private static class JobThreadFactory implements ThreadFactory {

		private final ThreadGroup group_;

		private int threadId_ = 0;

		JobThreadFactory(String prefix) {
			this.group_ = new ThreadGroup(prefix);
			group_.setDaemon(true);
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread result = new Thread(group_, r,
					group_.getName() + "-thread-" + ++threadId_);
			result.setDaemon(true);
			return result;
		}

	}

}
