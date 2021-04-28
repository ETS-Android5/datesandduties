package datesandduties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

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

	@Test
	public void testAddNewEvent() {
		LocalDate date = LocalDate.of(2021, 04, 22);
		LocalTime time = LocalTime.of(6, 30);

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
		LocalDate date = LocalDate.of(2021, 04, 22);
		LocalTime time = LocalTime.of(6, 30);

		when(eventRepository.findByTitle("testTitle"))
				.thenReturn(new Event("owner", "testTitle", "description", date, time));
		Event event = eventController.findByTitle("testTitle");

		assertEquals("owner", event.getOwner());
		assertEquals("testTitle", event.getTitle());
		assertEquals("description", event.getDescription());
		assertEquals(date, event.getDate());
		assertEquals(time, event.getTime());

	}
}
