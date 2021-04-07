package datesandduties.mock;

import datesandduties.Accounts.AccountController;
import datesandduties.Accounts.Account;
import datesandduties.Accounts.AccountRepository;

import datesandduties.Events.*;

import datesandduties.Tasks.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Captor;

import static org.mockito.Mockito.*;

import org.mockito.junit.*;

import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.*;

import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

	private static AccountController accountController = null;
	private static EventController eventController = null;
	private static TaskController taskController = null;

	private static AccountRepository accountRepository = mock(AccountRepository.class); 
	private static EventRepository eventRepository = mock(EventRepository.class);
	private static TaskRepository taskRepository = mock(TaskRepository.class);


	@BeforeEach
	public void setUp() {
		accountController = new AccountController(accountRepository);
		eventController = new EventController(eventRepository);
		taskController = new TaskController(taskRepository);
	}

	@Test
	public void findAccountByUsernameTest() {
		when(accountRepository.findByUsername("mockUsername")).thenReturn(new Account("name", "mockUsername", "password", "email", "gender", 15, 1234567890, "country"));
		
		Account account = accountController.findByUsername("mockUsername");

		assertEquals("name", account.getName());
		assertEquals("mockUsername", account.getUsername());
		assertEquals("password", account.getPassword());  
		assertEquals("email", account.getEmail());
		assertEquals("gender", account.getGender());
		assertEquals(15, account.getAge());
		assertEquals(1234567890, account.getPhone());
		assertEquals("country", account.getCountry());
	}

	@Test
	public void findEventByOwnerAndTitleTest() {
		when(eventRepository.findByOwnerAndTitle("testOwner", "testTitle")).thenReturn(new Event("testOwner", "testTitle", "testDescription", 123456, 654321));

		Event event = eventController.getByOwnerAndTitle("testOwner", "testTitle");

		assertEquals("testOwner", event.getOwner());
		assertEquals("testTitle", event.getTitle());
		assertEquals("testDescription", event.getDescription());
		assertEquals(123456, event.getDate());
		assertEquals(654321, event.getTime());			

	}

	@Test
	public void findTaskByOwnerAndTitleTest() {
		when(taskRepository.findByOwnerAndTitle("testOwner", "testTitle")).thenReturn(new Task("testOwner", "testTitle", "testDescription", 1, 12, "never"));

		Task task = taskController.getByOwnerAndTitle("testOwner", "testTitle");

		assertEquals("testOwner", task.getOwner());
		assertEquals("testTitle", task.getTitle());
		assertEquals("testDescription", task.getDescription());
		assertEquals(1, task.getPriority());
		assertEquals(12, task.getDue_date());
		assertEquals("never", task.getRecurrence());
	}
	

	@Test
	public void updateAccountTest() throws Exception {

		Account newAccount = new Account("name2", "mockUsername2", "password2", "email2", "gender2", 1, 1, "country2");
		when(accountRepository.save(any(Account.class))).thenReturn(newAccount);

		Account testAccount = accountRepository.save(new Account("name", "mockUsername", "password", "email", "gender", 12, 12, "country"));	

		assertEquals(testAccount, newAccount);
	}
	
}


