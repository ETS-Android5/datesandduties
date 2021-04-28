package datesandduties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;

import datesandduties.Events.Event;
import datesandduties.Events.EventController;
import datesandduties.Events.EventRepository;

public class EventTests {

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	LocalDate date = LocalDate.of(2021, 04, 22);
	LocalTime time = LocalTime.of(6, 30);

	@Test
	public void testAddNewEvent() {

		Event event = new Event("owner", "title", "description", date, time);
		EventController eventController = mock(EventController.class);

		when(eventController.addNewEvent(event)).thenReturn("Entry Saved!" + " Your Event ID is: " + event.getId());

		String result = eventController.addNewEvent(event);
		assertEquals("Entry Saved!" + " Your Event ID is: " + event.getId(), result);

		verify(eventController, times(1)).addNewEvent(event);
	}

	@InjectMocks
	EventController eventController = new EventController();

	@Mock
	EventRepository eventRepository;

	@Test
	public void testFindByTitle() {

		when(eventRepository.findByTitle("testTitle"))
				.thenReturn(new Event("owner", "testTitle", "description", date, time));
		Event event = eventController.findByTitle("testTitle");

		assertEquals("owner", event.getOwner());
		assertEquals("testTitle", event.getTitle());
		assertEquals("description", event.getDescription());
		assertEquals(date, event.getDate());
		assertEquals(time, event.getTime());

	}

	@Test
	public void testFindByTitleLike() {

		List<Event> eventList = new ArrayList<Event>() {
			{
				add(new Event("owner1", "testTitle", "descriptive description", date, time));
				add(new Event("owner2", "testTitle", "description", date, time));
			}
		};

		when(eventRepository.findByTitleLike("testTitle")).thenReturn(eventList);

		List<Event> testList = eventController.findByTitleLike("testTitle");

		assertEquals("testTitle", testList.get(0).getTitle());
		assertEquals("testTitle", testList.get(1).getTitle());

	}
}
