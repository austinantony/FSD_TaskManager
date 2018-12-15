package com.fsd.task.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.task.bean.Task;
import com.fsd.task.bean.TaskVO;
import com.fsd.task.entity.ParentTask;
import com.fsd.task.repository.TaskRepository;
import com.fsd.task.util.TaskUtils;

@Service("taskManagerService")
public class TaskManagerServiceImpl implements TaskManagerService {

	@Autowired
	private TaskRepository taskRepo;
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Override
	public Task addTask(Task boTask) {
		
		com.fsd.task.entity.Task enTask = taskRepo.find(boTask.getTaskName());
		if (enTask != null) {
			return null;
		}
		com.fsd.task.entity.Task newTask = saveTask(boTask, new com.fsd.task.entity.Task());
		boTask.setTaskId(newTask.getTaskId());
		return boTask;
	}

	@Override
	public Task updateTask(Task boTask) {
		Optional<com.fsd.task.entity.Task> dbTask = taskRepo.findById(boTask.getTaskId());
		if (dbTask != null) {
			boTask.setTaskId(dbTask.get().getTaskId());
			saveTask(boTask, dbTask.get());
			return boTask;
		} else {
			return null;
		}
	}
	
	@Override
	public Task endTask(Task boTask) {
		Optional<com.fsd.task.entity.Task> dbOpt = taskRepo.findById(boTask.getTaskId());
		if (dbOpt != null) {
			com.fsd.task.entity.Task enTask = dbOpt.get();
			enTask.setStatus("E");
			taskRepo.save(enTask);
			return boTask;
		} else {
			return null;
		}
	}
	
	private com.fsd.task.entity.Task saveTask(Task boTask, com.fsd.task.entity.Task enTask) {
		ParentTask parTask = new ParentTask(boTask.getParentTask());
		if (enTask.getParentTask() != null) {
			parTask = enTask.getParentTask();
		}
		enTask.setTaskName(boTask.getTaskName());
		enTask.setStatus(boTask.getStatus());
		enTask.setPriority(boTask.getPriority());
		enTask.setStartDate(new Date(boTask.getStartDate().getTime()));
		enTask.setEndDate(new Date(boTask.getEndDate().getTime()));
		enTask.setParentTask(parTask);
		return taskRepo.save(enTask);
	}
	
	public List<TaskVO> getAllTasks(){
		List<com.fsd.task.entity.Task> entityList = taskRepo.findAll();
		if (entityList != null)
			return TaskUtils.mapBOTask(entityList);
		else
			return new ArrayList<>();
	}
	
	public TaskVO getTask(int taskId) {
		Optional<com.fsd.task.entity.Task> dbOpt = taskRepo.findById(taskId);
		if (dbOpt != null) {
			com.fsd.task.entity.Task task = dbOpt.get();
			TaskVO boTask = new TaskVO();
			boTask.setTaskId(String.valueOf(task.getTaskId()));
			boTask.setTaskName(task.getTaskName());
			boTask.setParentTask(task.getParentTask() != null ? task.getParentTask().getParentTask() : "");
			boTask.setPriority(String.valueOf(task.getPriority()));
			boTask.setStatus(task.getStatus());
			boTask.setStartDate(TaskUtils.convertDateString(task.getStartDate()));
			boTask.setEndDate(TaskUtils.convertDateString(task.getEndDate()));
			return boTask;
		} else {
			return null;
		}
	}
	
}
