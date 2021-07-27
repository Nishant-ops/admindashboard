package com.Alphalyte.Jwt_Admin_dashboard.Service;


import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserResquest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Configuration
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserReposoritries userRepo;

    @Autowired
    private final UserGroupMasterRepo userGroupMasterRepo;


    @Bean
    public PasswordEncoder passencoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<user> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public ResponseEntity<?> UpdateUser(UserUpdateRequest userRequest, MultipartFile file) throws IOException{
        user user = userRepo.findById(userRequest.getUsercode()).get();

        if (userRequest.getUsername() != null){
            user.setUsername(userRequest.getUsername());
        }else return ResponseEntity.ok("Username cannot be null");

        Integer gid = userGroupMasterRepo.call(userRequest.getGroupname());
        if (gid == null){
            return ResponseEntity.ok("Invalid group name");
        }
        boolean exists = userGroupMasterRepo.existsById(gid);
        if (exists) {
            user.setGroup_name(userGroupMasterRepo.getById(gid));
        }else return ResponseEntity.ok("Invalid group code");


        if (userRequest.getEmail() != null){
            user.setEmail(userRequest.getEmail());
        }else return ResponseEntity.ok("Email cannot be null");


        if (userRequest.getPhone() > 6000000000L && userRequest.getPhone() < 9999999999L){
            user.setPhoneNumber(userRequest.getPhone());
        }else return ResponseEntity.ok("Invalid phone number");


        user.setLocaladdress(userRequest.getLocaladdress());


        if (userRequest.getPermanentAddress() != null) {
            user.setPermanentAddress(userRequest.getPermanentAddress());
        }else return ResponseEntity.ok("Permanent address cannot be null");


        if (userRequest.getBranch() != null){
        user.setBranch(userRequest.getBranch());
        }else return ResponseEntity.ok("Branch cannot be null");


        user.setLanguage(userRequest.getLanguage());
        user.setModifiedat(LocalDateTime.now());


        if (userRequest.getModifiedby() != null) {
            user.setModifiedBY(userRequest.getModifiedby());
        }else return ResponseEntity.ok("Modified-by cannot be null");


        if (userRequest.getDateOfJoining() != null) {
            user.setDateOfJoining(userRequest.getDateOfJoining());
        } //else{ return ResponseEntity.ok("Date of joining cannot be null");}


        //Image format check
        if (file != null){
            String type = file.getContentType();
            if ( (!file.isEmpty()) && ((type != null) && (type.equals("image/png") || type.equals("image/jpeg"))) ){
                byte[] image = file.getBytes();
                user.setImage(image);
            }else {
                System.out.println(file.getContentType());
                return ResponseEntity.ok("Add a valid format image");
            }
        }

        System.out.println(user);
        userRepo.save(user);
        return ResponseEntity.ok("User Updated");
    }



    @Override
    public ResponseEntity<?> deleteById(int usercode) {
        boolean exist = userRepo.existsById(usercode);
        if(!exist) {return ResponseEntity.ok("User with usercode " + usercode + " not found!");}
        else{
            userRepo.deleteById(usercode);
            return ResponseEntity.ok("User deleted");
        }
    }



    @Override
    public ResponseEntity<?> AddUser(UserResquest userRequest, MultipartFile file) throws IOException{
        user user = new user();
        if (userRequest.getUsername() != null){
            user.setUsername(userRequest.getUsername());
        }else return ResponseEntity.ok("Username cannot be null");


        Integer gid = userGroupMasterRepo.call(userRequest.getGroupname());
        if (gid == null){
            return ResponseEntity.ok("Invalid group name");
        }
        boolean exists = userGroupMasterRepo.existsById(gid);
        if (exists) {
            user.setGroup_name(userGroupMasterRepo.getById(gid));
        }else return ResponseEntity.ok("Invalid group code");

        if (userRequest.getPassword() != null){
            user.setPassword(userRequest.getPassword());
        }else return ResponseEntity.ok("Password cannot be null");

        if (userRequest.getEmail() != null){
            user.setEmail(userRequest.getEmail());
        }else return ResponseEntity.ok("Email cannot be null");


        if (userRequest.getPhone() > 6000000000L && userRequest.getPhone() < 9999999999L){
            user.setPhoneNumber(userRequest.getPhone());
        }else return ResponseEntity.ok("Invalid phone number");


        user.setLocaladdress(userRequest.getLocaladdress());


        if (userRequest.getPermanentaddress() != null) {
            user.setPermanentAddress(userRequest.getPermanentaddress());
        }else return ResponseEntity.ok("Permanent address cannot be null");


        if (userRequest.getBranch() != null){
            user.setBranch(userRequest.getBranch());
        }else return ResponseEntity.ok("Branch cannot be null");


        user.setLanguage(userRequest.getLanguage());
        user.setCreatedat(LocalDateTime.now());


        if (userRequest.getCreatedby() != null) {
            user.setCreatedBY(userRequest.getCreatedby());
        }else return ResponseEntity.ok("Created-by cannot be null");


        if (userRequest.getDateOfJoining() != null) {
            user.setDateOfJoining(userRequest.getDateOfJoining());
        } //else{ return ResponseEntity.ok("Date of joining cannot be null");}


        //Image format check
        if (file != null){
            String type = file.getContentType();
            if ( (!file.isEmpty()) && ((type != null) && (type.equals("image/png") || type.equals("image/jpeg"))) ){
                byte[] image = file.getBytes();
                user.setImage(image);
            }else {
                System.out.println(file.getContentType());
                return ResponseEntity.ok("Add a valid format image");
            }
        }

        userRepo.save(user);
        return ResponseEntity.ok("User added with usercode " + user.getUsercode());
    }

    @Override
    public user getUserById(int usercode) {
        boolean exist = userRepo.existsById(usercode);
        if(!exist) {throw new NullPointerException("User not found!");}
        user u = userRepo.getById(usercode);
        return u;
    }



/*    public void setImage(Integer id, MultipartFile file) {
        try {
            user dbuser = userRepo.findById(id).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            dbuser.setImage(byteObjects);
            userRepo.save(dbuser);
        } catch (IOException e){
            System.out.println("error " + e);;
        }
    }*/


    public byte[] renderImageFromDb(Integer id, HttpServletResponse response) throws IOException{
        user dbuser = userRepo.findById(id).get();
        if (dbuser.getImage().length != 0){
            byte[] byteArray = new byte[dbuser.getImage().length];

            int i = 0;

        for (Byte wrappedByte: dbuser.getImage()) {
            byteArray[i++] = wrappedByte;
        }
        return byteArray;
    }
        else return new byte[0];
    }

}
