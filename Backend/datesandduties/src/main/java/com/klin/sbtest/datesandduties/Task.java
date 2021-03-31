package com.klin.sbtest.datesandduties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.klin.sbtest.datesandduties.Account;


/*
 * Task_id
 * Owner
Title
Description
Priority
Due date
Recurrence
 */
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	
	@ApiModelProperty(notes = "Owner of Task", name="owner", required=true, value="test owner")
	private String owner;
	
	@ApiModelProperty(notes = "Title of Task", name="title", required=true, value="test title")
	private String title;
	
	@ApiModelProperty(notes = "Description of Task", name="description", required=true, value="test description")
	private String description;
	
	@ApiModelProperty(notes = "Priority of Task", name="priority", required=true, value="1,2,3")
	private Integer priority;
	
	@ApiModelProperty(notes = "Due Date of Task", name="date", required=true, value="test date")
	private Integer due_date;

	@ApiModelProperty(notes = "Recurrence", name="recurrence", required=true, value="daily/weekly/monthly")
	private String recurrence;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private Account account;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getDue_date() {
		return due_date;
	}

	public void setDue_date(Integer due_date) {
		this.due_date = due_date;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
