
package org.semanticweb.elk.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 */
public class FileUtils {

	public static FilenameFilter getExtBasedFilenameFilter(
			final String extension) {
		return new FilenameFilter() {
			@Override
			public boolean accept(File file, String name) {
				return name.endsWith("." + extension);
			}
		};
	}

	public static String dropExtension(String filename) {
		int index = -1;

		if ((index = filename.lastIndexOf('.')) < 0) {
			return filename;
		} else {
			return filename.substring(0, index);
		}
	}
	
	public static String dropExtension(String filename, String extension) {
		int index = -1;

		if ((index = filename.lastIndexOf("." + extension)) < 0) {
			return filename;
		} else {
			return filename.substring(0, index);
		}
	}	

	/**
	 * @param filename
	 * @return The file extension of the provided file name, or {@code null}
	 *         when it does not have an extension. A file extension is a
	 *         substring of the name starting immediately after the last
	 *         {@code '.'} and ending at the end of the name.
	 */
	public static String getExtension(final String filename) {
		final int index = filename.lastIndexOf('.');
		if (index < 0) {
			return null;
		} else {
			return filename.substring(index + 1, filename.length());
		}
	}

	public static String getFileName(String path) {
		return new File(path).getName();
	}

	public static void deleteRecursively(File file) throws IOException {
		deleteRecursively(file, false);
	}

	public static void deleteRecursively(File file, boolean deleteOnExit)
			throws IOException {
		File[] directoryFiles;
		if ((directoryFiles = file.listFiles()) != null) {
			for (File c : directoryFiles)
				deleteRecursively(c);
		}

		if (deleteOnExit) {
			file.deleteOnExit();
		}
		else {
			if (!file.delete()) {
				throw new IOException("Failed to delete file: " + file);
			}	
		}
	}
}
