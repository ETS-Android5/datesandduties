package datesandduties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;

import datesandduties.Tasks.Task;
import datesandduties.Tasks.TaskController;
import datesandduties.Tasks.TaskRepository;

//@RunWith(MockitoJUnitRunner.class)
public class TaskTests {

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	LocalDateTime due_date = LocalDateTime.parse("2021-04-22T06:30:00");

	@Test
	public void testAddNewTask() {

		Task task = new Task("owner", "title", "description", 1, due_date, "recurrence");
		TaskController taskController = mock(TaskController.class);

		when(taskController.addNewTask(task)).thenReturn("Entry Saved!" + " Your Task ID is: " + task.getId());

		String result = taskController.addNewTask(task);
		assertEquals("Entry Saved!" + " Your Task ID is: " + task.getId(), result);

		verify(taskController, times(1)).addNewTask(task);
	}

	@InjectMocks
	TaskController taskController = new TaskController();

	@Mock
	TaskRepository taskRepository;

	@Test
	public void testFindByTitle() {

		when(taskRepository.findByTitle("testTitle"))
				.thenReturn(new Task("owner", "testTitle", "description", 2343, due_date, "recurrence"));
		Task task = taskController.findByTitle("testTitle");

		assertEquals("owner", task.getOwner());
		assertEquals("testTitle", task.getTitle());
		assertEquals("description", task.getDescription());
		assertEquals((Integer) 2343, task.getPriority());
		assertEquals(due_date, task.getDue_date());
		assertEquals("recurrence", task.getRecurrence());

	}

	@Test
	public void testFindByTitleLike() {

		List<Task> taskList = new ArrayList<Task>() {
			{
				add(new Task("owner1", "mockTitle", "descriptionHere", 2343, due_date, "recurrence"));
				add(new Task("owner3", "mockTitle", "testDescription", 2343, due_date, "recurrence"));
			}
		};

		when(taskRepository.findByTitleLike("mockTitle")).thenReturn(taskList);

		List<Task> testList = taskRepository.findByTitleLike("mockTitle");

		assertEquals("mockTitle", testList.get(0).getTitle());
		assertEquals("mockTitle", testList.get(1).getTitle());

	}

}
