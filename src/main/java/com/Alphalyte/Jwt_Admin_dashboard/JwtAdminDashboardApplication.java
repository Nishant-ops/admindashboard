package com.Alphalyte.Jwt_Admin_dashboard;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JwtAdminDashboardApplication/* implements CommandLineRunner */{
	@Autowired
	UserGroupMasterRepo userGroupMasterRepo;
	@Autowired
	UserReposoritries userRepository;
	public static void main(String[] args) {

		SpringApplication.run(JwtAdminDashboardApplication.class, args);}



}


