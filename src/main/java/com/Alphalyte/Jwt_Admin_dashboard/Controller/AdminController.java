package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ChangePass;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ResetPass;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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


    @GetMapping("/usergroup")
    public List<UserGroupMaster> getallgroups(){
        return userGroupMasterRepo.findAll();
    }

    @PostMapping("/usergroup")
    public void addgroup(@RequestBody UserGroupMaster userGroupMaster){
        userGroupMaster.setCreatedat(LocalDateTime.now());
        userGroupMasterRepo.save(userGroupMaster);
    }

    @PutMapping(path = {"/usergroup"})
    public UserGroupMaster updateUser(@RequestBody UserGroupMaster userGroupMaster){
        userGroupMaster.setGid(userGroupMaster.getGid());
        userGroupMaster.setModifiedat(LocalDateTime.now());
        userGroupMasterRepo.save(userGroupMaster);
        return userGroupMaster;
    }



/*--------------------------------cHANGEpASSWORD----------------------------------------------*/

    @PutMapping("/changepass")
    public ResponseEntity<?> ChangePass(@RequestBody ChangePass changePass){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user dbuser = userRepo.getById(changePass.getUsercode());
        boolean match = bCryptPasswordEncoder.matches(changePass.getOldpass(), dbuser.getPassword());
        if (match){
            dbuser.setPassword(changePass.getNewpass());
            userRepo.save(dbuser);
            return ResponseEntity.ok("Password changed!");
        }
        return ResponseEntity.ok("Old Password not matched");
    }
/*---------------------------------rESETpASSWORD----------------------------------------------*/

    @PutMapping("/resetpass")
    public ResponseEntity<?> ResetPass(@RequestBody ResetPass resetPass){
         boolean exists = userRepo.existsById(resetPass.getUsercode());
         if (exists){
             user dbuser = userRepo.getById(resetPass.getUsercode());
             dbuser.setPassword(resetPass.getNewpass());
             userRepo.save(dbuser);
             return ResponseEntity.ok("PASSWORD RESET SUCCESSFULL!");
         }else return ResponseEntity.ok("Invalid user code.");
    }

}
