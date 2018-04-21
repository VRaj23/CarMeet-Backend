package varadraj.model.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthToken extends UsernamePasswordAuthenticationToken{
	
	private static final long serialVersionUID = 1L;
	
	private String token;

	public JwtAuthToken(String token) {
		super(null, null);
		this.token = token;		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}	

}
