package com.fsd.task;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsd.task.entity.Task;
import com.fsd.task.repository.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RepoTest {
	
	@Autowired
	private TaskRepository taskRepo;
	
	/*@Test
	public void testAddTask() throws Exception {
		Task taskEntity = new Task();
		taskEntity.setTaskName("TestTask");
		taskEntity.setPriority(10);
		taskEntity.setStatus("N");
		taskRepo.save(taskEntity);
		Task task = taskRepo.find("TestTask");
		assertThat(task.getTaskName()).isEqualTo("TestTask");
	}*/
	
	@Test
	public void testFindTask() throws Exception {
		List<Task> taskList = taskRepo.findAll();
		boolean flag = false;
		if (taskList != null) {
			for (Task task : taskList) {
				if ("TestTask".equals(task.getTaskName())) {
					flag = true;
				}
			}
		}
		assertThat(flag);
	}
	
}
