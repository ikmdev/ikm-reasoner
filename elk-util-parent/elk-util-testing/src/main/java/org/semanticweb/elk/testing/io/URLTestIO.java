
package org.semanticweb.elk.testing.io;

import java.net.URL;

import org.semanticweb.elk.testing.UrlTestInput;

/**
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 */
public class URLTestIO implements UrlTestInput {

	private final String name_;
	private final URL url;

	public URLTestIO(final String name, final URL file) {
		this.name_ = name;
		this.url = file;
	}

	@Override
	public URL getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return name_ + " " + url.toString();
	}

	@Override
	public String getName() {
		return name_;
	}

}