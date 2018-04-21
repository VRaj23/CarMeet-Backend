package varadraj.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtGenerator {
	
	private long expireInMinutes = 30L;
	private String signingKey = "TestKey";
	
	public String generateToken(long userID) {
		Date expiration = new Date(System.currentTimeMillis() + 1000L*60L*this.expireInMinutes);
		Claims claim = Jwts.claims()
				.setExpiration(expiration);
		claim.put("userID", new Long(userID).toString());
		
		return Jwts.builder()
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS512, this.signingKey)
                .compact();
	}

}
