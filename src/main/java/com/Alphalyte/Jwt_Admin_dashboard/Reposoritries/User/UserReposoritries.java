package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserReposoritries extends JpaRepository<user,Integer> {
    Optional<user> findByUsername(String username);

    Optional<user> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

//    public static class usermastergroup{
//        int group_id;
//        int usercode;
//    }
//    @Query(value = "SELECT u.group_id, u.usercode FROM usermastergroup u WHERE usercode=?1",nativeQuery = true)
//    usermastergroup getUserAndGroupMapping(int usercode);
}
