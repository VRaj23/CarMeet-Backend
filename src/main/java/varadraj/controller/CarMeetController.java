package varadraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.Users;
import varadraj.service.db.UserService;

@RestController
public class CarMeetController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/")
	public String hello() {
		System.out.println("GET request");
		return "Hello CarMeet";
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(
     @RequestParam("email") String username,
	 @RequestParam("password") String password	) {
		System.out.println("registerUser "+username);
		Users user = new Users(username.trim(), bCryptPasswordEncoder.encode(password.trim()));
		long id = userService.saveUser(user);
		return "User registered "+new Long(id).toString();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
	 @RequestParam("email") String username,
	 @RequestParam("password") String password) {
		if(userService.validateLogin(username, password))
			return "Logged In";
		else
			return "Login Failed";
	}

}
