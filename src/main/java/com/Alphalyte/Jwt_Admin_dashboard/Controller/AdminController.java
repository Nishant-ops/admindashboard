package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ChangePass;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ResetPass;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

    private String uploadFolder = "C:\\Users\\hp\\Desktop\\images";

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

        u.setCreatedat(LocalDateTime.now());
        userDetails.AddUser(u);
        return u;
    }
    /*--------------------------------------------------TEST-----------------------------------------------*/
//    @PostMapping(path = {"/users"})
    public @ResponseBody ResponseEntity<?> createUser(@RequestParam("groupname") UserGroupMaster groupname, @RequestParam("username") String username,@RequestParam("password") String password, @RequestParam("phoneNumber") long phoneNumber,
                                                      @RequestParam("dateofjoining") LocalDate dateofjoining,@RequestParam("localAddress") String localaddress,
                                                      @RequestParam("permanentAddress") String permanentaddress,@RequestParam("branch") String branch,
                                                      @RequestParam("createdBy") String createdby, HttpServletRequest request,final @RequestParam("image") MultipartFile file) {
        try {
            //String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            System.out.println("uploadDirectory:: " + uploadDirectory);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            System.out.println("FileName: " + file.getOriginalFilename());
            if (fileName == null || fileName.contains("..")) {
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    System.out.println("Folder Created");
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                System.out.println("in catch");
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            user user = new user();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setUsername(username);
            user.setGroup_name(groupname);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setCreatedat(LocalDateTime.now());
            user.setCreatedBY(createdby);
            user.setDateOfJoining(dateofjoining);
            user.setLocaladdress(localaddress);
            user.setPermanentAddress(permanentaddress);
            user.setBranch(branch);
            user.setPhoneNumber(phoneNumber);
            System.out.println("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    /*--------------------------------------------------TEST-----------------------------------------------*/
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
    public ResponseEntity<UserGroupMaster> updateUser(@RequestBody UserGroupMaster userGroupMaster){
//        userGroupMaster.setGid(userGroupMaster.getGid());
//        userGroupMaster.setUsers();
//        userGroupMaster.setModifiedat(LocalDateTime.now());
//        userGroupMasterRepo.save(userGroupMaster);
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
