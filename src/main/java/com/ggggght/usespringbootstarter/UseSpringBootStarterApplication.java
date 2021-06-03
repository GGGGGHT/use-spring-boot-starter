package com.ggggght.usespringbootstarter;

import com.gggggght.formatterspringbootstarter.service.Formatter;
import com.ggggght.usespringbootstarter.event.MyApplicationEvent;
import com.ggggght.usespringbootstarter.event.MyApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
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
		SpringApplicationBuilder builder = new SpringApplicationBuilder(UseSpringBootStarterApplication.class);
		builder.bannerMode(Banner.Mode.OFF);
		builder.logStartupInfo(false);
		builder.web(WebApplicationType.NONE);
		builder.initializers(new HelloWorldApplicationContextInitializer());
		builder.initializers(new HelloWorldApplicationContextInitializer());
		builder.run(args);
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		System.out.println("applicationContext.getDisplayName() = " + applicationContext.getDisplayName());
		applicationContext.addApplicationListener(applicationEvent -> System.out.println("触发事件: " + applicationEvent.getClass().getSimpleName()));
		applicationContext.registerBean(MyApplicationListener.class);

		System.out.println("应用上下文准备初始化...");
		applicationContext.refresh();
		System.out.println("应用上下文初始化完成!");

		applicationContext.publishEvent(new MyApplicationEvent("hello","localhost","content: hello world!"));
		System.out.println("应用上下文准备停止");
		applicationContext.stop();
		System.out.println("应用上下文停止完成!");

		System.out.println("应用上下文准备启动...");
		applicationContext.start();
		System.out.println("应用上下文启动完成!");

		System.out.println("应用上下文准备关闭");
		applicationContext.close();
		System.out.println("应用上下文关闭完成!");
		applicationContext.publishEvent(new MyApplicationEvent("hello again","localhost","content: hello world!"));
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

