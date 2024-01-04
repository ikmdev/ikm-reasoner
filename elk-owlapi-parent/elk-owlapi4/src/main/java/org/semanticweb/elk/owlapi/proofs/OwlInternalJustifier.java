
package org.semanticweb.elk.owlapi.proofs;



import java.util.Set;

import org.liveontologies.puli.Inference;
import org.liveontologies.puli.InferenceJustifier;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owlapi.ElkConverter;
import org.semanticweb.elk.proofs.InternalJustifier;
import org.semanticweb.owlapi.model.OWLAxiom;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class OwlInternalJustifier
		implements InferenceJustifier<Inference<?>, Set<OWLAxiom>>,
		Function<ElkAxiom, OWLAxiom> {

	private final InferenceJustifier<Inference<?>, Set<? extends ElkAxiom>> internalJustifier_ = new InternalJustifier();

	private final ElkConverter elkConverter_ = ElkConverter.getInstance();

	@Override
	public Set<OWLAxiom> getJustification(Inference<?> inference) {
		return ImmutableSet.copyOf(Iterables.transform(
				internalJustifier_.getJustification(inference), this));
	}

	@Override
	public OWLAxiom apply(final ElkAxiom input) {
		return elkConverter_.convert(input);
	}

}
