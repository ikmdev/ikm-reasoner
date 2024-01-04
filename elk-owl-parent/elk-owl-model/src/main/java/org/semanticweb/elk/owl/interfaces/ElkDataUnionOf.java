
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkDataUnionOfVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Union_of_Data_Ranges" >Union of
 * Data Ranges<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataUnionOf extends ElkDataRange {

	/**
	 * Get the list of data ranges that this expression refers to. The order of
	 * data ranges does not affect the semantics but it is relevant to the
	 * syntax of OWL.
	 * 
	 * @return list of data ranges
	 */
	public List<? extends ElkDataRange> getDataRanges();

	/**
	 * Accept an {@link ElkDataUnionOfVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataUnionOfVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataUnionOf}.
		 * 
		 * @param first
		 *            the first {@link ElkDataRange} for which the object should
		 *            be created
		 * @param second
		 *            the second {@link ElkDataRange} for which the object
		 *            should be created
		 * @param other
		 *            other {@link ElkDataRange}s for which the object should be
		 *            created
		 * @return an {@link ElkDataUnionOf} corresponding to the input
		 */
		public ElkDataUnionOf getDataUnionOf(ElkDataRange first,
				ElkDataRange second, ElkDataRange... other);

		/**
		 * Create an {@link ElkDataUnionOf}.
		 * 
		 * @param ranges
		 *            the {@link ElkDataRange}s for which the object should be
		 *            created
		 * @return an {@link ElkDataUnionOf} corresponding to the input
		 */
		public ElkDataUnionOf getDataUnionOf(
				List<? extends ElkDataRange> ranges);

	}

}
