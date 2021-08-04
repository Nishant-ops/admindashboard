package com.Alphalyte.Jwt_Admin_dashboard.payload.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class UserMasterTable {
    private int usercode;
    private String username;
    private String groupname;
    private long phone;
    private String email;
    private String branch;
    private LocalDateTime DateOfJoining;
    private String language;
    private String localaddress;
    private String permanentaddress;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;


}
