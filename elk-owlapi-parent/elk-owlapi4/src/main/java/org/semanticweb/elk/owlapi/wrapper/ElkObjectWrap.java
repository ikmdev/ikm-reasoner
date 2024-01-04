
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.predefined.AbstractElkObject;
import org.semanticweb.owlapi.model.HasCardinality;
import org.semanticweb.owlapi.model.HasDomain;
import org.semanticweb.owlapi.model.HasFiller;
import org.semanticweb.owlapi.model.HasObject;
import org.semanticweb.owlapi.model.HasProperty;
import org.semanticweb.owlapi.model.HasRange;
import org.semanticweb.owlapi.model.HasSubject;
import org.semanticweb.owlapi.model.OWLObject;

/**
 * Implements the {@link ElkObject} interface by wrapping instances of objects
 * from OWLAPI.
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkObjectWrap<T> extends AbstractElkObject
		implements ElkObject {

	/**
	 * The converter for converting sub-objects.
	 */
	protected static OwlConverter converter = OwlConverter.getInstance();

	/**
	 * The {@link OWLObject} for which {@link ElkObjectWrap} is created. Must be
	 * initialized by constructors of subclasses.
	 */
	protected final T owlObject;

	public ElkObjectWrap(T owlObject) {
		this.owlObject = owlObject;
	}

	public T getOwlObject() {
		return owlObject;
	}

	static <P extends OWLObject> P getProperty(HasProperty<P> owlObject) {
		return owlObject.getProperty();
	}

	static <T extends OWLObject> T getFiller(HasFiller<T> owlObject) {
		return owlObject.getFiller();
	}

	static <D extends OWLObject> D getDomain(HasDomain<D> owlObject) {
		return owlObject.getDomain();
	}

	static <R extends OWLObject> R getRange(HasRange<R> owlObject) {
		return owlObject.getRange();
	}

	static int getCardinality(HasCardinality owlObject) {
		return owlObject.getCardinality();
	}

	static <O extends OWLObject> O getObject(HasObject<O> owlObject) {
		return owlObject.getObject();
	}

	static <T extends OWLObject> T getSubject(HasSubject<T> owlObject) {
		return owlObject.getSubject();
	}

}
