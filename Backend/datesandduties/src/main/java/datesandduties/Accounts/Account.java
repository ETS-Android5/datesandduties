package datesandduties.Accounts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern.*;

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

	// Constructor in order to have Dependencies
	public Account(String name, String username, String password, String email, String gender, Integer age,
			Integer phone, String country) {
		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setGender(gender);
		this.setAge(age);
		this.setPhone(phone);
		this.setCountry(country);
	}

	public Account() {
		events = new ArrayList<>();
		tasks = new ArrayList<>();
	}

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
		if ((age > 0) || (age <= 122)) {
			this.age = age;
		} else {
			this.age = 0;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.chars().allMatch(Character::isLetter)) {
			this.name = name;
		} else {
			this.name = "null";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (email.matches(regex)) {
			this.email = email;
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		if (country.chars().allMatch(Character::isLetter)) {
			this.country = country;
		} else {
			this.country = "null";
		}
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
		if (gender.chars().allMatch(Character::isLetter)) {
			this.gender = gender;
		} else {
			this.gender = "null";
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username.chars().allMatch(Character::isLetter)) {
			this.username = username;
		} else {
			this.username = "null";
		}
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		if ((Integer.toString(phone).matches("\\d{10}"))
				|| (Integer.toString(phone).matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
				|| (Integer.toString(phone).matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
				|| (Integer.toString(phone).matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
				|| (Integer.toString(phone).matches("\\d{4}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}"))
				|| (Integer.toString(phone).matches("\\(\\d{5}\\)-\\d{3}-\\d{3}"))
				|| (Integer.toString(phone).matches("\\(\\d{4}\\)-\\d{3}-\\d{3}"))) {
			this.phone = phone;
		} else {
			this.phone = 0;
		}
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
