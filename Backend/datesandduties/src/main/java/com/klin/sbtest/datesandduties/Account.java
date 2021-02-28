package com.klin.sbtest.datesandduties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	// private String username;

	private String email;

	// private String gender;

	// private local/server -IDK how to implement this

	// private String password;

	// private Integer phone;

	// private String country;

	// private String SecurityQuestion; -IDK how to implement this

	private String name;

	private Integer age;

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

	/*
	 * public String getCountry() { return country; }
	 * 
	 * public void setCountry(String country) { this.country = country; }
	 * 
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * public String getGender() { return gender; }
	 * 
	 * public void setGender(String gender) { this.gender = gender; }
	 * 
	 * public String getUsername() { return username; }
	 * 
	 * public void setUsername(String username) { this.username = username; }
	 * 
	 * public Integer getPhone() { return phone; }
	 * 
	 * public void setPhone(Integer phone) { this.phone = phone; }
	 */
}