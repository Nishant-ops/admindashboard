package com.Alphalyte.Jwt_Admin_dashboard.payload.Response;

import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Note;
import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Project;
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
public class ClientFullView {

    private String firstName;
    private String lastName;
    private long phone1;
    private long phone2;
    private long landline;
    private String email1;
    private String email2;
    private String address;
    private String country;
    private String state;
    private String city;
    private String remark;
    private LocalDate regDate;
    private LocalDate dob;
    private String gender;
    private String status;

    private List<Project> projects;
    private List<Note> notes;

}
