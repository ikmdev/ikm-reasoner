
package org.semanticweb.elk.util.logging;



/**
 * A class that caches the time every interval
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class CachedTimeThread extends Thread {

	/**
	 * the frequency of update for the time snapshot values in milliseconds
	 */
	private static final int UPDATE_FREQUENCY_ = 1;

	private static final CachedTimeThread INSTANCE_ = new CachedTimeThread("elk-timer-thread"); 
	
	/**
	 * the current time in milliseconds delayed by at most 10 milliseconds the
	 * value that would be returned by {@link System#currentTimeMillis()}
	 */
	private volatile long currentTimeMillis_ = System.currentTimeMillis();

	private CachedTimeThread(String name) {
		super(name);
		setDaemon(true);
	}

	static {
		INSTANCE_.start();
	}

	public static long getCurrentTimeMillis() {
		return INSTANCE_.currentTimeMillis_;
	}

	@Override
	public void run() {
		for (;;) {
			long newTime = System.currentTimeMillis();
			// make sure the time changes monotonically
			if (newTime > currentTimeMillis_)
				currentTimeMillis_ = newTime;

			try {
				Thread.sleep(UPDATE_FREQUENCY_);
			} catch (InterruptedException e) {
				// will continue; the thread should die automatically
			}
		}
	}
}
