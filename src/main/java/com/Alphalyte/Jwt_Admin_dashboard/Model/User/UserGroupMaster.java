package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;




@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "group_master")
public class UserGroupMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gid;
    private String groupname;
    private boolean active;
    private LocalDateTime createdat;
    private String createdby;
    private LocalDateTime modifiedat;
    private String modifiedby;

    public UserGroupMaster( String groupname,
                           boolean active, LocalDateTime createdat, String createdby, LocalDateTime modifiedat, String modifiedby) {
        this.groupname = groupname;
        this.active = active;
        this.createdat = createdat;
        this.createdby = createdby;
        this.modifiedat = modifiedat;
        this.modifiedby = modifiedby;
    }
}
