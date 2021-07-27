package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResquest {
    private String username;
    private String password;
    private String groupname;
    private String language;
    private LocalDateTime DateOfJoining;
    private String localaddress;
    private String permanentaddress;
    private String branch;
    private String createdby;
    private String email;
    private long phone;
}
