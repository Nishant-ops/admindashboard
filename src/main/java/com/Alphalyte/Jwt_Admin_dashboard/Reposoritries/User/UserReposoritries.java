package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserReposoritries extends JpaRepository<user,Integer> {
    Optional<user> findByUsername(String username);

    Optional<user> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT u.usercode FROM user_master u WHERE username=?",nativeQuery = true)
    Integer getUsercodeFromName(String username);

    @Query(value = "SELECT * FROM user_master WHERE group_name_gid=?1",nativeQuery = true)
    List<user> groupUsers(int gid);
}
