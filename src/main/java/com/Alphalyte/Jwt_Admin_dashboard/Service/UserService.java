package com.Alphalyte.Jwt_Admin_dashboard.Service;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserResquest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//@Service
public interface UserService {

    public List<user> getAllUsers();

    public ResponseEntity<?> UpdateUser(UserUpdateRequest userRequest, MultipartFile file) throws IOException;

    public ResponseEntity<?> deleteById(int usercode);

    public ResponseEntity<?> AddUser(UserResquest userResquest, MultipartFile file) throws IOException;

    public user getUserById(int usercode);

}
