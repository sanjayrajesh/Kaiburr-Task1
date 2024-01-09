package com.kaiburr.task1.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.kaiburr.task1.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

	@Query("{name:'?0'}")
	Task findByName(String name);

	@Query("{name : ?0}")
	List<Task> findAllByName(String name);

	@Query("{assignee : ?0}")
	List<Task> findAllByAssignee(String assigneeName, Pageable pageable);
}
