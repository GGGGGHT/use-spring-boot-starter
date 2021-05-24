package com.ggggght.usespringbootstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class UseSpringBootStarterApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(UseSpringBootStarterApplication.class, args);
	}

	@Autowired
	ApplicationContext context;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}
}
