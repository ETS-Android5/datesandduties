package datesandduties.Events;

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

import datesandduties.Accounts.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Event Controller", description = "REST APIs related to Events")
@RestController
@RequestMapping(path = "/event")
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
	@ApiOperation(value = "Adds an Event to the Database", response = Event.class, tags = "addNewEvent")
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public String addNewEvent(@RequestParam String owner, @RequestParam String title, 
			@RequestParam String description, @RequestParam Integer date, @RequestParam Integer time) {
		
		Event n = new Event();
		n.setOwner(owner);
		n.setTitle(title);
		n.setDescription(description);
		n.setDate(date);
		n.setTime(time);


		eventRepository.save(n);
		return "Entry Saved!";
	}
	
	@ApiOperation(value = "Return all the Events in Database", response = Iterable.class, tags = "getAllEvents")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	
	@GetMapping(path = "/all")
	public Iterable<Event> getAllEvents() {
		// This returns a JSON or XML with the users
		return eventRepository.findAll();
	}
	
	@ApiOperation(value = "Deletes an Event from the Database", response = Event.class, tags = "deleteEvent")
	@DeleteMapping(path = "/delete/{id}")
	public String deleteEvent(@PathVariable int id) {
		eventRepository.deleteById(id);
		return "Deleted Event!";
	}
	
    @GetMapping(path = "/find/{id}")
    public Optional<Event> getEventtById( @PathVariable int id){
        return eventRepository.findById(id);
    }
	
	@PutMapping(path = "/update/{id}")
	public Event updateEvent(@PathVariable int id, @RequestBody Event request) {
		Optional<Event> event = eventRepository.findById(id);
		if (event == null) {
			return null;
		}
		eventRepository.save(request);
		return eventRepository.findById(id).get();
	}
	@GetMapping(path = "/findEvent/{title}")
	public Event searchByTitle(@PathVariable String title) {
		return eventRepository.findByTitle(title);
	}

	

}
