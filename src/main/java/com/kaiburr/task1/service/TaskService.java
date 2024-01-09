package com.kaiburr.task1.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	private final static String candidateName ="SanjayRajesh";

	public String generateSanjayRajeshProperty() {
		SecureRandom random = new SecureRandom();
		StringBuilder specialProperty = new StringBuilder();

		for (int i = 0; i < 5; i++) {
			int randomIndex = random.nextInt(candidateName.length());
			specialProperty.append(candidateName.charAt(randomIndex));
		}
		return specialProperty.toString();
	}
}
