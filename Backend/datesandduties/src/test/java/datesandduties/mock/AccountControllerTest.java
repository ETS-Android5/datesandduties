// package datesandduties.mock;

// import datesandduties.Accounts.AccountController;
// import datesandduties.Accounts.Account;
// import datesandduties.Accounts.AccountRepository;

// import datesandduties.Events.*;

// import datesandduties.Tasks.*;

// import java.util.ArrayList;
// import java.util.List;

// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.InjectMocks;
// import org.mockito.Captor;

// import static org.mockito.Mockito.*;

// import org.mockito.junit.*;

// import org.mockito.junit.jupiter.MockitoExtension;

// import org.junit.*;

// import org.junit.jupiter.api.*;
// import org.junit.jupiter.api.Test;

// //import org.junit.jupiter.api.DisplayName;
// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.extension.ExtendWith;


// @ExtendWith(MockitoExtension.class)
// public class AccountControllerTest {

// 	/*private static AccountController accountController = null;
// 	private static EventController eventController = null;
// 	private static TaskController taskController = null;*/

// 	private static AccountRepository accountRepository = mock(AccountRepository.class); 
// 	private static EventRepository eventRepository = mock(EventRepository.class);
// 	private static TaskRepository taskRepository = mock(TaskRepository.class);
	
// 	@BeforeEach
// 	public void setup() {
// 		MockitoAnnotations.openMocks(this);
// 	}

// 	@InjectMocks
// 	EventController eventController;

// 	@InjectMocks
// 	TaskController taskController;
	
// 	@InjectMocks
// 	AccountController accountController;
	
// 	@Test
// 	public void findEventByOwnerAndTitleTest() {

// 		when(eventRepository.findByOwnerAndTitle("testOwner", "testTitle")).thenReturn(new Event("testOwner", "testTitle", "testDescription", 12345600, 654321));

// 		Event event = eventController.getByOwnerAndTitle("testOwner", "testTitle");

// 		assertEquals("testOwner", event.getOwner());
// 		assertEquals("testTitle", event.getTitle());
// 		assertEquals("testDescription", event.getDescription());
// 		assertEquals(12345600, event.getDate());
// 		assertEquals(654321, event.getTime());			

// 	}

// 	@Test
// 	public void findTaskByOwnerAndTitleTest() {

// 		when(taskRepository.findByOwnerAndTitle("testOwner", "testTitle")).thenReturn(new Task("testOwner", "testTitle", "testDescription", 1, 12121212, "never"));

// 		Task task = taskController.getByOwnerAndTitle("testOwner", "testTitle");

// 		assertEquals("testOwner", task.getOwner());
// 		assertEquals("testTitle", task.getTitle());
// 		assertEquals("testDescription", task.getDescription());
// 		assertEquals(1, task.getPriority());
// 		assertEquals(12121212, task.getDue_date());
// 		assertEquals("never", task.getRecurrence());
// 	}
	

// 	@Test
// 	public void updateAccountTest() throws Exception {
// 		Account newAccount = new Account("nameTwo", "mockUsername2", "password2", "email2", "gender2", 1, 1, "country2");
// 		when(accountRepository.save(any(Account.class))).thenReturn(newAccount);

// 		Account testAccount = accountRepository.save(new Account("name", "mockUsername", "password", "email", "gender", 12, 12, "country"));	

// 		assertEquals(testAccount.getName(), "nameTwo");
// 	}

// 	@Test
// 	public void updateEventTest() throws Exception {
// 		Event newEvent = new Event("testOwnerTwo", "testTitle2", "testDescription2", 1, 1);
// 		when(eventRepository.save(any(Event.class))).thenReturn(newEvent);

// 		Event testEvent = eventRepository.save(new Event("testOwner", "testTitle", "testDescription", 123456, 654321));

// 		assertEquals(testEvent.getOwner(), "testOwnerTwo");
// 	}

// 	@Test
// 	public void updateTaskTest() throws Exception {
// 		Task newTask = new Task("testOwnerTwo", "testTitle2", "testDescription2", 2, 1, "always");
// 		when(taskRepository.save(any(Task.class))).thenReturn(newTask);

// 		Task testTask = taskRepository.save(new Task("testOwner", "testTitle", "testDescription", 1, 2, "never"));

// 		assertEquals(testTask.getOwner(), "testOwnerTwo");
// 	}

// }


