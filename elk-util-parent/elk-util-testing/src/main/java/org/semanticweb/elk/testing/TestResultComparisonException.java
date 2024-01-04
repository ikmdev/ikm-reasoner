
package org.semanticweb.elk.testing;

/**
 * Thrown if the actual test value does not match the expected test value as specified in the test manifest.
 * 
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 *
 */
public class TestResultComparisonException extends AssertionError {

	private Object expOutput;
	private Object actualOutput;
	
	public TestResultComparisonException() {}
	
	public TestResultComparisonException(String msg, Object expected, Object actual) {
		super(msg);
		
		expOutput = expected;
		actualOutput = actual;
	}
	
	public Object getExpectedOutput() {
		return expOutput;
	}

	public Object getActualOutput() {
		return actualOutput;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
