package com.ggggght.usespringbootstarter.event;

import org.springframework.context.ApplicationListener;

public class BlackListNotifier implements ApplicationListener<BlackListEvent> {


	@Override
	public void onApplicationEvent(BlackListEvent blackListEvent) {
		System.out.println("Address: " + blackListEvent.getAddress() + ", content: " + blackListEvent.getContent());
	}
}
