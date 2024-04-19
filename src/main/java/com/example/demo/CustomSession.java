package com.example.demo;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionIdGenerator;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@EnableSpringHttpSession
@Configuration
public class CustomSession {

	class MySessionIdGenerator implements SessionIdGenerator {
		@Override
		public String generate() {

			UUID uuidone = UUID.randomUUID();
			String uuidAsString = uuidone.toString() + UUID.randomUUID();
			return uuidAsString.replace("-", "");
		}
	}

    @Bean
    MapSessionRepository sessionRepository() {
		MapSessionRepository test = new MapSessionRepository(new ConcurrentHashMap<>());
		test.setSessionIdGenerator(new MySessionIdGenerator());
		return test;
	}
}
