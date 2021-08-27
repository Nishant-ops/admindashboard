package com.Alphalyte.Jwt_Admin_dashboard.Model.Lead;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Leads")
public class Lead {

    //TODO:   Add upload file & Multipart request.

    @Autowired
    @Transient
    UserRepository userRepo;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
    }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    private String name;
    private String gender;
    private long mobile;
    private String email;
    private LocalDate date;

    @OneToOne(cascade = CascadeType.MERGE)
    private user assignTo;


    private String guardianName;
    private long guardianMobile;
    private String status;
    private String leadSource;
    private String remark;
    private String board;
    private String medium;
    private String address;
    private String country;
    private String state;
    private String city;
    private String degree;
    private String college;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAssignTo() {
        return assignTo.getUsername();
    }

    public String getGuardianName() {
        return guardianName;
    }

    public long getGuardianMobile() {
        return guardianMobile;
    }

    public String getStatus() {
        return status;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public String getRemark() {
        return remark;
    }

    public String getBoard() {
        return board;
    }

    public String getMedium() {
        return medium;
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

    public String getDegree() {
        return degree;
    }

    public String getCollege() {
        return college;
    }
}
