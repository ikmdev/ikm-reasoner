
package org.semanticweb.elk.config;

/**
 * Thrown if an instance of ELK cannot be created due to misconfiguration
 * 
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 6644245038744863339L;

	public ConfigurationException() {
		super();
	}

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}

}
