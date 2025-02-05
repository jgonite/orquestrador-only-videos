package com.fiap.orquestrador_only_videos.adapter.broker.config;

import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
@EnableSqs
public class SqsConfig {

	public static final Region region = Region.US_EAST_1;

	@Bean
	public SqsAsyncClient sqsAsyncClient() {
		return SqsAsyncClient.builder()
				.region(region)
				.credentialsProvider(DefaultCredentialsProvider.create())
				.build();
	}

}
