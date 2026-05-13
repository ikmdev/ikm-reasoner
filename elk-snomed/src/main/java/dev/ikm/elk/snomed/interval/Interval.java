package dev.ikm.elk.snomed.interval;

import java.math.BigDecimal;

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

	private BigDecimal lowerBound, upperBound;

	private boolean lowerOpen, upperOpen;

	private Concept unitOfMeasure;

	public BigDecimal getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(BigDecimal lowerBound) {
		this.lowerBound = lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = new BigDecimal(lowerBound);
	}

	public BigDecimal getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(BigDecimal upperBound) {
		this.upperBound = upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = new BigDecimal(upperBound);
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

	public Interval(BigDecimal lowerBound, boolean lowerOpen, BigDecimal upperBound, boolean upperOpen,
			Concept unitOfMeasure) {
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
		String integer = "(\\-?\\d+)";
		String decimal = "(\\-?\\d+(\\.\\d+)?)";
		String regex = "^(\\[|\\()" + decimal + "," + decimal + "(\\]|\\))" + integer + "$";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(str);
		if (!mat.matches())
			throw new IllegalArgumentException(str);
		Interval ret = new Interval();
		ret.lowerOpen = mat.group(1).equals("(");
		ret.lowerBound = new BigDecimal(mat.group(2));
		ret.upperBound = new BigDecimal(mat.group(4));
		ret.upperOpen = mat.group(6).equals(")");
		long uom = Long.parseLong(mat.group(7));
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

	private boolean lowerContains(Interval that) {
		if (this.isLowerOpen() && !that.isLowerOpen())
			return this.getLowerBound().compareTo(that.getLowerBound()) < 0;
		return this.getLowerBound().compareTo(that.getLowerBound()) <= 0;
	}

	private boolean upperContains(Interval that) {
		if (this.isUpperOpen() && !that.isUpperOpen())
			return this.getUpperBound().compareTo(that.getUpperBound()) > 0;
		return this.getUpperBound().compareTo(that.getUpperBound()) >= 0;
	}

	// an open interval does not include endpoints
	// (a, b) = { x | a < x < b }
	// a closed interval includes endpoints
	// [a, b] = { x | a <= x <= b }

	// x contains y
	//
	// x.LO & y.LO -> x.LB <= y.LB
	// x.LC & y.LC -> x.LB <= y.LB
	// x.LC & y.LO -> x.LB <= y.LB
	// x.LO & y.LC -> x.LB < y.LB
	//
	// x.UO & y.UO -> x.UB >= y.UB
	// x.UC & y.UC -> x.UB >= y.UB
	// x.UC & y.UO -> x.UB >= y.UB
	// x.UO & y.UC -> x.UB > y.UB

	public boolean contains(Interval that) {
		return lowerContains(that) && upperContains(that) && this.unitOfMeasure.equals(that.unitOfMeasure);
	}

}
