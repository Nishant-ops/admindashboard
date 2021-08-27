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
public class follow_up_Response {

    private String uid;
    private LocalDate date;
    private String name;
    private String course;
    private LocalDate next_Call_Date;
    private int total_follow_up;
    private long contact;
    private String byUser;
}
