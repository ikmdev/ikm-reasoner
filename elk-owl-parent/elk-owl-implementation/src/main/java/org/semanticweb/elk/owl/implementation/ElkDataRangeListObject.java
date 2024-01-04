
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataRange;

/**
 * Implementation for ElkObjects that maintain a list of object ranges.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkDataRangeListObject extends
		ElkObjectListObject<ElkDataRange> {

	ElkDataRangeListObject(List<? extends ElkDataRange> dataRanges) {
		super(dataRanges);
	}

	public List<? extends ElkDataRange> getDataRanges() {
		return getObjects();
	}

}
