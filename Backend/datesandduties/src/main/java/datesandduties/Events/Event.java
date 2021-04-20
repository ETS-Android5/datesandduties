package datesandduties.Events;

import java.util.Date;

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
	private Integer date;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
	//private Date date;

	//https://stackoverflow.com/questions/37612603/parsing-date-object-in-postman-to-be-converted-to-c-sharp-datetime
	//https://salesforce.stackexchange.com/questions/128685/how-to-give-date-and-time-values-in-request-body-using-postman
	//https://www.javaer101.com/en/article/11527705.html
	//https://medium.com/@andylke/rest-controller-configure-date-time-format-for-request-parameter-80fd76a7dff1
	//https://stackoverflow.com/questions/15164864/how-to-accept-date-params-in-a-get-request-to-spring-mvc-controller
	//https://www.baeldung.com/spring-date-parameters
	//https://www.baeldung.com/spring-boot-formatting-json-dates

	//A lot of things to troubleshoot hopefully by end of tonight

	@ApiModelProperty(notes = "Time of Event", name = "time", required = true, value = "test time")
	private Integer time;

	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private Account account;

	public Event(String owner, String title, String description, Integer date, Integer time) {
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
		}
		else {
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
		}
		else {
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
		}
		else {
			this.description = "null";
		}
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		//this.date = date;
		//trying out java.util.Date 
		//Trying out Date gave this error when inputting new Event: 
		//SQL Error: 1265, SQLState: 01000
		//Data truncated for column 'date' at row 1
		//Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.orm.jpa.JpaSystemException: 
		//could not execute statement; nested exception is org.hibernate.exception.GenericJDBCException: could not execute statement] with root cause

		//Essentially this error is saying that the the column date doesn't have enough room. Not sure how to fix this.
		String regex = "[0-9]{2}{1}[0-9]{2}{1}[0-9]{4}"; // This is just checking to make sure the input is [DD][MM][YYYY]
		if (Integer.toString(date).matches(regex)) {
			this.date = date;
		}
		else {
			this.date = 0;
		}

	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
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
