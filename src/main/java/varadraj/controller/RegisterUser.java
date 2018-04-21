package varadraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.BaseResponse;
import varadraj.model.Users;
import varadraj.model.jwt.LoginRequest;
import varadraj.service.db.UserService;

@RestController
@RequestMapping("/registerUser")
public class RegisterUser {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@PostMapping
	public BaseResponse registerUser(@RequestBody final LoginRequest loginRequest	) {
		
		System.out.println("registerUser "+loginRequest.getUsername());
		
		Users user = new Users(loginRequest.getUsername().trim()
				, bCryptPasswordEncoder.encode(loginRequest.getPassword().trim()));
		
		long id = userService.saveUser(user);
		
		return new BaseResponse(201, new Long(id).toString() );
	}


}
