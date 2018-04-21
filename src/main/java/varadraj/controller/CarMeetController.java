package varadraj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.BaseResponse;

@RestController
@RequestMapping("/")
public class CarMeetController {

	@GetMapping
	public BaseResponse hello() {
		return new BaseResponse(200, new String("CarMeet Backend"));
	}
}
