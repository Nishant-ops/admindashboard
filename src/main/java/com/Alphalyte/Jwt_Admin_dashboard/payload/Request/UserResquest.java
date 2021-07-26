package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResquest {
    private String username;
    private String password;
    private int groupcode;
    private LocalDate DateOfJoining;
    private String localaddress;
    private String permanentAddress;
    private String branch;
    private String createdBy;
    private String email;
    private long phone;
}
