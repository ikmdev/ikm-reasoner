
package org.semanticweb.elk.reasoner.indexing.conversion;



import org.semanticweb.elk.reasoner.completeness.Feature;

public class ElkIndexingUnsupportedFeature
		extends ElkIndexingUnsupportedException {

	private static final long serialVersionUID = -250091255387538392L;

	private final Feature feature_;

	public ElkIndexingUnsupportedFeature(Feature feature) {
		super("ELK does not support " + feature);
		this.feature_ = feature;
	}

	public Feature getFeature() {
		return feature_;
	}

}
