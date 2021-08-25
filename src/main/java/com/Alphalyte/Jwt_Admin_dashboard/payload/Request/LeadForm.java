package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LeadForm {

    private String name;
    private String gender;
    private long mobile;
    private String email;
    private LocalDate date;
    private int usercode;
    private String guardianName;
    private long guardianMobile;
    private String status;
    private String leadSource;
    private String remark;

}
