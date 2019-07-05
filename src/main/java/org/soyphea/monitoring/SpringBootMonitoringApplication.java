package org.soyphea.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class SpringBootMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMonitoringApplication.class, args);
	}

	@GetMapping("/greeting")
	public String greeting(){
		return "Hi from Spring Boot + Prometheus + Grafana";
	}
}
