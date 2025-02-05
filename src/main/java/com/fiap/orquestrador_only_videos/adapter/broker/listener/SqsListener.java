package com.fiap.orquestrador_only_videos.adapter.broker.listener;

import org.springframework.stereotype.Component;

@Component
public class SqsListener {

	@org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
	public void processMessage(String message) {
		System.out.println("Received message: " + message);
	}

}
