// package datesandduties.mock;

// import datesandduties.Accounts.AccountController;
// import datesandduties.Accounts.Account;
// import datesandduties.Accounts.AccountRepository;
// import datesandduties.Events.*;
// import datesandduties.Tasks.*;

// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.LocalTime;

// import org.mockito.MockitoAnnotations;
// import org.mockito.InjectMocks;
// import static org.mockito.Mockito.*;

// import org.mockito.junit.jupiter.MockitoExtension;
// import org.junit.jupiter.api.*;
// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.extension.ExtendWith;

// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.web.servlet.View;
// import org.mockito.Mock;

// import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// import java.util.Optional;
// import java.util.Objects;

// @ExtendWith(MockitoExtension.class)
// public class mockitoTest {

// 	private static AccountRepository accountRepository = mock(AccountRepository.class);
// 	private static EventRepository eventRepository = mock(EventRepository.class);
// 	private static TaskRepository taskRepository = mock(TaskRepository.class);

// 	LocalDate date = LocalDate.of(2021, 04, 22);
// 	LocalTime time = LocalTime.of(6, 30);
// 	LocalDateTime due_date = LocalDateTime.parse("2021-04-22T06:30:00");

// 	private MockMvc mockMvcAccount;

// 	@BeforeEach
// 	public void setup() {
// 		MockitoAnnotations.openMocks(this);
// 		mockMvcAccount = MockMvcBuilders.standaloneSetup(accountController).build();
// 	}

// 	@InjectMocks
// 	EventController eventController;

// 	@InjectMocks
// 	TaskController taskController;

// 	@InjectMocks
// 	AccountController accountController;

// 	@Test
// 	public void findEventByOwnerAndTitleTest() {

// 		when(eventRepository.findByOwnerAndTitle("testOwner", "testTitle"))
// 				.thenReturn(new Event("testOwner", "testTitle", "testDescription", date, time));

// 		Event event = eventController.getByOwnerAndTitle("testOwner", "testTitle");

// 		assertEquals("testOwner", event.getOwner());
// 		assertEquals("testTitle", event.getTitle());
// 		assertEquals("testDescription", event.getDescription());
// 		assertEquals(date, event.getDate());
// 		assertEquals(time, event.getTime());

// 	}

// 	@Test
// 	public void findTaskByOwnerAndTitleTest() {

// 		when(taskRepository.findByOwnerAndTitle("testOwner", "testTitle"))
// 				.thenReturn(new Task("testOwner", "testTitle", "testDescription", 1, due_date, "never"));

// 		Task task = taskController.getByOwnerAndTitle("testOwner", "testTitle");

// 		assertEquals("testOwner", task.getOwner());
// 		assertEquals("testTitle", task.getTitle());
// 		assertEquals("testDescription", task.getDescription());
// 		assertEquals(1, task.getPriority());
// 		assertEquals(due_date, task.getDue_date());
// 		assertEquals("never", task.getRecurrence());
// 	}

// 	@Test
// 	public void updateAccountTest() throws Exception {
// 		Account newAccount = new Account("nameTwo", "mockUsername2", "password2", "email2", "gender2", 1, 1,
// 				"country2");
// 		when(accountRepository.save(any(Account.class))).thenReturn(newAccount);

// 		Account testAccount = accountRepository
// 				.save(new Account("name", "mockUsername", "password", "email", "gender", 12, 12, "country"));

// 		assertEquals(testAccount.getName(), "nameTwo");
// 	}

// 	@Test
// 	public void updateEventTest() throws Exception {
// 		Event newEvent = new Event("testOwnerTwo", "testTitle2", "testDescription2", date, time);
// 		when(eventRepository.save(any(Event.class))).thenReturn(newEvent);

// 		Event testEvent = eventRepository.save(new Event("testOwner", "testTitle", "testDescription", date, time));

// 		assertEquals(testEvent.getOwner(), "testOwnerTwo");
// 	}

// 	@Test
// 	public void updateTaskTest() throws Exception {
// 		Task newTask = new Task("testOwnerTwo", "testTitle2", "testDescription2", 2, due_date, "always");
// 		when(taskRepository.save(any(Task.class))).thenReturn(newTask);

// 		Task testTask = taskRepository
// 				.save(new Task("testOwner", "testTitle", "testDescription", 1, due_date, "never"));

// 		assertEquals(testTask.getOwner(), "testOwnerTwo");
// 	}


// 	@Test
// 	public void deleteAccountTest() throws Exception {

// 		doThrow(new IllegalStateException("Ran the delete account.")).when(accountRepository).deleteById(1);
		
// 		Exception exception = assertThrows(IllegalStateException.class, () -> {
// 			accountController.deleteAccount(1);
// 		});

// 		String expectedMessage = "Ran the delete account.";
// 		String actualMessage = exception.getMessage();

// 		assertTrue(actualMessage.contains(expectedMessage));

// 		//Throwable exception = assertThrows(RuntimeException.class, accountController.deleteAccount(1));

// 		//assertEquals("Ran the delete account.", exception.getMessage());
		
// 		//assertRaises(IllegalStateException);

// 		//assertThrows(accountController.deleteAccount(1), RuntimeException);
// 	}

// 	@Test
// 	public void deleteEventTest() throws Exception {
// 		doThrow(new IllegalStateException("Ran the delete event.")).when(eventRepository).deleteById(1);

// 		Exception exception = assertThrows(IllegalStateException.class, () -> {
// 			eventController.deleteEvent(1);
// 		});

// 		String expectedMessage = "Ran the delete event.";
// 		String actualMessage = exception.getMessage();

// 		assertTrue(actualMessage.contains(expectedMessage));
	
// 	}

// 	@Test
// 	public void deleteTaskTest() throws Exception {
// 		doThrow(new IllegalStateException("Ran the delete task.")).when(taskRepository).deleteById(1);

// 		Exception exception = assertThrows(IllegalStateException.class, () -> {
// 			taskController.deleteTask(1);
// 		});

// 		String expectedMessage = "Ran the delete task.";
// 		String actualMessage = exception.getMessage();

// 		assertTrue(actualMessage.contains(expectedMessage));
	
// 	}
// }
