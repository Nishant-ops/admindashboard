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
public class EnquiryForm {

    private String batch;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfEnquiry;
    private LocalDate dateOfBirth;
    private long phone;
    private String email;
    private String guardianName;
    private long guardianPhone;
    private String address;
    private String country;
    private String state;
    private String city;
    private String institute;
    private String standard;
    private String branch;
    private String semester;
    private String board;
    private String medium;

    private float grossAmount;
    private float comittedAmount;
    private float prevClassPercent;
    private LocalDate nextCallDate;
    private String fbLink;
    private String tag;
    private String enquirySource;

    private int usercode;
    private String careerObjective;
    private String remark;

}
