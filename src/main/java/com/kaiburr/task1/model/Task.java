package com.kaiburr.task1.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tasklists")
public class Task {

	private String name;

	@Id
	private String id;

	private String assignee;
	private String project;

	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSX", shape =
	// JsonFormat.Shape.STRING)
	private Date startTime;

	private String sanjayRajeshProperty;

	public Task(String name, String id, String assignee, String project, Date startTime, String sanjayRajeshProperty) {
		super();
		this.name = name;
		this.id = id;
		this.assignee = assignee;
		this.project = project;
		this.startTime = startTime;
		this.sanjayRajeshProperty = sanjayRajeshProperty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getSanjayRajeshProperty() {
		return sanjayRajeshProperty;
	}
}