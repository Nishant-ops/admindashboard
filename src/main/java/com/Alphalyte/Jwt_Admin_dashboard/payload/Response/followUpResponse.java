package com.Alphalyte.Jwt_Admin_dashboard.payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class followUpResponse {

    private String uid;
    private String name;
    private LocalDate date;
    private LocalDate nextCallDate;
    private String course;
    private String totalFollowUp;
    private String user;
    private long contact;
}
