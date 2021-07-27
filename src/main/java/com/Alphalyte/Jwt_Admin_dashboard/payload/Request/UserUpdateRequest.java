package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
    private int usercode;
    private String username;
    private String groupname;
    private String language;
    private LocalDateTime DateOfJoining;
    private String localaddress;
    private String permanentAddress;
    private String branch;
    private String modifiedby;
    private String email;
    private long phone;
}
