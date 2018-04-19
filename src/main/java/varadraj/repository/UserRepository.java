package varadraj.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.model.Users;

public interface UserRepository extends CrudRepository<Users, Long>{
	
	public Users findByUsername(String username);

}
