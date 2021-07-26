package com.Alphalyte.Jwt_Admin_dashboard.Model.User;


import com.Alphalyte.Jwt_Admin_dashboard.Service.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;


@Entity
@NoArgsConstructor
@Table(name = "user_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@ToString
public class user implements UserDetails {
    @Autowired
    @Transient
    UserServiceImpl userService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usercode;


    @OneToOne(cascade = CascadeType.MERGE)
    private UserGroupMaster group_name;


    private String username;
    private String password;

    private LocalDate DateOfJoining;
    private String localaddress;
    private String permanentAddress;
    private String branch;

    private LocalDateTime createdat;
    private String createdBY;

    @Column(unique = true,nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private long phoneNumber;
    @Lob
    private byte[] image;

    public Byte[] getImage(int usercode, HttpServletResponse response) throws IOException {
        Byte[] image = userService.renderImageFromDb(usercode,response);
        return image;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    public UserGroupMaster getGroup_name() {
        return group_name;
    }

    public void setGroup_name(UserGroupMaster group_name) {
        this.group_name = group_name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return group_name.isActive();
    }
    
    
}
