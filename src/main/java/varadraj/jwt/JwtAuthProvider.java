package varadraj.jwt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import varadraj.model.jwt.JwtAuthToken;
import varadraj.model.jwt.JwtCustomUserDetails;

@Component
public class JwtAuthProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	private JwtValidator jwtValidator;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		JwtAuthToken jwtAuthToken = (JwtAuthToken) authentication;
		
		String token = jwtAuthToken.getToken();
		
		Long userID = jwtValidator.validateToken(token);
		
		if (userID == null) {
			throw new RuntimeException("Invalid Token");
		}
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("user");
		
		return new JwtCustomUserDetails(token, userID, grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthToken.class.isAssignableFrom(authentication));
	}
	
	

}
