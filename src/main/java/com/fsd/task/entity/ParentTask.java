package com.fsd.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PARENT_TASK")
public class ParentTask {
	
	public ParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	
	public ParentTask() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ParentTaskSeq")
	@SequenceGenerator(name="ParentTaskSeq", initialValue=1, allocationSize=1)
	@Column(name = "PARENT_ID", nullable=false, unique=true)
	private long parentId;
	
	@Column(name = "PARENT_TASK")
	private String parentTask;

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	
}
