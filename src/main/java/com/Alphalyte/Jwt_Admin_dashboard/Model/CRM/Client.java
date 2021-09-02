package com.Alphalyte.Jwt_Admin_dashboard.Model.CRM;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENT_TBL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "enquiry_sequence"),
                    @Parameter(name = "initial_value", value = "100768"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;
    @Column(name = "FNAME")
    private String firstName;
    @Column(name = "LNAME")
    private String lastName;

    private long phone1;
    private long phone2;
    private long landline;

    private String email1;
    private String email2;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Project> project;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Note> notes;
    private String status;
    private String address;
    private String country;
    private String state;
    private String city;
    private String remark;

    @Column(name = "REG_DATE")
    private LocalDate regDate;
    @Column(name = "DOB")
    private LocalDate dob;
    private String gender;

    @Lob
    private byte[] image;

    public Client(String firstName, String lastName, long phone1, long phone2, long landline,
                  String email1, String email2, String address, String country, String state,
                  String city, String remark, LocalDate regDate, LocalDate dob, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.landline = landline;
        this.email1 = email1;
        this.email2 = email2;
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.remark = remark;
        this.regDate = regDate;
        this.dob = dob;
        this.gender = gender;
    }


}
