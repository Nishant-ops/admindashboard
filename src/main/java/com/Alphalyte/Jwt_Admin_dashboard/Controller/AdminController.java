package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ChangePass;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ResetPass;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.Service.UserServiceImpl;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserResquest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserUpdateRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.UserMasterTable;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public List<UserMasterTable> users(){
        List<UserMasterTable> users = new ArrayList<>();
        for (user u : userDetails.getAllUsers()) {
            UserMasterTable user = new UserMasterTable();
            user.setUsercode(u.getUsercode());
            user.setUsername(u.getUsername());
            user.setGroupname(u.getGroup_name());
            user.setPhone(u.getPhoneNumber());
            user.setEmail(u.getEmail());
            user.setBranch(u.getBranch());
            user.setCreatedAt(u.getCreatedat());
            user.setCreatedBy(u.getCreatedBY());
            user.setModifiedAt(u.getModifiedat());
            user.setModifiedBy(u.getModifiedBY());
            users.add(user);
        };
        return users;
    }

    @GetMapping("/users/{usercode}/photo")
    public ResponseEntity<?> renderImageFromDb(@PathVariable("usercode") int usercode, HttpServletResponse response) throws IOException {
        boolean exists = userRepo.existsById(usercode);
        if (exists){
        byte[] image = userDetails.renderImageFromDb(usercode,response);
        if (image.length != 0){
            response.setContentType("image/png");
            InputStream is = new ByteArrayInputStream(image);
            IOUtils.copy(is, response.getOutputStream());
            return ResponseEntity.ok("image");
        }}
        return ResponseEntity.ok("Usercode not found");
    }

    /*-------------------------Delete user by userID-----------------------------*/

    @DeleteMapping(path = "/users/{usercode}")
    public ResponseEntity<?> deleteUser(@PathVariable("usercode") int usercode){
        userDetails.deleteById(usercode);
        return ResponseEntity.ok().build();
    }

    /*----------------------------ADD A NEW USER---------------------------------*/
    @PostMapping(path = {"/users"})
    public ResponseEntity<?> addUser(@RequestPart("user") UserResquest userResquest, @RequestPart("file") MultipartFile file) throws IOException{
      return userDetails.AddUser(userResquest,file);
    }


//  Get user by usercode
    @GetMapping(path = "/users/{usercode}")
    public user getUserById(@PathVariable("usercode") int usercode){
        return userDetails.getUserById(usercode);
    }


//  Update an existing user
    @PutMapping(path = {"/users"})
    public ResponseEntity<?> updateUser(@RequestPart("user") UserUpdateRequest userRequest, @RequestPart(value = "file",required = false) MultipartFile file) throws IOException{
        boolean exists = userRepo.existsById(userRequest.getUsercode());
        if (exists){
            return userDetails.UpdateUser(userRequest,file);
        }
        return ResponseEntity.ok("User with usercode " + userRequest.getUsercode() + " doesn't exist");
    }









/*--------------------------------uSERgROUPmASTER---------------------------------------------*/


    @GetMapping("/usergroup")
    public List<UserGroupMaster> getallgroups(){
        return userGroupMasterRepo.findAll();
    }

    @GetMapping("/usergroup/{gid}")
    public UserGroupMaster getById(@PathVariable int gid){
        return userGroupMasterRepo.findById(gid).get();
    }

    @PostMapping("/usergroup")
    public void addgroup(@RequestBody UserGroupMaster userGroupMaster){
        userGroupMaster.setCreatedat(LocalDateTime.now());
        userGroupMasterRepo.save(userGroupMaster);
    }

    @GetMapping("/usergroup/names")
    public List<String> getGroupNames(){
        return userGroupMasterRepo.groupnames();
    }

    @PutMapping(path = {"/usergroup"})
    public ResponseEntity<UserGroupMaster> updateUser(@RequestBody UserGroupMaster userGroupMaster){
        boolean exist = userGroupMasterRepo.existsById(userGroupMaster.getGid());
        if (exist){
            UserGroupMaster dbuser = userGroupMasterRepo.getById(userGroupMaster.getGid());
            dbuser.setModifiedat(LocalDateTime.now());
            dbuser.setGroupname(userGroupMaster.getGroupname());
            dbuser.setActive(userGroupMaster.isActive());
            userGroupMasterRepo.save(dbuser);
            return ResponseEntity.ok(dbuser);
        }
        return ResponseEntity.ok().build();
    }
/*--------------------------------getGroupCodeByName-----------------------------------------*/

    @GetMapping("/groupcode/{groupname}")
    public ResponseEntity<?> getGroupCodeByName(@PathVariable("groupname") String groupname){
        Integer gid = userGroupMasterRepo.call(groupname);
        if (gid != null) {
            return ResponseEntity.ok(gid);
        }return ResponseEntity.ok("Invalid group name");
    }


/*--------------------------------cHANGEpASSWORD----------------------------------------------*/

    @PutMapping("/changepass")
    public ResponseEntity<?> ChangePass(@RequestBody ChangePass changePass){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user dbuser = userRepo.getById(changePass.getUsercode());
        boolean match = bCryptPasswordEncoder.matches(changePass.getOldpass(), dbuser.getPassword());
        if (match){
            dbuser.setPassword(bCryptPasswordEncoder.encode(changePass.getNewpass()));
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
