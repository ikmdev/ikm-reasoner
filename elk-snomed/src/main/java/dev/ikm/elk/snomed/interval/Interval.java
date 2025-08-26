package dev.ikm.elk.snomed.interval;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2025 Integrated Knowledge Management
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.ikm.elk.snomed.model.Concept;

public class Interval {

	private int lowerBound, upperBound;

	private boolean lowerOpen, upperOpen;

	private Concept unitOfMeasure;

	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public boolean isLowerOpen() {
		return lowerOpen;
	}

	public void setLowerOpen(boolean lowerOpen) {
		this.lowerOpen = lowerOpen;
	}

	public boolean isUpperOpen() {
		return upperOpen;
	}

	public void setUpperOpen(boolean upperOpen) {
		this.upperOpen = upperOpen;
	}

	public Concept getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(Concept unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Interval(int lowerBound, boolean lowerOpen, int upperBound, boolean upperOpen, Concept unitOfMeasure) {
		super();
		this.lowerBound = lowerBound;
		this.lowerOpen = lowerOpen;
		this.upperBound = upperBound;
		this.upperOpen = upperOpen;
		this.unitOfMeasure = unitOfMeasure;
	}

	private Interval() {
	}

	public static Interval fromString(String str) {
		str = str.replace(" ", "");
		String regex = "^(\\[|\\()(\\-?\\d+),(\\-?\\d+)(\\]|\\))(\\-?\\d+)$";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(str);
		if (!mat.matches())
			throw new IllegalArgumentException(str);
		Interval ret = new Interval();
		ret.lowerOpen = mat.group(1).equals("(");
		ret.lowerBound = Integer.parseInt(mat.group(2));
		ret.upperBound = Integer.parseInt(mat.group(3));
		ret.upperOpen = mat.group(4).equals(")");
		long uom = Long.parseLong(mat.group(5));
		ret.unitOfMeasure = new Concept(uom);
		return ret;
	}

	@Override
	public String toString() {
		return toString(true);
	}

	public String toString(boolean includeUnitOfMeasure) {
		return (lowerOpen ? "(" : "[") + lowerBound + "," + upperBound + (upperOpen ? ")" : "]")
				+ (includeUnitOfMeasure ? unitOfMeasure.getId() : "");
	}

	private int getLowerContainsValue() {
		if (this.isLowerOpen())
			return this.getLowerBound() + 1;
		return this.getLowerBound();
	}

	private int getUpperContainsValue() {
		if (this.isUpperOpen())
			return this.getUpperBound() - 1;
		return this.getUpperBound();
	}

	public boolean contains(Interval that) {
		return this.getLowerContainsValue() <= that.getLowerContainsValue()
				&& this.getUpperContainsValue() >= that.getUpperContainsValue()
				&& this.unitOfMeasure.equals(that.unitOfMeasure);
	}

}
