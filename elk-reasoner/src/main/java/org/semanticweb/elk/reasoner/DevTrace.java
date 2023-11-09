package org.semanticweb.elk.reasoner;

import org.slf4j.Logger;

public class DevTrace {

	public static boolean trace = false;

	public static void log(Logger logger, String format, Object... arguments) {
		if (trace)
			logger.info(format, arguments);
	}

}
