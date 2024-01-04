
package org.semanticweb.elk.reasoner.incremental;


/**
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 */
public enum IncrementalStages {

	ADDITIONS_INIT {

		@Override
		public String toString() {
			return "Incremental Additions Initialization";
		}
		
	},

	DELETIONS_INIT {

		@Override
		public String toString() {
			return "Incremental Deletions Initialization";
		}
		
	},
	
	CONTEXT_AFTER_DEL_INIT {

		@Override
		public String toString() {
			return "Post-Deletion Context Initialization";
		}
		
	},	
	
	CONTEXT_AFTER_CLEAN_INIT {

		@Override
		public String toString() {
			return "Post-Cleaning Context Initialization";
		}
		
	},	
	
	DELETION {

		@Override
		public String toString() {
			return "Incremental Deletion";
		}
		
	},
	
	ADDITION {

		@Override
		public String toString() {
			return "Incremental Addition";
		}
		
	},	
	
	CONTEXT_CLEANING {

		@Override
		public String toString() {
			return "Incremental Context Cleaning";
		}
	},
		
	COMPLETION {

		@Override
		public String toString() {
			return "Incremental Context Completion";
		}
	},
		
	TAXONOMY_CLEANING {

		@Override
		public String toString() {
			return "Incremental Taxonomy Cleaning";
		}
	};

	@Override
	public abstract String toString();	
}
