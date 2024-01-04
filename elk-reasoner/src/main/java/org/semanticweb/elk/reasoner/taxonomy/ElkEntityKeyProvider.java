
package org.semanticweb.elk.reasoner.taxonomy;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.KeyProvider;

/**
 * {@link KeyProvider} for {@link ElkEntity}.
 * 
 * @author Peter Skocovsky
 */
public class ElkEntityKeyProvider implements KeyProvider<ElkEntity> {

	/**
	 * The instance of this class.
	 */
	public static final ElkEntityKeyProvider INSTANCE = new ElkEntityKeyProvider();

	@Override
	public Object getKey(final ElkEntity arg) {
		return arg.getIri();
	}

}
