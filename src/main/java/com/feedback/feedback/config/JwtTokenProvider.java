package com.feedback.feedback.config;

import com.feedback.feedback.model.User;
import com.feedback.feedback.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

//    @Value("${security.jwt.token.expire-length:960000000}")
//    private long validityInMilliseconds; // 26,666666667 h

    @Autowired
    private UserService userService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("username", user.getUsername());
        claims.put("role",user.getRole());

        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
        String token = Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
//                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
        UsernamePasswordAuthenticationToken auth = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);



        return token;
    }

    UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = userService.findById(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsername(String token){
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("username");
    }
}
