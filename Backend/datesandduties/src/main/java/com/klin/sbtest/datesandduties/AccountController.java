package com.klin.sbtest.datesandduties;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import com.klin.sbtest.datesandduties.Event;
import com.klin.sbtest.datesandduties.EventRepository;

import com.klin.sbtest.datesandduties.Task;
import com.klin.sbtest.datesandduties.TaskRepository;



@Api(value = "Account Controller", description = "REST APIs related to Accounts")
@RestController 
@RequestMapping(path = "/account")
public class AccountController {
	@Autowired 
	private AccountRepository accountRepository;
	
	@ApiOperation(value = "Adds an Account to the Database", response = Account.class, tags = "addNewAccount")
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public String addNewAccount(@RequestParam String name, @RequestParam String username,
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

	@ApiOperation(value = "Return all the Accouints in Database", response = Iterable.class, tags = "getAllAccounts")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	
	@GetMapping(path = "/all")
	public Iterable<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@ApiOperation(value = "Deletes an Account from the Database", response = Account.class, tags = "deleteAccount")
	@DeleteMapping(path = "/delete/{id}")
	public String deleteAccount(@PathVariable int id) {
		accountRepository.deleteById(id);
		return "Deleted Account!";
	}
	
    @GetMapping(path = "/find/{id}")
    public Optional<Account> getAccountById( @PathVariable int id){
        return accountRepository.findById(id);
    }

	
    /*@PutMapping("/account/{accountId}/events/{eventId}")
    String assignEventToAccount(@PathVariable int accountId,@PathVariable int eventId){
    	Account account = accountRepository.findById(accountId);
        Event event = eventRepository.findById(eventId);
        if(account == null || event == null)
            return "Adding Event Failed";
        event.setAccount(account);
        account.addEvents(event);
        accountRepository.save(account);
        return "Success!";
    }*/


}