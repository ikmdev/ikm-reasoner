/**
 * 
 */
package org.semanticweb.elk.util.collections;
/*
 * #%L
 * ELK Utilities Collections
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2014 Department of Computer Science, University of Oxford
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Collection;
import java.util.Map;

/**
 * TODO
 * 
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 * 
 * @param <Key>
 *            the keys of the multimap
 * @param <Value>
 *            the values of the multimap 
 */
@Deprecated
public interface MultimapQueue<Key, Value> extends Multimap<Key, Value> {
	
	/**
	 * 
	 * @return the removed entry or {@code null} if the queue is empty
	 */
	public Map.Entry<Key, Collection<Value>> takeEntry();

}
