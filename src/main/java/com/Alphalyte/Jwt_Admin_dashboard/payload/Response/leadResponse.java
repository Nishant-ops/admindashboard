package com.Alphalyte.Jwt_Admin_dashboard.payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.SequenceGenerators;
import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class leadResponse {


    private String uid;
    private String name;
    private LocalDate created_date;
    private LocalDate next_Call_Date;
    private long contact;
    private String course;
    private String ref;
    private int total_Follow_Up;
    private String email;
    private String college;
    private String degree;
    private String AssignTo;
    private String last_Follow_Up;
    private String Address;
    private String status;
}
