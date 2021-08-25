package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResquest {
    private String username;
    private String password;
    private String groupname;
    private String language;
    private LocalDate DateOfJoining;
    private String localaddress;
    private String permanentaddress;
    private String branch;
    private String createdby;
    private String email;
    private long phone;
}
