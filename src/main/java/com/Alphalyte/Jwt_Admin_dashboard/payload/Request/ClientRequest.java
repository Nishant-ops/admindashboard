package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    private String firstName;
    private String lastName;
    private long phone1;
    private long phone2;
    private long landline;
    private String email1;
    private String email2;
    private List<Long> projects;
    private String address;
    private String country;
    private String state;
    private String city;
    private String remark;
    private LocalDate regDate;
    private LocalDate dob;
    private String gender;

}
