
package org.semanticweb.elk.testing;

/**
 * The base interface for a test instance which defines the input, the expected
 * output and the method of comparison.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 * @param <I>
 *            The type of test input.
 * @param <O>
 *            The type of test output.
 */
public interface TestManifestWithOutput<I extends TestInput, O>
		extends TestManifest<I> {

	/**
	 * @return The expected output.
	 */
	public O getExpectedOutput();

	/**
	 * Compares {@link #getExpectedOutput()} and the provided actual output.
	 * 
	 * @param actualOutput
	 * @throws TestResultComparisonException
	 */
	public void compare(O actualOutput) throws TestResultComparisonException;

}
