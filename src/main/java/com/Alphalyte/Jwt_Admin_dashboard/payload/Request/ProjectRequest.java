package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    private String pname;
    private long commitment;
    private String status;
    private String description;
    private String remark;
    private LocalDate date;

}
