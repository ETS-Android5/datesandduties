package datesandduties.Accounts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import datesandduties.Events.Event;
import datesandduties.Tasks.Task;
import io.swagger.annotations.ApiModelProperty;

/*Need to implement the following:
 * Username -done
 * Email -done
 * Gender -done
 * Local/Server -unsure how to implement
 * Password -done
 * Phone -done	
 * Country -done
 * Security Question -unsure how to implement
 * Name -done
 * Age -done
 */

@Entity // This tells Hibernate to make a table out of this class
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;

	@ApiModelProperty(notes = "Name of Account Holder", name = "name", required = true, value = "test name")
	private String name;

	@ApiModelProperty(notes = "Username of Account", name = "username", required = true, value = "test username")
	private String username;

	@ApiModelProperty(notes = "Password of Account", name = "password", required = true, value = "test password")
	private String password;

	@ApiModelProperty(notes = "Email Address of Account", name = "email", required = true, value = "test email address")
	private String email;

	@ApiModelProperty(notes = "Gender of Account Holder", name = "password", required = false, value = "test gender")
	private String gender;

	@ApiModelProperty(notes = "Age of Account Holder", name = "age", required = true, value = "test age")
	private Integer age;

	// private local/server; //IDK how to implement this

	@ApiModelProperty(notes = "Phone Number of Account", name = "phone", required = true, value = "test phone number")
	private Integer phone;

	@ApiModelProperty(notes = "Country of Account Holder", name = "country", required = true, value = "test country")
	private String country;

	// private String SecurityQuestion; //IDK how to implement this

	@OneToMany
	private List<Event> events;

	@OneToMany
	private List<Task> tasks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public void addEvents(Event event) {
		this.events.add(event);
	}

	public void removeEvents(Event event) {
		this.events.remove(event);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void addTasks(Task task) {
		this.tasks.add(task);
	}

	public void removeTasks(Task task) {
		this.tasks.remove(task);
	}

}