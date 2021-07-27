package com.Alphalyte.Jwt_Admin_dashboard.payload.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String username;
    private String password;
    private int groupcode;
    private String language;
    private LocalDate DateOfJoining;
    private String localaddress;
    private String permanentAddress;
    private String branch;
    private String createdBy;
    private String email;
    private long phone;
}
