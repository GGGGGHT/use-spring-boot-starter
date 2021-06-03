package com.ggggght.usespringbootstarter.event;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
		ConfigurableApplicationContext context = applicationPreparedEvent.getApplicationContext();
		context.setId("context-ggggght");
		System.out.println("当前上下文调整为: " + context.getId());
	}
}
