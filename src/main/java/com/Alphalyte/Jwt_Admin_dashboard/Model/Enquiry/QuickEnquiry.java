package com.Alphalyte.Jwt_Admin_dashboard.Model.Enquiry;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuickEnquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long phone;
    private String gender;
    private String email;
    private String college;
    private String medium;
    private String guardianName;
    private long guardianPhone;

    private float fees;
    private String enquirySource;
    private String address;
    private String remark;


}
