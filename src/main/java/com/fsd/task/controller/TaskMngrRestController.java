package com.fsd.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fsd.task.bean.Task;
import com.fsd.task.bean.TaskVO;
import com.fsd.task.service.TaskManagerService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/taskmanager")
@EnableWebMvc
public class TaskMngrRestController {

	@Autowired
	private TaskManagerService taskManagerService;
	
	@RequestMapping(value = "/rest/task", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Task createTask(@RequestBody Task task) {
		
		if (task == null || "".equals(task.getTaskName())) {
			return null;
		} else {
			return taskManagerService.addTask(task);
		}
	}
	
	@RequestMapping(value = "/rest/task", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Task updateTask(@RequestBody Task task) {
		
		if (task != null) {
			return taskManagerService.updateTask(task);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/rest/endtask", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Task endTask(@RequestBody Task task) {
		
		if (task != null) {
			return taskManagerService.endTask(task);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/rest/task/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskVO getTask(@PathVariable("id") int taskId) {
		return taskManagerService.getTask(taskId);
	}
	
	@RequestMapping(value = "/rest/task", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskVO> getTasks() {
		return taskManagerService.getAllTasks();
	}

}
