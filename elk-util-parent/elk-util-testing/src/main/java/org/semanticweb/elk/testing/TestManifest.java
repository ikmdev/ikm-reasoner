
package org.semanticweb.elk.testing;

/**
 * The basic interface for a test case which defines test name and test input.
 * 
 * @author Peter Skocovsky
 *
 * @param <I>
 *            The type of test input.
 */
public interface TestManifest<I extends TestInput> {

	public String getName();

	public I getInput();

}
