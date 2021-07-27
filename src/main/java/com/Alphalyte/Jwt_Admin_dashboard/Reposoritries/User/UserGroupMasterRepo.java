package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupMasterRepo extends JpaRepository<UserGroupMaster,Integer> {
    @Query(value = "SELECT g.gid FROM group_master g WHERE groupname=?1",nativeQuery = true)
    Integer call(/*@Param("groupname")*/ String groupname);

    @Query(value = "SELECT groupname FROM group_master",nativeQuery = true)
    List<String> groupnames();
}
