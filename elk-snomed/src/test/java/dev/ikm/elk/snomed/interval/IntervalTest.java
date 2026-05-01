package dev.ikm.elk.snomed.interval;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2026 Integrated Knowledge Management
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntervalTest {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(IntervalTest.class);

	@Test
	public void open() {
		Interval i = Interval.fromString("(10, 20) 30");
		i = Interval.fromString(i.toString());
		assertTrue(i.isLowerOpen());
		assertEquals(new BigDecimal(10), i.getLowerBound());
		assertEquals(new BigDecimal(20), i.getUpperBound());
		assertTrue(i.isUpperOpen());
		assertEquals(30, i.getUnitOfMeasure().getId());
	}

	@Test
	public void closed() {
		Interval i = Interval.fromString("[10, 20] 30");
		i = Interval.fromString(i.toString());
		assertFalse(i.isLowerOpen());
		assertEquals(new BigDecimal(10), i.getLowerBound());
		assertEquals(new BigDecimal(20), i.getUpperBound());
		assertFalse(i.isUpperOpen());
		assertEquals(30, i.getUnitOfMeasure().getId());
	}

	@Test
	public void lowerOpen() {
		Interval i = Interval.fromString("(10, 20] 30");
		i = Interval.fromString(i.toString());
		assertTrue(i.isLowerOpen());
		assertEquals(new BigDecimal(10), i.getLowerBound());
		assertEquals(new BigDecimal(20), i.getUpperBound());
		assertFalse(i.isUpperOpen());
		assertEquals(30, i.getUnitOfMeasure().getId());
	}

	@Test
	public void upperOpen() {
		Interval i = Interval.fromString("[10, 20) 30");
		i = Interval.fromString(i.toString());
		assertFalse(i.isLowerOpen());
		assertEquals(new BigDecimal(10), i.getLowerBound());
		assertEquals(new BigDecimal(20), i.getUpperBound());
		assertTrue(i.isUpperOpen());
		assertEquals(30, i.getUnitOfMeasure().getId());
	}

	private boolean contains(String i1, String i2) {
		return Interval.fromString(i1 + "1").contains(Interval.fromString(i2 + "1"));
	}

	@Test
	public void contains() {
		assertTrue(contains("[0,10]", "[0,9]"));
		assertTrue(contains("[0,10]", "[0,10]"));
		assertFalse(contains("[0,10]", "[0,11]"));
		assertTrue(contains("[0,10]", "[1,9]"));
		assertTrue(contains("[0,10]", "[1,10]"));
		assertFalse(contains("[0,10]", "[1,11]"));
		assertFalse(contains("[0,10]", "[-1,9]"));
		assertFalse(contains("[0,10]", "[-1,10]"));
		assertFalse(contains("[0,10]", "[-1,11]"));
		//
		assertTrue(contains("[0,10]", "(0,9)"));
		assertTrue(contains("[0,10]", "(0,10)"));
		assertFalse(contains("[0,10]", "(0,11)"));
		assertTrue(contains("[0,10]", "(1,9)"));
		assertTrue(contains("[0,10]", "(1,10)"));
		assertFalse(contains("[0,10]", "(1,11)"));
		assertFalse(contains("[0,10]", "(-1,9)"));
		assertFalse(contains("[0,10]", "(-1,10)"));
		assertFalse(contains("[0,10]", "(-1,11)"));
		//
		assertFalse(contains("(0,10)", "[0,9]"));
		assertFalse(contains("(0,10)", "[0,10]"));
		assertFalse(contains("(0,10)", "[0,11]"));
		assertTrue(contains("(0,10)", "[1,9]"));
		assertFalse(contains("(0,10)", "[1,10]"));
		assertFalse(contains("(0,10)", "[1,11]"));
		assertFalse(contains("(0,10)", "[-1,9]"));
		assertFalse(contains("(0,10)", "[-1,10]"));
		assertFalse(contains("(0,10)", "[-1,11]"));
		//
		assertTrue(contains("[0,10]", "(0,9]"));
		assertTrue(contains("[0,10]", "(0,10]"));
		assertFalse(contains("[0,10]", "(0,11]"));
		assertTrue(contains("[0,10]", "(1,9]"));
		assertTrue(contains("[0,10]", "(1,10]"));
		assertFalse(contains("[0,10]", "(1,11]"));
		assertFalse(contains("[0,10]", "(-1,9]"));
		assertFalse(contains("[0,10]", "(-1,10]"));
		assertFalse(contains("[0,10]", "(-1,11]"));
		//
		assertTrue(contains("[0,10]", "[0,9)"));
		assertTrue(contains("[0,10]", "[0,10)"));
		assertFalse(contains("[0,10]", "[0,11)"));
		assertTrue(contains("[0,10]", "[1,9)"));
		assertTrue(contains("[0,10]", "[1,10)"));
		assertFalse(contains("[0,10]", "[1,11)"));
		assertFalse(contains("[0,10]", "[-1,9)"));
		assertFalse(contains("[0,10]", "[-1,10)"));
		assertFalse(contains("[0,10]", "[-1,11)"));
	}

	@Test
	public void negative() {
		Interval i = Interval.fromString("[-10, -20) -30");
		i = Interval.fromString(i.toString());
		assertFalse(i.isLowerOpen());
		assertEquals(new BigDecimal(-10), i.getLowerBound());
		assertEquals(new BigDecimal(-20), i.getUpperBound());
		assertTrue(i.isUpperOpen());
		assertEquals(-30, i.getUnitOfMeasure().getId());
	}

	@Test
	public void longMaxUnit() {
		Interval i = Interval.fromString("[-10, -20) " + Long.MAX_VALUE);
		i = Interval.fromString(i.toString());
		assertFalse(i.isLowerOpen());
		assertEquals(new BigDecimal(-10), i.getLowerBound());
		assertEquals(new BigDecimal(-20), i.getUpperBound());
		assertTrue(i.isUpperOpen());
		assertEquals(Long.MAX_VALUE, i.getUnitOfMeasure().getId());
	}

	@Test
	public void longMinUnit() {
		Interval i = Interval.fromString("[-10, -20) " + Long.MIN_VALUE);
		i = Interval.fromString(i.toString());
		assertFalse(i.isLowerOpen());
		assertEquals(new BigDecimal(-10), i.getLowerBound());
		assertEquals(new BigDecimal(-20), i.getUpperBound());
		assertTrue(i.isUpperOpen());
		assertEquals(Long.MIN_VALUE, i.getUnitOfMeasure().getId());
	}

}
