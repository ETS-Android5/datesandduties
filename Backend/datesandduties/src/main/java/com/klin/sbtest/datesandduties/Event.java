package com.klin.sbtest.datesandduties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

/* Event_id
Title
Description
Date
Time
Who can share, view, and edit
Owner  */

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;
	@ApiModelProperty(notes = "Owner of Event", name="owner", required=true, value="test owner")
	private String owner;
	
	@ApiModelProperty(notes = "Title of Event", name="title", required=true, value="test title")
	private String title;
	
	@ApiModelProperty(notes = "Description of Event", name="description", required=true, value="test description")
	private String description;

	@ApiModelProperty(notes = "Date of Event", name="date", required=true, value="test date")
	private Integer date;

	@ApiModelProperty(notes = "Time of Event", name="time", required=true, value="test time")
	private Integer time;
	
	
	//@ApiModelProperty(notes = "Gender of Account Holder", name="password", required=false, value="test gender")
	//idk how to implement this. List of IDs that have access?
	//private String sharing;


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

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	

}
