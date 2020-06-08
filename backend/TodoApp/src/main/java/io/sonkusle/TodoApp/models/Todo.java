package io.sonkusle.TodoApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;



@Entity
@Table(indexes = {@Index(columnList = "userId",unique = false)})
public class Todo {
	@Id
	@GeneratedValue
	private Long id;
	private int userId = 41;
	private String title; 	
	private String desc;
	private boolean isComplete = false;
	
	public Todo() {
		
	}
	

	public Todo(String title, String desc) {
		super();
		this.title = title;
		this.desc = desc;
	}

	public boolean isComplete() {
		return isComplete;
	}


	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getId() {
		return id;
	}
	
	
}
