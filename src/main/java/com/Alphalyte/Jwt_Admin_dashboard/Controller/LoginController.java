package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.User_Ip;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.Ip_repository;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.IPUtil;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.loginRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.JwtResponse;
import com.Alphalyte.Jwt_Admin_dashboard.security.jwt.JwtUsernameandPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(value = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUsernameandPasswordAuthenticationFilter jwtUsernameandPasswordAuthenticationFilter;
    @Autowired
    Ip_repository ip_repository;

    List<String> list=new ArrayList<>();

   // private
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody loginRequest loginRequest, HttpServletRequest request)
    {
        final Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        list.add("ROLE_ADMIN");
        System.out.println(loginRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        user userDetails=(user) authentication.getPrincipal();
        String token=jwtUsernameandPasswordAuthenticationFilter.generateToken(userDetails.getUsername());

        String ip = IPUtil.getIpAddr(request);
        User_Ip user_ip = new User_Ip(loginRequest.getUsername(),ip);
        ip_repository.save(user_ip);

        return ResponseEntity.ok(new JwtResponse(
                token, userDetails.getUsercode(),userDetails.getUsername(), userDetails.getEmail(),
                list
        ));
    }
}

