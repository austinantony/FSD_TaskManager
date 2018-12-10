package com.fsd.task.service;

import java.util.List;

import com.fsd.task.bean.Task;
import com.fsd.task.bean.TaskVO;

public interface TaskManagerService {

	Task addTask(Task boTask);
	Task updateTask(Task boTask);
	Task endTask(Task boTask);
	TaskVO getTask(int taskId);
	List<TaskVO> getAllTasks();
}
