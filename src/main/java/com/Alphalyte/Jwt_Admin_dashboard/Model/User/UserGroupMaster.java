package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Group_Master")
public class UserGroupMaster {
    @Autowired
    user user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "group_name")
    @OneToOne(mappedBy = "user_groupname")
    private String groupname;

//    @JsonManagedReference
//    @OneToOne(mappedBy = "groupname", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)


    UserGroupMaster(String groupname){
        this.groupname = groupname;
    }
}
