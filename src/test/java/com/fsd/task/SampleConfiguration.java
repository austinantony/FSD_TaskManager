package com.fsd.task;

import static org.mockito.Mockito.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.Repositories;

import com.fsd.task.repository.TaskRepository;

@Configuration
public class SampleConfiguration {

	@Autowired ApplicationContext context;

	@Bean
	public Repositories repositories() {
		return new Repositories(context);
	}

	@Bean
	public TaskRepository productRepository() {
		return mock(TaskRepository.class);
	}
}