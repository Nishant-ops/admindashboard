package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.security.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    UserServiceImpl userDetails;

    @Autowired
    UserReposoritries userRepo;

    @Autowired
    UserGroupMasterRepo userGroupMasterRepo;
/*--------------------------------------uSERmASTER------------------------------------------*/
    //Get all users
    @GetMapping(path = "/users")
    public List<user> users(){
        return userDetails.getAllUsers();
    }

    //Delete user by userID
    @DeleteMapping(path = "/users/{usercode}")
    public void deleteUser(@PathVariable("usercode") int usercode){
        userDetails.deleteById(usercode);
    }

    //ADD a new user
    @PostMapping(path = {"/users"})
    public user addUser(@RequestBody user u){
        userDetails.AddUser(u);
        return u;
    }

    //Get user by usercode
    @GetMapping(path = "/users/{usercode}")
    public user getUserById(@PathVariable("usercode") int usercode){
        return userDetails.getUserById(usercode);
    }

//  Update an existing user
    @PutMapping(path = {"/users"})
    public user updateUser(@RequestBody user u){
        userDetails.UpdateUser(u.getUsercode(),u);
        return u;
    }

/*--------------------------------uSERgROUPmASTER---------------------------------------------*/
    @PostMapping("/usergroup")
    public void addgroup(@RequestBody UserGroupMaster userGroupMaster){
        userGroupMasterRepo.save(userGroupMaster);
    }


}
