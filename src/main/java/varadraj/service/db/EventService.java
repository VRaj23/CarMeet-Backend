package varadraj.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.model.Event;
import varadraj.model.Users;
import varadraj.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepo;
	
	public void saveEvent(Event event) {
		eventRepo.save(event);
	}

	public List<Event> getAllUserEvents(Users user) {
		List<Event> events = new ArrayList<>();
		eventRepo.findByCreatedBy(user).forEach(events::add);
		return events;
	}
	
	private boolean validateOperation(long eventID, Long userID) {
		return eventRepo.findByEventID(eventID).getCreatedBy().getUserID() == userID.longValue();
	}

	public void deleteEvent(long eventID, Long userID) {
		if( validateOperation(eventID,userID) )
			eventRepo.deleteById(eventID);
		else
			throw new RuntimeException("User cannot delete this event");
		
	}

	public void updateEvent(Event newEvent, Long userID) {
		if( validateOperation(newEvent.getEventID(), userID) ) {
			Event event = eventRepo.findByEventID(newEvent.getEventID());
				if(newEvent.getEventDateTime() != null)
					event.setEventDateTime(newEvent.getEventDateTime());
				if(newEvent.getEventName() != null)
					event.setEventName(newEvent.getEventName());
				if(newEvent.getEventDetails() != null)
					event.setEventDetails(newEvent.getEventDetails());
				if(newEvent.getAddress() != null)
					event.setAddress(newEvent.getAddress());
				if(newEvent.getCity() != null)
					event.setCity(newEvent.getCity());
				if(newEvent.getState() != null)
					event.setState(newEvent.getState());
				if(newEvent.getCountry() != null)
					event.setCountry(newEvent.getCountry());
				if(newEvent.getPostalCode() > 0)
					event.setPostalCode(newEvent.getPostalCode());
			eventRepo.save(event);
		}
		else 
			throw new RuntimeException("User cannot update this event");
		
	}

}
