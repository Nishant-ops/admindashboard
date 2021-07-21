package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

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

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class user implements UserDetails {

//    @Autowired
//    UserGroupMaster userGroupMaster;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int usercode;

    private String group_name;

    private String username;
    private String password;
    private LocalDateTime createdat;
    private String createdBY;
    private String localaddress;
//    private Boolean isactive = userGroupMaster.isActive();
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private long phoneNumber;

    public user(String groupname, String username, String password,
                LocalDateTime createdat, String createdBY,
                String localaddress/*, Boolean isactive*/, String email, long phoneNumber) {
        this.group_name = groupname;
        this.username = username;
        this.password = password;
        this.createdat = createdat;
        this.createdBY = createdBY;
        this.localaddress = localaddress;
//        this.isactive = isactive;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
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
        return true;
    }
    
    
}
