
package org.semanticweb.elk.reasoner.stages;

/**
 * Defines all {@link ReasonerStage}s used by the reasoner and static
 * dependencies between them. Dynamic dependencies are maintained manually.
 * 
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 */
public class ReasonerStageManager {

	final AbstractReasonerStage inputLoadingStage, propertyInitializationStage,
			propertyHierarchyCompositionComputationStage,
			objectPropertyTaxonomyComputationStage, contextInitializationStage,
			consistencyCheckingStage, classSaturationStage,
			incrementalCompletionStage, incrementalDeletionInitializationStage,
			incrementalDeletionStage, incrementalContextGapFillingStage,
			incrementalAdditionInitializationStage, incrementalAdditionStage,
			incrementalTaxonomyCleaningStage,
			classTaxonomyComputationStage,
			instanceTaxonomyComputationStage, inferenceTracingStage,
			classExpressionQueryStage, entailmentQueryStage;

	ReasonerStageManager(AbstractReasonerState reasoner) {

		// Java will not allow to define stages with cyclic dependencies

		/* Loading stages */

		this.inputLoadingStage = new InputLoadingStage(reasoner);

		/* Property stages */

		this.propertyInitializationStage = new PropertyInitializationStage(
				reasoner);

		this.propertyHierarchyCompositionComputationStage = new PropertyHierarchyCompositionComputationStage(
				reasoner, propertyInitializationStage);

		this.objectPropertyTaxonomyComputationStage = reasoner.objectPropertyTaxonomyState
				.createStage(reasoner,
						propertyHierarchyCompositionComputationStage);

		/* Non-Incremental saturation restoration stages */

		this.contextInitializationStage = new ContextAssignmentResetStage(
				reasoner);

		/* Incremental saturation restoration stages */

		this.incrementalCompletionStage = new IncrementalCompletionStage(
				reasoner, propertyHierarchyCompositionComputationStage);

		this.incrementalDeletionInitializationStage = new IncrementalDeletionInitializationStage(
				reasoner, incrementalCompletionStage);

		this.incrementalDeletionStage = new IncrementalDeletionStage(reasoner,
				incrementalDeletionInitializationStage);

		this.incrementalContextGapFillingStage = new IncrementalOverdeletionPruningStage(
				reasoner, incrementalDeletionStage);

		this.incrementalAdditionInitializationStage = new IncrementalAdditionInitializationStage(
				reasoner, incrementalContextGapFillingStage);

		this.incrementalAdditionStage = new IncrementalAdditionStage(reasoner,
				incrementalAdditionInitializationStage);

		this.incrementalTaxonomyCleaningStage = new IncrementalTaxonomyCleaningStage(
				reasoner, incrementalAdditionStage);

		/* Reasoning task stages */

		this.consistencyCheckingStage = new ConsistencyCheckingStage(reasoner,
				propertyHierarchyCompositionComputationStage);

		this.classSaturationStage = new ClassSaturationStage(reasoner,
				consistencyCheckingStage);

		this.classTaxonomyComputationStage = new ClassTaxonomyComputationStage(
				reasoner, consistencyCheckingStage);

		this.instanceTaxonomyComputationStage = new InstanceTaxonomyComputationStage(
				reasoner, classTaxonomyComputationStage);

		/* Tracing stages */

		this.inferenceTracingStage = new TracingStage(reasoner);

		/* Query stages */

		this.classExpressionQueryStage = new ClassExpressionQueryStage(
				reasoner);

		this.entailmentQueryStage = new EntailmentQueryStage(reasoner,
				propertyHierarchyCompositionComputationStage);

	}
}
