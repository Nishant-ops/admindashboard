package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.User_Ip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ip_repository extends JpaRepository<User_Ip, Integer> {

}
