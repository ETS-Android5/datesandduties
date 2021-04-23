package datesandduties.Events;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import datesandduties.Accounts.Account;
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
	@ApiModelProperty(notes = "Owner of Event", name = "owner", required = true, value = "test owner")
	private String owner;

	@ApiModelProperty(notes = "Title of Event", name = "title", required = true, value = "test title")
	private String title;

	@ApiModelProperty(notes = "Description of Event", name = "description", required = true, value = "test description")
	private String description;

	@ApiModelProperty(notes = "Date of Event", name = "date", required = true, value = "test date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	// https://www.baeldung.com/spring-date-parameters
	// https://www.baeldung.com/spring-boot-formatting-json-dates

	@ApiModelProperty(notes = "Time of Event", name = "time", required = true, value = "test time")
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime time;

	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private Account account;

	public Event(String owner, String title, String description, LocalDate date, LocalTime time) {
		this.setOwner(owner);
		this.setTitle(title);
		this.setDescription(description);
		this.setDate(date);
		this.setTime(time);
	}

	public Event() {

	}

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
		if (owner.chars().allMatch(Character::isLetter)) {
			this.owner = owner;
		} else {
			this.owner = "null";
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String regex = "^[a-zA-Z0-9_]+$";
		if (title.matches(regex)) {
			this.title = title;
		} else {
			this.title = "null";
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		String regex = "^[a-zA-Z0-9_]+$";
		if (description.matches(regex)) {
			this.description = description;
		} else {
			this.description = "null";
		}
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
		// No longer need below because only valid Dates are taken in by ava.util.Date
		/*
		 * String regex = "[0-9]{2}{1}[0-9]{2}{1}[0-9]{4}"; // This is just checking to
		 * make sure the input is [DD][MM][YYYY] if
		 * (Integer.toString(date).matches(regex)) { this.date = date; } else {
		 * this.date = 0; }
		 */

	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void resetAccount() {
		this.account = null;
	}
}
