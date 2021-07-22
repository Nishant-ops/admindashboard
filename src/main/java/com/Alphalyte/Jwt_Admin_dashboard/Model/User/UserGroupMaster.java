package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
public class UserGroupMaster {

    @Id
    @GeneratedValue
    private int gid;
    private String groupname;
    private boolean active;
    private LocalDateTime createdat;
    private String createdby;
    private LocalDateTime modifiedat;
    private String modifiedby;

    public UserGroupMaster(String groupname, boolean active, String createdby) {
        this.groupname = groupname;
        this.active = active;
        this.createdby = createdby;
        this.modifiedby = null;
    }


}
