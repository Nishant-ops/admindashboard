package com.Alphalyte.Jwt_Admin_dashboard.Model.Lead;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Leads")
public class Lead {

    @Autowired
    @Transient
    UserReposoritries userRepo;

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


}
