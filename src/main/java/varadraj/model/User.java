package varadraj.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userID;
	
	@Column(unique = true)
	private String username;
	private String passwordHash;
	private ZonedDateTime creationDateTime;
	
	public User() {
		
	}

	public User(String username, String passwordHash, ZonedDateTime creationDateTime) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.creationDateTime = creationDateTime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!User.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final User user = (User) obj;
		
		if(this.getUserID() != user.getUserID())
			return false;
		return true;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public ZonedDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(ZonedDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	
	

}
