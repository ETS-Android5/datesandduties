package datesandduties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;

import datesandduties.Accounts.Account;
import datesandduties.Accounts.AccountController;
import datesandduties.Accounts.AccountRepository;

import datesandduties.Events.Event;
import datesandduties.Events.EventController;
import datesandduties.Events.EventRepository;

import datesandduties.Tasks.Task;
import datesandduties.Tasks.TaskController;
import datesandduties.Tasks.TaskRepository;

//@RunWith(MockitoJUnitRunner.class)
public class AccountTests {

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddNewAccount() {
		Account account = new Account("name", "mockUsername", "password", "email", "gender", 15, 1234567890, "country");
		AccountController testController = mock(AccountController.class);

		when(testController.addNewAccount(account))
				.thenReturn("Entry Saved!" + " Your Account ID is: " + account.getId());

		String result = testController.addNewAccount(account);
		assertEquals("Entry Saved!" + " Your Account ID is: " + account.getId(), result);

		verify(testController, times(1)).addNewAccount(account);

	}

	@InjectMocks
	AccountController acctController = new AccountController();

	@Mock
	AccountRepository acctRepository;

	@Test
	public void testFindByUsername() {

		when(acctRepository.findByUsername("mockUsername")).thenReturn(new Account("name", "mockUsername", "password",
				"email@email.com", "gender", 15, 1234567890, "country"));
		Account account = acctController.findByUsername("mockUsername");

		assertEquals("name", account.getName());
		assertEquals("mockUsername", account.getUsername());
		assertEquals("password", account.getPassword());
		assertEquals("email@email.com", account.getEmail());
		assertEquals("gender", account.getGender());
		assertEquals((Integer) 15, account.getAge());
		assertEquals((Integer) 1234567890, account.getPhone());
		assertEquals("country", account.getCountry());

	}

	@Test
	public void testFindByUsernameLike() {

		List<Account> acctList = new ArrayList<Account>() {
			{
				add(new Account("name", "mockUser", "password", "email@email.com", "gender", 15, 1234567890,
						"country"));
				add(new Account("test2", "mockUser", "pswd", "email@email.com", "male", 20, 1234567890, "country"));
			}
		};

		when(acctRepository.findByUsernameLike("mockUser")).thenReturn(acctList);

		List<Account> testList = acctController.findByUsernameLike("mockUser");

		assertEquals("mockUser", testList.get(0).getUsername());
		assertEquals("mockUser", testList.get(1).getUsername());

	}

	@Test
	public void testAddTaskToAccount() {
		LocalDateTime due_date = LocalDateTime.parse("2021-04-22T06:30:00");
		Account account = new Account("name", "mockUsername", "password", "email", "gender", 15, 1234567890, "country");
		Task task = new Task("owner", "testTitle", "description", 2343, due_date, "recurrence");
		account.setId(15);
		task.setId(16);

		AccountController testController = mock(AccountController.class);
		when(testController.addTaskToAccount(account.getId(), task.getId())).thenReturn("Success!");
		String testReturn = testController.addTaskToAccount(account.getId(), task.getId());

		assertEquals("Success!", testReturn);
		assertEquals((Integer) 15, account.getId());
		assertEquals((Integer) 16, task.getId());

		verify(testController, times(1)).addTaskToAccount(account.getId(), task.getId());

	}

	@Test
	public void testAddEventToAccount() {
		LocalDate date = LocalDate.of(2021, 04, 22);
		LocalTime time = LocalTime.of(6, 30);

		Account account = new Account("name", "mockUsername", "password", "email", "gender", 15, 1234567890, "country");
		Event event = new Event("owner", "title", "description", date, time);
		account.setId(15);
		event.setId(16);

		AccountController testController = mock(AccountController.class);

		when(testController.addEventToAccount(account.getId(), event.getId())).thenReturn("Success!");
		String testReturn = testController.addEventToAccount(account.getId(), event.getId());

		assertEquals("Success!", testReturn);

		assertEquals((Integer) 15, account.getId());
		assertEquals((Integer) 16, event.getId());

		verify(testController, times(1)).addEventToAccount(account.getId(), event.getId());
	}

	@Test
	public void testDeleteEventFromAccount() {
		LocalDate date = LocalDate.of(2021, 04, 22);
		LocalTime time = LocalTime.of(6, 30);

		Account account = new Account("name", "mockUsername", "password", "email", "gender", 15, 1234567890, "country");
		Event event = new Event("owner", "title", "description", date, time);
		account.setId(15);
		event.setId(16);

		AccountController testController = mock(AccountController.class);

		when(testController.removeEvent(account.getId(), event.getId())).thenReturn("Task Removed from Account!");
		String testReturn = testController.removeEvent(account.getId(), event.getId());

		assertEquals("Task Removed from Account!", testReturn);
		verify(testController, times(1)).removeEvent(account.getId(), event.getId());
	}


	@Test
	public void testRemoveTaskFromAccount() {
		LocalDateTime due_date = LocalDateTime.parse("2021-04-22T06:30:00");
		Account account = new Account("name", "mockUsername", "password", "email", "gender", 15, 1234567890, "country");
		Task task = new Task("owner", "testTitle", "description", 2343, due_date, "recurrence");
		account.setId(15);
		task.setId(16);

		AccountController testController = mock(AccountController.class);
		when(testController.removeTask(account.getId(), task.getId())).thenReturn("Task Removed from Account!");
		String testReturn = testController.removeTask(account.getId(), task.getId());

		assertEquals("Task Removed from Account!", testReturn);
		verify(testController, times(1)).removeTask(account.getId(), task.getId());

	}
}
