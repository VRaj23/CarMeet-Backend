package varadraj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarMeetController {
	
	@RequestMapping("/")
	public String hello() {
		return "Hello CarMeet";
	}

}
