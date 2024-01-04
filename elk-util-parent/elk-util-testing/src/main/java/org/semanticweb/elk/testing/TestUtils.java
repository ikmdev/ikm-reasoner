
package org.semanticweb.elk.testing;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.semanticweb.elk.io.FileUtils;

/**
 * A collection of test utilities, e.g., for creating and destroying the test
 * environment
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class TestUtils {

	public static final String TEST_ROOT = ".test-home";

	public static void createTestEnvironment(File baseDir) {
		File root = new File(baseDir.getAbsolutePath() + "/" + TEST_ROOT);

		if (root.exists()) {
			try {
				FileUtils.deleteRecursively(root);
			} catch (IOException e) {
				throw new RuntimeException(
						"Initialization of test environment failed, unable to delete the root test folder");
			}
		}

		if (!root.mkdirs()) {
			throw new RuntimeException(
					"Initialization of test environment failed, unable to create the root test folder");
		}
	}

	public static void cleanUp(File baseDir) {
		try {
			File root = new File(baseDir.getAbsolutePath() + "/" + TEST_ROOT);

			if (root.exists()) {
				FileUtils.deleteRecursively(root);
			}
		} catch (IOException e) {
			throw new RuntimeException(
					"Clean-up of test environment failed, unable to delete the root test folder");
		}
	}

	public static void cleanUpOnExit(File baseDir) {
		File root = new File(baseDir.getAbsolutePath() + "/" + TEST_ROOT);

		if (root.exists()) {
			try {
				FileUtils.deleteRecursively(root, true);
			} catch (IOException e) {
				// TODO shouldn't throw any exceptions if
				// deleting on exit
			}
		}
	}

	/**
	 * @param input
	 * @param inputDataLocation
	 * @param sortedIgnoredPaths
	 * @return Whether the suffix of the path of the input URL starting at the
	 *         last occurrence of the input data location is in sorted ignored
	 *         paths.
	 */
	public static boolean ignore(final UrlTestInput input,
			final String inputDataLocation, final String[] sortedIgnoredPaths) {
		final String path = input.getUrl().getPath();
		final int index = path.lastIndexOf(inputDataLocation);
		if (index < 0) {
			throw new IllegalArgumentException("\"" + inputDataLocation
					+ "\" does not occur in \"" + path + "\"");
		}
		final String relativePath = path.substring(index);
		return Arrays.binarySearch(sortedIgnoredPaths, relativePath) >= 0;
	}

}
