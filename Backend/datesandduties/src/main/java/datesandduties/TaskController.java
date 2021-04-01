package datesandduties;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Task Controller", description = "REST APIs related to Tasks")
@RestController
@RequestMapping(path = "/task")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;
	
	@ApiOperation(value = "Adds an Task to the Database", response = Task.class, tags = "addNewTask")
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public String addNewTask(@RequestParam String owner, @RequestParam String title, @RequestParam String description,
			@RequestParam Integer priority, @RequestParam Integer due_date, @RequestParam String recurrence) {
		
		Task n = new Task();
		
		n.setOwner(owner);
		n.setTitle(title);
		n.setDescription(description);
		n.setPriority(priority);
		n.setDue_date(due_date);
		n.setRecurrence(recurrence);
		
		taskRepository.save(n);
		return "Entry Saved!";
	}
	
	@ApiOperation(value = "Return all the Tasks in Database", response = Iterable.class, tags = "getAllTasks")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })

	@GetMapping(path="/all")
	public Iterable<Task> getAllTasks() {
		return taskRepository.findAll();

	}
	
	@ApiOperation(value = "Deletes a Task from the Database", response = Task.class, tags = "deleteTask")
	@DeleteMapping(path = "/delete/{id}")
	public String deleteTask(@PathVariable int id) {
		taskRepository.deleteById(id);
		return "Deleted Task!";
	}
	
    @GetMapping(path = "/find/{id}")
    public Optional<Task> getTaskById( @PathVariable int id){
        return taskRepository.findById(id);
    }
}
