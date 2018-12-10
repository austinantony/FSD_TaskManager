package com.fsd.task.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fsd.task.bean.TaskVO;

public class TaskUtils {
	
	public static Date convertString2Date(String strDate) {
		
		if(isNotEmpty(strDate)) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    	    return new Date(formatter.parse(strDate).getTime());
			}
			catch (ParseException e) {
				System.out.println("Please check the date format");
			}
		}
		return null;
	}
	
	public static String convertDateString(Date date) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			return formatter.format(date);
		} else {
			return "";
		}
		
	}
	
	public static boolean isNotEmpty(String str) {
		if(str != null && !"".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static List<TaskVO> mapBOTask(List<com.fsd.task.entity.Task> taskList) {
		List<TaskVO> returnList = new ArrayList<>();
		taskList.forEach(task -> {
			TaskVO boTask = new TaskVO();
			boTask.setTaskId(String.valueOf(task.getTaskId()));
			boTask.setTaskName(task.getTaskName());
			boTask.setParentTask(task.getParentTask() != null ? task.getParentTask().getParentTask() : "");
			boTask.setPriority(String.valueOf(task.getPriority()));
			boTask.setStatus(task.getStatus());
			boTask.setStartDate(convertDateString(task.getStartDate()));
			boTask.setEndDate(convertDateString(task.getEndDate()));
			returnList.add(boTask);
		});
		return returnList;
	}

}
