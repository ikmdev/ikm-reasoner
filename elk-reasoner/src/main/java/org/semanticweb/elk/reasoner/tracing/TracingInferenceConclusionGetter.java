
package org.semanticweb.elk.reasoner.tracing;



class TracingInferenceConclusionGetter
		extends TracingInferenceConclusionVisitor<Conclusion> {

	TracingInferenceConclusionGetter(Conclusion.Factory factory) {
		super(factory, new TrivialConclusionVisitor());
	}

	TracingInferenceConclusionGetter() {
		this(ConclusionBaseFactory.getInstance());
	}

	static class TrivialConclusionVisitor
			extends DummyConclusionVisitor<Conclusion> {

		@Override
		protected Conclusion defaultVisit(Conclusion conclusion) {
			return conclusion;
		}
	}

}
