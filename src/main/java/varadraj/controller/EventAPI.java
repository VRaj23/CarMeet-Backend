package varadraj.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventAPI {
	
	@GetMapping
	public String event(Principal p) {
		return "EventAPI "+p.getName();
	}

}
