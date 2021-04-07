package datesandduties.Accounts;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import datesandduties.Events.Event;
import datesandduties.Events.EventRepository;
import datesandduties.Tasks.Task;
import datesandduties.Tasks.TaskRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Account Controller", description = "REST APIs related to Accounts")
@RestController
@RequestMapping(path = "/account")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	public AccountController(AccountRepository accountRepository) { // Necessary to mock Mockito repo
		this.accountRepository = accountRepository;
	}
	
	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private TaskRepository taskRepository;
	
	@ApiOperation(value = "Adds an Account to the Database", response = Account.class, tags = "addNewAccount")
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public String addNewAccount(@RequestBody Account account) {
		if (account == null) {
			return "Account Creation Failed";
		}
		accountRepository.save(account);
		return "Entry Saved!";
	}

	public String addNewAccount(Account account) { // adding this to make my life easier for Mockito
		accountRepository.save(account);
		return "Entry Saved!";
	}

	@ApiOperation(value = "Return all the Accouints in Database", response = Iterable.class, tags = "getAllAccounts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
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
	public Optional<Account> getAccountById(@PathVariable int id) {
		return accountRepository.findById(id);
	}

	@PutMapping(path = "/update/{id}")
	public Account updateAccount(@PathVariable int id, @RequestBody Account request) {
		Optional<Account> event = accountRepository.findById(id);
		if (event == null) {
			return null;
		}
		accountRepository.save(request);
		return accountRepository.findById(id).get();
	}

<<<<<<< Backend/datesandduties/src/main/java/datesandduties/Accounts/AccountController.java
	@GetMapping(path = "/getByUsername/{username}")
	public Account findByUsername(@PathVariable String username) {
		return accountRepository.findByUsername(username);
	} //Adding this comment so that I can push again, because it says I'm up to date.

	@RequestMapping(path = "/login/{username}/{password}")
	public String loginWork(@PathVariable String username, @PathVariable String password) {
		if (accountRepository.findByUsername(username) == null) {
			return "Login failed.";
		}
		if (accountRepository.findByUsername(username).getPassword().equals(password)) {
			return Integer.toString(accountRepository.findByUsername(username).getId());
		}
		
		return "Login failed.";
	}

	@PostMapping(path = "/update/{id}")
	public Account updateAccountInfo(@PathVariable Integer id, Account request) {
		Optional<Account> account = accountRepository.findById(id);
		/*if (account == null) {
			return null;
		}*/
		accountRepository.save(request);
		return accountRepository.findById(id).get(); 
	}	
}
=======
	@GetMapping(path = "/events/{id}")
	public List<Event> accountEvents(@PathVariable int id) {
		Account account = accountRepository.findById(id).get();
		return account.getEvents();
	}

	@GetMapping(path = "/tasks/{id}")
	public List<Task> accountTasks(@PathVariable int id) {
		Account account = accountRepository.findById(id).get();
		return account.getTasks();
	}

	@PutMapping(path = "/assignEvent/{accountId}/{eventId}")
	public String addEventToAccount(@PathVariable int accountId, @PathVariable int eventId) {
		Account account = accountRepository.findById(accountId).get();
		Event event = eventRepository.findById(eventId).get();
		if (account == null || event == null) {
			return "Adding Event Failed";
		}
		event.setAccount(account);
		account.addEvents(event);
		accountRepository.save(account);
		eventRepository.save(event);
		return "Success!";
	}

	@PutMapping(path = "/assignTask/{accountId}/{taskId}")
	public String addTaskToAccount(@PathVariable int accountId, @PathVariable int taskId) {
		Account account = accountRepository.findById(accountId).get();
		Task task = taskRepository.findById(taskId).get();
		if (account == null || task == null) {
			return "Adding Task Failed";
		}
		task.setAccount(account);
		account.addTasks(task);
		accountRepository.save(account);
		taskRepository.save(task);
		return "Success!";
	}

	@PutMapping(path = "/removeEvent/{accountId}/{eventId}")
	public String removeEvent(@PathVariable int accountId, @PathVariable int eventId) {
		Account account = accountRepository.findById(accountId).get();
		Event event = eventRepository.findById(eventId).get();
		if (account == null || event == null) {
			return "Remove Event Failed";
		}
		event.resetAccount();
		account.removeEvents(event);
		accountRepository.save(account);
		eventRepository.save(event);
		return "Event Removed From Account!";
	}

	@PutMapping(path = "/removeTask/{accountId}/{eventId}")
	public String removeTask(@PathVariable int accountId, @PathVariable int taskId) {
		Account account = accountRepository.findById(accountId).get();
		Task task = taskRepository.findById(taskId).get();
		if (account == null || task == null) {
			return "Remove Task Failed";
		}
		task.resetAccount();
		account.removeTasks(task);
		accountRepository.save(account);
		taskRepository.save(task);
		return "Task Removed from Account!";
	}

	@GetMapping(path = "/findUser/{username}")
	public Account findByUsername(@PathVariable String username) {
		return accountRepository.findByUsername(username);
	}
}
>>>>>>> Backend/datesandduties/src/main/java/datesandduties/Accounts/AccountController.java
