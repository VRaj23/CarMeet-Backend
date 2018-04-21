package varadraj.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import varadraj.model.Users;
import varadraj.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<Users> getAllUsers(){
		List<Users> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public long saveUser(Users user) {
		return userRepository.save(user).getUserID();
	}
	
	public Users getUser(String username) {
		return userRepository.findByUsername(username);
	}
	
	public Users getUser(long userID) {
		return userRepository.findByUserID(userID);
	}
	
	public boolean validateLogin(String username, String password) {
		Users user = userRepository.findByUsername(username);
			
		return bCryptPasswordEncoder.matches(password.trim(), user.getPasswordHash());
		
	}

}
