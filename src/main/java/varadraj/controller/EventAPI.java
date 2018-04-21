package varadraj.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.BaseResponse;
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
	
	
	@PostMapping("/addEvent")
	public BaseResponse addEvent(@RequestBody Event event,Principal user) {
		event.setCreatedBy(userService.getUser(new Long(user.getName())));
		eventService.saveEvent(event);
		return new BaseResponse(201,"Event Added");
	}
	
	@GetMapping("/allUserEvents")
	public BaseResponse getAllUserEvents(Principal user){
		return new BaseResponse(200,
				eventService.getAllUserEvents(userService.getUser(new Long(user.getName()))) );
	}
	
	@DeleteMapping("/deleteEvent/{eventID}")
	public BaseResponse deleteEvent(@PathVariable long eventID, Principal user) {
		eventService.deleteEvent(eventID,new Long(user.getName()));
		return new BaseResponse(200,"Event Deleted");
	}
	
	@PutMapping("/updateEvent")
	public BaseResponse updateEvent(@RequestBody Event event,Principal user) {
		if(event.getEventID() < 1L)
			throw new RuntimeException("Valid EventID required for Event Update");
		
		eventService.updateEvent(event,new Long(user.getName()));
		
		return new BaseResponse(200,"Event Update");
	}
	

}
