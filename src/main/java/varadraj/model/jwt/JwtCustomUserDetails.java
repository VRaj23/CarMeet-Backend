package varadraj.model.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtCustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 2L;
	
	 private String token;
     private Long userID;
     private Collection<? extends GrantedAuthority> authorities;
     
     

	public JwtCustomUserDetails(String token, Long userID, 
			Collection<? extends GrantedAuthority> authorities) {
		
		this.token = token;
		this.userID = userID;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.userID.toString();
		//THIS WILL BE AVAILABLE IN REST CONTROLLER AFTER USER IS AUTHENTICATED
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	

}
