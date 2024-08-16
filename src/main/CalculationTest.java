package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculationTest {

	@Test
	void testFindMax() {
		assertEquals(4, Calculation.findMax(new int[]{1,2,3,4}));
		assertEquals(50, Calculation.findMax(new int[]{-12,-1,-3,-4,-2,50}));
		assertEquals(-1, Calculation.findMax(new int[]{-12,-1,-3,-4,-2}));
	}
	
	@Test
	void testFindMax2() {
		assertEquals(7, Calculation.findMax(new int[]{1,2,3,4,5,8,9}));
	}
	
	@Test
	void testFindMax3() {
		assertEquals(-12, Calculation.findMax(new int[]{-12,-1,-3,-4,-2,50}));
	}
	
	@Test
	public void testFindMaxElement() {
	    assertEquals(1, Calculation.findMax(new int[]{7}));
	}
}
