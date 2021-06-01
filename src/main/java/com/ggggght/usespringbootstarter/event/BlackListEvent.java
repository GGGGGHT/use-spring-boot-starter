package com.ggggght.usespringbootstarter.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextStartedEvent;

public class BlackListEvent extends ContextStartedEvent {
	private final String address;
	private final String content;

	public BlackListEvent(ApplicationContext source, String address, String content) {
		super(source);
		this.address = address;
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public String getContent() {
		return content;
	}
}
