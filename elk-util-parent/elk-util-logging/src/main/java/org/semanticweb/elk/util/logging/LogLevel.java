
package org.semanticweb.elk.util.logging;

/**
 * Defines the levels as an enum for an efficient implementation of {@link LoggerWrap#log(org.slf4j.Logger, LogLevel, String, Object[])} 
 * 
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
public enum LogLevel {
	TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR
}
