package com.yhproject.operation_together;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@ServletComponentScan
@SpringBootApplication
public class OperationTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationTogetherApplication.class, args);
	}

}
