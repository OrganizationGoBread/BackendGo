package com.school.sptech.grupo3.gobread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GoBreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoBreadApplication.class, args);
	}

}
