package com.Pressure.PressureChecker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.Pressure.model.Pressure;

class PressureTest {
	
	private Pressure p;
	
	@BeforeEach
	void setUp() throws Exception {
		p = new Pressure(1027l, 1025l, 1028.5, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test sulla pressione")
	void test() {
		//assertEquals(1036l, p.getValue_max());
		//assertEquals(1021l, p.getValue_min());
		//assertEquals(1028.5, p.getValue_med());
		//assertEquals(4, p.getValue_diff());
		assertEquals(1027l, p.getValue_max());
		assertEquals(1025l, p.getValue_min());
		assertEquals(1028.5, p.getValue_med());
		assertEquals(2, p.getValue_diff());
	}

}
