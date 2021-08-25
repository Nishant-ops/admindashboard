package com.Alphalyte.Jwt_Admin_dashboard.security.jwt;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtUsernameandPasswordAuthenticationFilter {
@Autowired
    UserReposoritries userReposoritries;
    @Value("${Alphalyte.app.jwtSecret}")
    private String key;
    public Claims getAllClaimsFromJwtToken(String token)
    {
        Claims claims;
        try{
            claims=Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();

        }
        catch (Exception e)
        {
            claims=null;
        }
        return claims;
    }

    public String generateToken(String username)
    {
        int id=userReposoritries.getUsercodeFromName(username);
        user user=userReposoritries.getById(id);

        //System.out.println(token);

        return Jwts.builder()
                .setSubject(username)
                .claim("authorities",user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(generateExperationDate())
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }

    public String getUsernameFromToken(String Token)
    {
        String Username;
        try{
            final Claims claims = this.getAllClaimsFromJwtToken(Token);
            Username=claims.getSubject();
    }
        catch (Exception e)
        {
            Username=null;
        }
        return Username;
    }

    public Date generateExperationDate()
    {
        return new Date(new Date().getTime()+2*1000000000);
    }

    public boolean ValidateToken(String Token, UserDetails userDetails)
    {
        String username=this.getUsernameFromToken(Token);
        return (
                username!=null&&userDetails.getUsername().equals(username)&&
                        !expiresToken(Token)
                );
    }

    public Boolean expiresToken(String Token)
    {
      Date expire=getExperitionDate(Token);
      return expire.before(new Date());
    }

    public Date getExperitionDate(String Token)
    {
       Date expire;
        try{
            final Claims claims=this.getAllClaimsFromJwtToken(Token);
            expire=claims.getExpiration();
        }
        catch (Exception e)
        {
            expire=null;
        }
        return expire;
    }

    public Date getIssuedDate(String Token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromJwtToken(Token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String getToken( HttpServletRequest request ) {

        String authHeader = getAuthHeaderFromHeader( request );
        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader( HttpServletRequest request ) {
        return request.getHeader("Authorization");
    }
}