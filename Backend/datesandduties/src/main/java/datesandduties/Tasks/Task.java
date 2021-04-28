package datesandduties.Tasks;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import datesandduties.Accounts.Account;

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

	@ApiModelProperty(notes = "Owner of Task", name = "owner", required = true, value = "test owner")
	private String owner;

	@ApiModelProperty(notes = "Title of Task", name = "title", required = true, value = "test title")
	private String title;

	@ApiModelProperty(notes = "Description of Task", name = "description", required = true, value = "test description")
	private String description;

	@ApiModelProperty(notes = "Priority of Task", name = "priority", required = true, value = "1,2,3")
	private Integer priority;

	@ApiModelProperty(notes = "Due Date of Task", name = "date", required = true, value = "test date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime due_date;

	@ApiModelProperty(notes = "Recurrence", name = "recurrence", required = true, value = "daily/weekly/monthly")
	private String recurrence;

	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private Account account;

	public Task(String owner, String title, String description, Integer priority, LocalDateTime due_date,
			String recurrence) {
		this.setOwner(owner);
		this.setTitle(title);
		this.setDescription(description);
		this.setPriority(priority);
		this.setDue_date(due_date);
		this.setRecurrence(recurrence);
	}

	public Task() {

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
		String regex = "^[a-zA-Z0-9_!.\"  *\"]+$";
		if (title.matches(regex)) {
			this.title = title;
		} else {
			this.title = "mull";
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		String regex = "^[a-zA-Z0-9_!.,\"  *\"]+$";
		if (description.matches(regex)) {
			this.description = description;
		} else {
			this.description = "null";
		}
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		if ((priority > 0) || (priority < 4)) {
			this.priority = priority;
		} else {
			this.priority = 0;
		}
	}

	public LocalDateTime getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDateTime due_date) {

		this.due_date = due_date;
		// No need for this if we use LocalDateTime
		/*
		 * String regex = "[0-9]{2}{1}[0-9]{2}{1}[0-9]{4}"; if
		 * (Integer.toString(due_date).matches(regex)) { this.due_date = due_date; }
		 * else { this.due_date = 0; }
		 */
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

	public void resetAccount() {
		this.account = null;
	}

}
