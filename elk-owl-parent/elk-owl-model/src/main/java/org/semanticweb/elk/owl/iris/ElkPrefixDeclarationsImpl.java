
package org.semanticweb.elk.owl.iris;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frantisek Simancik
 * 
 */
public class ElkPrefixDeclarationsImpl implements ElkPrefixDeclarations {

	protected final Map<String, ElkPrefix> prefixLookup = new HashMap<String, ElkPrefix>();

	@Override
	public boolean addPrefix(ElkPrefix prefix) {
		if (prefixLookup.containsKey(prefix.getName()))
			return false;

		prefixLookup.put(prefix.getName(), prefix);
		return true;
	}

	@Override
	public ElkPrefix getPrefix(String prefixName) {
		return prefixLookup.get(prefixName);
	}

}
