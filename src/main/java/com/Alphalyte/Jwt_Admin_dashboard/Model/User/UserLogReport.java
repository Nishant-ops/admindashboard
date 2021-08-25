package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserLogReport")
@Getter
@Setter
@NoArgsConstructor
public class UserLogReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false)
    private int usercode;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String FormName;
    @Column(nullable = false)
    private String LogType;
    @Column(nullable = false)
    private LocalDateTime LogDateTime;
}
