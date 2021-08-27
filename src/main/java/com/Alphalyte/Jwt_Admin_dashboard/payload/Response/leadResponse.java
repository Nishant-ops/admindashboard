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
public class leadResponse {

    private String uid;
    private String name;
    private LocalDate createdDate;
    private LocalDate nextCallDate;
    private long contact;
    private String course;
    private String ref;
    private int totalFollowUp;
    private String email;
    private String college;
    private String degree;
    private LocalDate lastFollowUp;
    private String status;
    private String address;
    private String assignTo;
}
