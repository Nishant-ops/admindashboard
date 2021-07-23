package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.Service.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "user_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class user implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usercode;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "usermastergroup", joinColumns = @JoinColumn(name = "usercode")
    ,inverseJoinColumns = @JoinColumn(name = "group_id"))
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
    private Byte[] image;

    public user(UserGroupMaster group_name, String username, String password, String email, long phoneNumber) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.group_name = group_name;
        this.username = username;
        this.password = bCryptPasswordEncoder.encode(password);
        this.email = email;
        this.phoneNumber = phoneNumber;
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
