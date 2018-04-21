package varadraj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CarMeetController {

	@GetMapping
	public String hello() {
		return "CarMeet Backend";
	}
}
