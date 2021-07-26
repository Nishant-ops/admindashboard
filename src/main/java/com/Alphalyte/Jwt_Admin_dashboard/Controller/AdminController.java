package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ChangePass;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ResetPass;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.Service.UserServiceImpl;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserResquest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/usertest/{usercode}")
    public Byte[] renderImageFromDb(@PathVariable("usercode") int usercode, HttpServletResponse response) throws IOException {
        return userDetails.renderImageFromDb(usercode,response);
    }

    //Delete user by userID
    @DeleteMapping(path = "/users/{usercode}")
    public void deleteUser(@PathVariable("usercode") int usercode){
//        userDetails.DeleteRelation(usercode);
        userDetails.deleteById(usercode);
    }

    //ADD a new user
    @PostMapping(path = {"/users"})
    public ResponseEntity<?> addUser(@RequestPart("user") UserResquest userResquest, @RequestPart("file") MultipartFile file) throws IOException{
      user user = new user();
      user.setUsername(userResquest.getUsername());
      user.setPassword(userResquest.getPassword());
      user.setGroup_name(userGroupMasterRepo.getById(userResquest.getGroupcode()));
      user.setEmail(userResquest.getEmail());
      user.setPhoneNumber(userResquest.getPhone());
      user.setLocaladdress(userResquest.getLocaladdress());
      user.setPermanentAddress(userResquest.getPermanentAddress());
      user.setBranch(userResquest.getBranch());
      user.setCreatedBY(userResquest.getCreatedBy());
      user.setCreatedat(LocalDateTime.now());
      user.setDateOfJoining(userResquest.getDateOfJoining());
      byte[] image = file.getBytes();
      user.setImage(image);
      userRepo.save(user);
      return ResponseEntity.ok("User added");
    }

    /*--------------------------------------------------TEST-----------------------------------------------*/


    /*@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam(required=true, value="file") MultipartFile file,
                                             @RequestParam(required=true, value="username")String username,
                                             @RequestParam(required = false, value = "password")String password) throws IOException  {

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] image = file.getBytes();
        File convertFile = new File("C://Users//hp//Desktop//images//"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(image);
        fout.close();


        return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);

    }*/


    /*@PostMapping(path = {"/test"})
    public @ResponseBody ResponseEntity<?> createUser(@RequestBody UserResquest u,@RequestParam("image") MultipartFile image, HttpServletRequest request),@RequestBody user u,@RequestParam("image") MultipartFile file, HttpServletRequest request)@RequestParam("groupname") UserGroupMaster groupname, @RequestParam("username") String username,
                                                      @RequestParam("password") String password,
                                                      @RequestParam("phoneNumber") long phoneNumber,@RequestParam("dateofjoining") LocalDate dateofjoining
                                                      @RequestParam("localAddress") String localaddress,
                                                      @RequestParam("permanentAddress") String permanentaddress,@RequestParam("branch") String branch,
                                                      @RequestParam("createdBy") String createdby, HttpServletRequest request,
                                                      final @RequestParam("image") MultipartFile file) {
        try {
            MultipartFile file = image;
            if (fileName == null || fileName.contains("..")) {
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
           catch (Exception e) {
                System.out.println("in catch");
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            System.out.println("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Product Saved With File - " , HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/
    /*--------------------------------------------------TEST-----------------------------------------------*/


//  Get user by usercode
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

    @GetMapping("/usergroup/{gid}")
    public UserGroupMaster getById(@PathVariable int gid){
        return userGroupMasterRepo.findById(gid).get();
    }

    @PostMapping("/usergroup")
    public void addgroup(@RequestBody UserGroupMaster userGroupMaster){
        userGroupMaster.setCreatedat(LocalDateTime.now());
        userGroupMasterRepo.save(userGroupMaster);
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
