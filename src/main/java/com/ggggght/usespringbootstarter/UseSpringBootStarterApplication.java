package com.ggggght.usespringbootstarter;

import com.gggggght.formatterspringbootstarter.service.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Thread.*;

@SpringBootApplication
public class UseSpringBootStarterApplication implements ApplicationRunner, CommandLineRunner {
	@Autowired
	StringRedisTemplate redisTemplate;


	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException, IOException {
		// LocalDateTime start = LocalDateTime.now();
		// CompletableFuture<Double> future = CompletableFuture.supplyAsync(UseSpringBootStarterApplication::randomInt).
		// 		thenCombine(CompletableFuture.supplyAsync(UseSpringBootStarterApplication::randomDouble), (i, d) -> i * d);

		// System.out.println(future.get(1, TimeUnit.MINUTES));
		// System.out.println(Duration.between(start,LocalDateTime.now()).toSeconds());


		// CompletableFuture.supplyAsync(UseSpringBootStarterApplication::randomInt).thenAccept(t -> System.out.printf("int is %d\n", t));

		// List<Long> list = List.of(1L, 2L, 3L, 4L);
		// StringJoiner joiner = new StringJoiner(",");
		// list.stream().map(Objects::toString).forEach(joiner::add);
		// System.out.println("joiner.toString() = " + joiner.toString());
		// List<Integer> list2 = List.of(5, 6, 7, 9);
		// joiner = new StringJoiner(",");
		// list2.stream().map(Object::toString).forEach(joiner::add);
		// System.out.println("joiner = " + joiner);
		// SpringApplicationBuilder builder = new SpringApplicationBuilder(UseSpringBootStarterApplication.class);
		// builder.run(args);
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

		List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8);
		// IntStream.rangeClosed(0,list.size()/2).forEach(i -> {
		// });
		// Map<Integer, Integer> map = Map.of(1, 1, 2, 2, 3, 3, 4, 4, 7, 7, 10, 10);
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			map.put(i, i);
		}
		list.forEach(key -> map.remove(key));
		// Iterator<Integer> iterator = list.iterator();
		// while (iterator.hasNext()) {
		// 	map.remove(iterator.next());
		// }

		map.entrySet().forEach(k -> System.out.println("key: " + k.getKey() + ", v: " + k.getValue()));
	}

	@Autowired
	ApplicationContext context;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// context.getBeansOfType(ObjectMapper.class).forEach((k, v) -> System.out.println("k: " + k + ", v: " + v));
		// Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

	}

	@Override
	public void run(String... args) throws Exception {
		Map<String, String> map = Map.of("k1", "v1", "k2", "v2");
		redisTemplate.opsForHash().putAll("hashkey",map);

		sleep(2000L);
		Map<Object, Object> hashkey = redisTemplate.opsForHash().entries("hashkey");
		for (Map.Entry<Object, Object> entry : hashkey.entrySet()) {
			System.out.println("key: " + entry.getKey().toString() + ", value: " + entry.getValue().toString());
		}

		sleep(300L);
		map = Map.of("k1","","k2","");
		redisTemplate.opsForHash().putAll("hashkey",map);
		sleep(2000L);
		System.out.printf("key \t value \n");
		hashkey = redisTemplate.opsForHash().entries("hashkey");
		for (Map.Entry<Object, Object> entry : hashkey.entrySet()) {
			System.out.println("key: " + entry.getKey().toString() + ", value: " + entry.getValue().toString());
		}
		// Map<String, Object> map = new HashMap<>();
		// map.put("name", "zhangsan");
		// // Formatter formatter = context.getBean(Formatter.class);
		// Map<String, Formatter> beans = context.getBeansOfType(Formatter.class);
		// if (CollectionUtils.isEmpty(beans)) {
		// 	return;
		// }
		//
		// System.out.println();
		// beans.forEach((name, formatter) -> {
		// 	System.out.printf("[Bean name: %s] %s.format(data): %s\n", name,formatter.getClass().getSimpleName(), formatter.format(map));
		// });
		// System.out.printf("%s.format(data): %s\n", formatter.getClass().getSimpleName(), formatter.format(map));
	}

	public Future<String> getHelloWorldAsync() {
		return CompletableFuture.supplyAsync(UseSpringBootStarterApplication::hello);
	}

	private static String hello() {
		return "hello world!";
	}

	private static int randomInt() {
		try {
			sleep(1_000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Random().nextInt(10);
	}

	private static double randomDouble() {
		try {
			sleep(1_000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Random().nextDouble();
	}

}

