package com.Pressure.PressureChecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.Pressure.controller", "com.Pressure.PressureChecker", "com.Pressure.service", "com.Pressure.model", "com.Pressure.exceptions","com.Pressure.utilities"})
public class PressureCheckerApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(PressureCheckerApplication.class, args);
	}

}
