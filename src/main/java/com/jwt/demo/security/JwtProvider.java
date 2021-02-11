package com.jwt.demo.security;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.constants.ConstantAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {
	
	@Value("${jwt.expiration}")
	public int expiration;
	
	@Value("${jwt.secret}")
	public String jwtSecret;
	
	public String generateToken(String username) {
		Date now = new Date();
//		Date expireDate = new Date(now.getTime() + expiration);
		Date expireDate = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
		return Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtSecret)
					.setSubject(username).setExpiration(expireDate).compact();
	}
	
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.error(ConstantAuthentication.INVALIDSIGNATURE);
		} catch (MalformedJwtException e) {
			log.error(ConstantAuthentication.INVALIDTOKEN);
		} catch (ExpiredJwtException ex) {
            log.error(ConstantAuthentication.TOKENEXPIRED);
        } catch (UnsupportedJwtException e) {
			log.error(ConstantAuthentication.UNSUPPORTEDTOKEN);
		} catch (IllegalArgumentException e) {
			log.error(ConstantAuthentication.STRINGCLAIMS);
		}
		return false;
	}
	
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
}
