package com.fsd.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fsd.task.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query(value = "SELECT * FROM TASK WHERE TASK = ?1", nativeQuery = true)
    public Task find(String taskName);
		
}
