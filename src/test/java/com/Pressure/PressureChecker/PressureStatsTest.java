package com.Pressure.PressureChecker;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.Pressure.model.Pressure;
import com.Pressure.service.*;

class PressureStatsTest {
	
	private Pressure pressure;
	private PressureServiceImpl pService;
	
	@BeforeEach
	void setUp() throws Exception {
		pressure = new Pressure();
		pService = new PressureServiceImpl();
		pressure = pService.readJSON("allData.Milan.json", "17/12/2021 13:30:00", "17/12/2021 19:43:58" );
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test sulle statistiche")
	/*Le righe // le ho escluse per evidenziare il fatto che il test in quel caso
	 * non funziona, se le utilizziamo, notiamo che poi il test termina con "failures"
	 */
	void test() {
		//assertEquals(1020, pressure.getValue_min());
		//assertEquals(1021, pressure.getValue_max());
		//assertEquals(1022, pressure.getValue_med());
		//assertEquals(23, pressure.getValue_diff());
		assertEquals(1025, pressure.getValue_min());
		assertEquals(1029, pressure.getValue_max());
		assertEquals(1027, pressure.getValue_med());
		assertEquals(4, pressure.getValue_diff());
	}

}
