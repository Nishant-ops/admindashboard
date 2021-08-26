package com.Alphalyte.Jwt_Admin_dashboard.Model.Enquiry;


import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {

    private int enquiryNum;
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
    private float committedAmount;
    private float prevClassPercent;
    private LocalDate nextCallDate;
    private String fbLink;
    private String tag;
    private String enquirySource;

    @OneToOne(cascade = CascadeType.MERGE)
    private user assignTo;
    private String careerObjective;
    private String remark;

    public int getEnquiryNum() {
        return enquiryNum;
    }

    public String getBatch() {
        return batch;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfEnquiry() {
        return dateOfEnquiry;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public long getGuardianPhone() {
        return guardianPhone;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getInstitute() {
        return institute;
    }

    public String getStandard() {
        return standard;
    }

    public String getBranch() {
        return branch;
    }

    public String getSemester() {
        return semester;
    }

    public String getBoard() {
        return board;
    }

    public String getMedium() {
        return medium;
    }

    public float getGrossAmount() {
        return grossAmount;
    }

    public float getCommittedAmount() {
        return committedAmount;
    }

    public float getPrevClassPercent() {
        return prevClassPercent;
    }

    public LocalDate getNextCallDate() {
        return nextCallDate;
    }

    public String getFbLink() {
        return fbLink;
    }

    public String getTag() {
        return tag;
    }

    public String getEnquirySource() {
        return enquirySource;
    }

    public int getAssignTo() {
        return assignTo.getUsercode();
    }

    public String getCareerObjective() {
        return careerObjective;
    }

    public String getRemark() {
        return remark;
    }
}
