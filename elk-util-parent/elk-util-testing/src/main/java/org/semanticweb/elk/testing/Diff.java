
package org.semanticweb.elk.testing;



import java.io.IOException;
import java.io.Writer;

public class Diff {

	public static <E, O extends DiffableOutput<E, O>> void writeDiff(
			O firstOutput, O secondOutput, Writer writer) throws IOException {
		try {
			firstOutput.reportMissingElementsOf(secondOutput,
					new WritingOutputListener<>(writer, "\n <"));
			secondOutput.reportMissingElementsOf(firstOutput,
					new WritingOutputListener<>(writer, "\n >"));
		} catch (RuntimeIOException e) {
			throw e.getIOException();
		}
		writer.flush();
	}

	public static class WritingOutputListener<E>
			implements DiffableOutput.Listener<E> {
		private final Writer writer_;
		private final String prefix_;

		public WritingOutputListener(Writer writer, String prefix) {
			this.writer_ = writer;
			this.prefix_ = prefix;
		}

		@Override
		public void missing(E element) {
			try {
				writer_.append(prefix_ + element);
			} catch (IOException e) {
				throw new RuntimeIOException(e);
			}
		}

	}

	public static class RuntimeIOException extends RuntimeException {
		private static final long serialVersionUID = 225260712709109779L;

		public RuntimeIOException(IOException e) {
			super(e);
		}

		public IOException getIOException() {
			return (IOException) getCause();
		}

	}

}
