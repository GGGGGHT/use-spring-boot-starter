package com.ggggght.usespringbootstarter;

import com.gggggght.formatterspringbootstarter.service.Formatter;
import com.ggggght.usespringbootstarter.event.ApplicationPreparedEventListener;
import com.ggggght.usespringbootstarter.event.MyApplicationEvent;
import com.ggggght.usespringbootstarter.event.MyApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
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
	// @Autowired
	// StringRedisTemplate redisTemplate;


	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(UseSpringBootStarterApplication.class, args)));
		// new SpringApplicationBuilder(UseSpringBootStarterApplication.class).web(WebApplicationType.NONE).run(args).close();
		// SpringApplicationBuilder builder = new SpringApplicationBuilder(UseSpringBootStarterApplication.class);
		// builder.bannerMode(Banner.Mode.OFF);
		// builder.logStartupInfo(false);
		// builder.web(WebApplicationType.NONE);
		// builder.listeners(new ApplicationPreparedEventListener());
		// builder.initializers(context -> {
		// 	throw new UnknownError("抛出异常 查看分析器与报告器的结果");
		// });
		// builder.initializers(new HelloWorldApplicationContextInitializer());
		// builder.initializers(new HelloWorldApplicationContextInitializer());
		// builder.run(args);
		// GenericApplicationContext applicationContext = new GenericApplicationContext();
		// System.out.println("applicationContext.getDisplayName() = " + applicationContext.getDisplayName());
		// applicationContext.addApplicationListener(applicationEvent -> System.out.println("触发事件: " + applicationEvent.getClass().getSimpleName()));
		// applicationContext.registerBean(MyApplicationListener.class);
		//
		// System.out.println("应用上下文准备初始化...");
		// applicationContext.refresh();
		// System.out.println("应用上下文初始化完成!");
		//
		// applicationContext.publishEvent(new MyApplicationEvent("hello","localhost","content: hello world!"));
		// System.out.println("应用上下文准备停止");
		// applicationContext.stop();
		// System.out.println("应用上下文停止完成!");
		//
		// System.out.println("应用上下文准备启动...");
		// applicationContext.start();
		// System.out.println("应用上下文启动完成!");
		//
		// System.out.println("应用上下文准备关闭");
		// applicationContext.close();
		// System.out.println("应用上下文关闭完成!");
		// applicationContext.publishEvent(new MyApplicationEvent("hello again","localhost","content: hello world!"));
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

	@Bean
	ExitCodeGenerator exitCodeGenerator() {
		System.out.println("ExitCodeGenerator bean created");

		return () -> {
			System.out.println("执行退出码(33)生成");

			return 33;
		};
	}
}

