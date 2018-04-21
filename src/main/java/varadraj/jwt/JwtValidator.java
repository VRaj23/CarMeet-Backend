package varadraj.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	private String signingKey = "TestKey";
	
	public Long validateToken(String token) {
		
		Long userID = null;
		try {
			Claims body = Jwts.parser()
					.setSigningKey(this.signingKey)
					.parseClaimsJws(token)
					.getBody();
			
			userID = Long.parseLong((String) body.get("userID"));
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return userID;
	}

}
