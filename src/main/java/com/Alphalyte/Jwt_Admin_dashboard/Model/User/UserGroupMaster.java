package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;




@NoArgsConstructor
@Entity
@Table(name = "group_master")
@ToString
public class UserGroupMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gid;
    private String groupname;
    private boolean active;
    private LocalDateTime createdat;
    private String createdby;
    private LocalDateTime modifiedat;
    private String modifiedby;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public LocalDateTime getModifiedat() {
        return modifiedat;
    }

    public void setModifiedat(LocalDateTime modifiedat) {
        this.modifiedat = modifiedat;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }
}
