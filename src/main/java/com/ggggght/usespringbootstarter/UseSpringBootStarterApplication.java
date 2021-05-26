package com.ggggght.usespringbootstarter;

import com.gggggght.formatterspringbootstarter.service.Formatter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class UseSpringBootStarterApplication implements ApplicationRunner, CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UseSpringBootStarterApplication.class, args);
	}

	@Autowired
	ApplicationContext context;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

	@Override
	public void run(String... args) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "zhangsan");
		// Formatter formatter = context.getBean(Formatter.class);
		Map<String, Formatter> beans = context.getBeansOfType(Formatter.class);
		if (CollectionUtils.isEmpty(beans)) {
			return;
		}

		System.out.println();
		beans.forEach((name, formatter) -> {
			System.out.printf("[Bean name: %s] %s.format(data): %s\n", name,formatter.getClass().getSimpleName(), formatter.format(map));
		});
		// System.out.printf("%s.format(data): %s\n", formatter.getClass().getSimpleName(), formatter.format(map));
	}
}

