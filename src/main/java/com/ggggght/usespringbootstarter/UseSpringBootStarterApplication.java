package com.ggggght.usespringbootstarter;

import com.gggggght.formatterspringbootstarter.service.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@SpringBootApplication
public class UseSpringBootStarterApplication implements ApplicationRunner, CommandLineRunner {

	public static void main(String[] args) {
		// SpringApplicationBuilder builder = new SpringApplicationBuilder(UseSpringBootStarterApplication.class);
		//
		// builder.web(WebApplicationType.NONE).run(args);
		// SpringApplication.run(UseSpringBootStarterApplication.class, args);
		// StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
		// for (StackTraceElement stackTraceElement : stackTrace) {
		// 	if ("main".equals(stackTraceElement.getMethodName())) {
		// 		try {
		// 			System.out.println(Class.forName(stackTraceElement.getClassName()));
		// 		} catch (ClassNotFoundException e) {
		// 			for (StackTraceElement traceElement : e.getException().getStackTrace()) {
		//
		// 				System.out.println("traceElement.getMethodName() = " + traceElement.getMethodName());
		// 				System.out.println("traceElement.getClassName() = " + traceElement.getClassName());
		// 				System.out.println();
		// 			}
		// 			e.printStackTrace();
		// 		}
		// 	}
		// }

		// try {
		// 	int i = 1 / 0;
		// } catch (Exception e) {
		// 	for (StackTraceElement traceElement : e.getStackTrace()) {
		// 		System.out.println("traceElement.getMethodName() = " + traceElement.getMethodName());
		// 		System.out.println("traceElement.getClassName() = " + traceElement.getClassName());
		// 		System.out.println();
		// 	}
		//
		// }
	}

	@Autowired
	ApplicationContext context;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// context.getBeansOfType(ObjectMapper.class).forEach((k, v) -> System.out.println("k: " + k + ", v: " + v));
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

	public Future<String> getHelloWorldAsync() {
		return CompletableFuture.supplyAsync(UseSpringBootStarterApplication::hello);
	}

	private static String hello() {
		return "hello world!";
	}



}

