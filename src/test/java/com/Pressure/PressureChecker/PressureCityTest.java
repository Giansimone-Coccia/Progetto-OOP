package com.Pressure.PressureChecker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Pressure.model.City;

class PressureCityTest {
	
	private City city1;
	private City city2;
	
	@BeforeEach
	void setUp() throws Exception {
		city1 = new City("Amsterdam", 574395l, 18394.5, 361729.0);
		city2 = new City("Tokyo", 23104l, 19203.5, 13947.6);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCity() {
		assertEquals("Amsterdam", city1.getName());
		assertEquals(574395l, city1.getId());
		assertEquals(18394.5, city1.getLon());
		assertEquals(361729.0, city1.getLat());
	}
	
	@Test
	void testCity2() {
		assertEquals("Berlino", city1.getName());
		//assertEquals("Tokyo", city2.getName());
		//assertEquals(23104l, city2.getId());
		assertEquals(564655, city2.getId());
		assertEquals(19203.5, city2.getLon());
		assertEquals(13947.6, city2.getLat());
	}

}
