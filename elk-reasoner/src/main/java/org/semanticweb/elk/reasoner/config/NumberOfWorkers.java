
package org.semanticweb.elk.reasoner.config;

/**
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
public class NumberOfWorkers {

	private final int numOfWorkers;
	
	public NumberOfWorkers(String value) {
		if (value == null || value.length() == 0) {
			numOfWorkers = Runtime.getRuntime().availableProcessors();
		}
		else {
			numOfWorkers = Integer.valueOf(value);
		}
	}
	
	public int getNumberOfWorkers() {
		return numOfWorkers;
	}
	
	@Override
	public String toString() {
		return String.valueOf(numOfWorkers);
	}
}
