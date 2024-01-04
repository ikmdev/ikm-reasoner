
package org.semanticweb.elk.owl.printers;

import java.io.IOException;

import org.semanticweb.elk.owl.interfaces.ElkObject;

/**
 * Printing ELK Objects in OWL 2 functional style syntax.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class OwlFunctionalStylePrinter {

	/**
	 * Converting an ELK Object to string.
	 * 
	 * @param elkObject
	 *            the input ELK object
	 * @return the string representation of the ELK object
	 */
	public static String toString(ElkObject elkObject) {
		return toString(elkObject, false);
	}
	
	public static String toString(ElkObject elkObject, boolean expandAbbreviatedIris) {
		StringBuilder writer = new StringBuilder();
		OwlFunctionalStylePrinterVisitor printer = new OwlFunctionalStylePrinterVisitor(
				writer, expandAbbreviatedIris);
		elkObject.accept(printer);
		return writer.toString();
	}	

	/**
	 * Printing an ELK Object through an appender.
	 * 
	 * @param appender
	 *            the appender used for printing
	 * @param elkObject
	 *            the ELK Object to print
	 * @throws IOException
	 *             if an I/O Error occurs
	 */
	public static void append(Appendable appender, ElkObject elkObject)
			throws IOException {
		append(appender, elkObject, false);
	}
	
	public static void append(Appendable appender, ElkObject elkObject, boolean expandAbbreviatedIris)
			throws IOException {
		OwlFunctionalStylePrinterVisitor printer = new OwlFunctionalStylePrinterVisitor(
				appender, expandAbbreviatedIris);
		try {
			elkObject.accept(printer);
		} catch (PrintingException e) {
			throw new IOException(e.getMessage(), e.getCause());
		}

	}	

}
