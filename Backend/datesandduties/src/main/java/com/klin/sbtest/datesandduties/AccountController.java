package com.klin.sbtest.datesandduties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/sbtest")
public class AccountController {
	@Autowired // This means to get the bean called userRepository
	private AccountRepository accountRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewAccount(@RequestParam String name, @RequestParam String username,
			@RequestParam String password, @RequestParam String email, @RequestParam String gender,
			@RequestParam Integer age, @RequestParam Integer phone, @RequestParam String country) {

		Account n = new Account();
		n.setName(name);
		n.setUsername(username);
		n.setPassword(password);
		n.setEmail(email);
		n.setGender(gender);
		n.setAge(age);
		n.setPhone(phone);
		n.setCountry(country);

		accountRepository.save(n);
		return "Entry Saved!";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Account> getAllAccounts() {
		// This returns a JSON or XML with the users
		return accountRepository.findAll();
	}
}