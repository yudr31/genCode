package com.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@MapperScan("com.spring.boot.mapper")
@ComponentScan(basePackages = {"com.spring.boot"})
public class GenCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenCodeApplication.class, args);
	}
}
