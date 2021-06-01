package com.ggggght.usespringbootstarter.event;

import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
	@Override
	public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
		System.out.println(myApplicationEvent.getClass().getSimpleName() + " -> " + myApplicationEvent.getAddress() + " -> " + myApplicationEvent.getContent());
	}
}
