package varadraj.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.Event;
import varadraj.service.db.EventService;
import varadraj.service.db.UserService;

@RestController
@RequestMapping("/api/event")
public class EventAPI {
	
	@Autowired
	private EventService eventService;
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String event(Principal p) {
		return "EventAPI "+p.getName();
	}
	
	@PostMapping("/addEvent")
	public String addEvent(@RequestBody Event event,Principal user) {
		event.setCreatedBy(userService.getUser(new Long(user.getName())));
		eventService.saveEvent(event);
		return "Event Added";
	}
	
	@GetMapping("/allUserEvents")
	public List<Event> getAllUserEvents(Principal user){
		return eventService.getAllUserEvents(userService.getUser(new Long(user.getName())));
	}
	
	@DeleteMapping("/deleteEvent/{eventID}")
	public String deleteEvent(@PathVariable long eventID, Principal user) {
		eventService.deleteEvent(eventID,new Long(user.getName()));
		return "Event Deleted";
	}
	
	@PutMapping("/updateEvent")
	public String updateEvent(@RequestBody Event event,Principal user) {
		if(event.getEventID() < 1L)
			throw new RuntimeException("Valid EventID required for Event Update");
		
		eventService.updateEvent(event,new Long(user.getName()));
		
		return "Event Update";
	}
	

}
