
package org.semanticweb.elk.owl.implementation;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObject;

/**
 * Implementation for {@link ElkObject}s that maintain a list of other
 * ElkObjects.
 * 
 * @author Markus Kroetzsch
 * @param <O>
 *            the type of {@link ElkObject}s in the list
 * 
 */
public abstract class ElkObjectListObject<O extends ElkObject> extends
		ElkObjectImpl {

	private final List<? extends O> elkObjects_;

	ElkObjectListObject(List<? extends O> elkObjects) {
		this.elkObjects_ = elkObjects;
	}

	public List<? extends O> getObjects() {
		return elkObjects_;
	}

	@SafeVarargs
	public static <O> List<? extends O> varArgsToList(O firstObject,
			O secondObject, O... otherObjects) {
		List<O> objects = new ArrayList<O>(2 + otherObjects.length);
		objects.add(firstObject);
		objects.add(secondObject);
		for (int i = 0; i < otherObjects.length; ++i) {
			objects.add(otherObjects[i]);
		}
		return objects;
	}

	@SafeVarargs
	public static <O> List<? extends O> varArgsToList(O firstObject,
			O... otherObjects) {
		List<O> objects = new ArrayList<O>(1 + otherObjects.length);
		objects.add(firstObject);
		for (int i = 0; i < otherObjects.length; ++i) {
			objects.add(otherObjects[i]);
		}
		return objects;
	}

}
