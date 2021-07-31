package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Service.User.UserGroupMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ChangePass;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ResetPass;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.Service.User.UserMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserResquest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserUpdateRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.UserMasterTable;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    UserMasterService userDetails;

    @Autowired
    UserGroupMasterService userGroupMasterService;

    @Autowired
    UserReposoritries userRepo;

    @Autowired
    UserGroupMasterRepo userGroupMasterRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

/*--------------------------------------uSERmASTER------------------------------------------*/
//  Get all users

    @GetMapping(path = "/users")
    public List<UserMasterTable> users(){
       return userDetails.GetAllUsers();
    }

//  Get User Image

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

//  Delete User

    @DeleteMapping(path = "/users/{usercode}")
    public ResponseEntity<?> deleteUser(@PathVariable("usercode") int usercode,@RequestBody String username){
       return userDetails.deleteById(usercode,username);
    }

//  Add User
    @PostMapping(path = {"/users"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addUser(@RequestPart(value = "user",required = true) UserResquest userResquest, @RequestParam(value = "file",required = true) MultipartFile file) throws IOException{
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
//dkjfhh


/*--------------------------------uSERgROUPmASTER---------------------------------------------*/




    @GetMapping("/usergroup")
    @PreAuthorize("hasAuthority('DEVELOPER')")
    public List<UserGroupMaster> getallgroups(){
        return userGroupMasterService.GetAllGroups();
    }

    @GetMapping("/usergroup/{gid}")
    public ResponseEntity getById(@PathVariable int gid){
        return userGroupMasterService.GetById(gid);
    }

    @PostMapping("/usergroup")
    public ResponseEntity<?> addGroup(@RequestBody UserGroupMaster userGroupMaster){
        return userGroupMasterService.AddGroup(userGroupMaster);
    }

    @GetMapping("/usergroup/names")
    public List<String> getGroupNames(){
        return userGroupMasterService.GetGroupNames();
    }

    @PutMapping(path = {"/usergroup"})
    public ResponseEntity<?> updateUser(@RequestBody UserGroupMaster userGroupMaster){
        return userGroupMasterService.UpdateGroup(userGroupMaster);
    }


    @GetMapping("/groupcode/{groupname}")
    public ResponseEntity<?> getGroupCodeByName(@PathVariable("groupname") String groupName){
        return userGroupMasterService.GetGroupCodeByName(groupName);
    }

    @DeleteMapping("/usergroup/{groupname}")
    public ResponseEntity<?> deleteGroup(@PathVariable("groupname") String groupname, @RequestParam String username){
        return userGroupMasterService.DeleteGroupByName(groupname,username);
    }

    /*--------------------------------User Log Report--------------------------------------------*/
    @GetMapping("/logreport")
    public ResponseEntity getLogReport(){
        List<UserLogReport> logs = userLogReportRepo.findAll();
        return ResponseEntity.ok(logs);
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
