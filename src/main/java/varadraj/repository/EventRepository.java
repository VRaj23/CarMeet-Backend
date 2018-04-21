package varadraj.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import varadraj.model.Event;
import varadraj.model.Users;

public interface EventRepository extends CrudRepository<Event, Long>{
	
	public List<Event> findByCreatedBy(Users user);
	public Event findByEventID(long eventID);

}
