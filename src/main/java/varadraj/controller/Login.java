package varadraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.jwt.JwtGenerator;
import varadraj.model.jwt.LoginRequest;
import varadraj.service.db.UserService;

@RestController
@RequestMapping("/login")
public class Login {
	
	@Autowired
	private UserService userService;
	
	private JwtGenerator jwtGenerator;
	
	public Login(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}
	
	@PostMapping
	public String login(@RequestBody final LoginRequest loginRequest) {
		if(userService.validateLogin(loginRequest.getUsername(), loginRequest.getPassword()) )
			return jwtGenerator.generateToken(userService.getUser(loginRequest.getUsername()).getUserID());
		else
			return "Login Failed: Username or Password is Incorrect";
	}

}
