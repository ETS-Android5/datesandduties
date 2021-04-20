// package datesandduties;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import org.junit.Before;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// //import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.MockitoJUnitRunner;
// import org.springframework.beans.factory.annotation.Autowired;

// import datesandduties.Events.Event;
// import datesandduties.Events.EventController;
// import datesandduties.Events.EventRepository;

// //@RunWith(MockitoJUnitRunner.class)
// public class EventTests {

// 	@BeforeEach
// 	public void setup() {
// 		MockitoAnnotations.openMocks(this);
// 	}

// 	@Test
// 	public void testAddNewEvent() {
// 		Event event = new Event("owner", "title", "description", 2343, 2329);
// 		EventController eventController = mock(EventController.class);

// 		when(eventController.addNewEvent(event)).thenReturn("Entry Saved!");

// 		String result = eventController.addNewEvent(event);
// 		assertEquals("Entry Saved!", result);

// 		verify(eventController, times(1)).addNewEvent(event);
// 	}

// 	@InjectMocks
// 	EventController eventController = new EventController();

// 	@Mock
// 	EventRepository eventRepository;

// 	@Test
// 	public void testFindByTitle() {

// 		when(eventRepository.findByTitle("testTitle"))
// 				.thenReturn(new Event("owner", "testTitle", "description", 23430000, 2329));
// 		Event event = eventController.findByTitle("testTitle");

// 		assertEquals("owner", event.getOwner());
// 		assertEquals("testTitle", event.getTitle());
// 		assertEquals("description", event.getDescription());
// 		assertEquals((Integer) 23430000, event.getDate());
// 		assertEquals((Integer) 2329, event.getTime());

// 	}
// }
