package com.Alphalyte.Jwt_Admin_dashboard.Model.Enquiry;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuickEnquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
