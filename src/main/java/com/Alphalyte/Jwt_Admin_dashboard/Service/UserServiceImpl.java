package com.Alphalyte.Jwt_Admin_dashboard.Service;


import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserReposoritries userRepo;


    @Bean
    public PasswordEncoder passencoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<user> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    @Transactional
    public user UpdateUser(int usercode, user user) {
        user.setUsercode(usercode);
        user.setPassword(passencoder().encode(user.getPassword()));
        return userRepo.save(user);
    }


    @Override
    public void deleteById(int usercode) {
        boolean exist = userRepo.existsById(usercode);
        if(!exist) throw new IllegalStateException("User with usercode "+ usercode + " not found.");
        userRepo.deleteById(usercode);
    }

    @Override
    public user AddUser(user user) {
        user.setPassword(passencoder().encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    @Override
    public user getUserById(int usercode) {
        boolean exist = userRepo.existsById(usercode);
        if(!exist) {throw new NullPointerException("User not found!");}
        user u = userRepo.getById(usercode);
        return u;
    }



//    public void setImage(Integer id, MultipartFile file) {
//        try {
////            user dbuser = userRepo.findById(id).get();
//            Byte[] byteObjects = new Byte[file.getBytes().length];
//            int i = 0;
//            for (byte b : file.getBytes()) {
//                byteObjects[i++] = b;
//            }
////            dbuser.setImage(byteObjects);
////            userRepo.save(dbuser);
//        } catch (IOException e){
//            System.out.println("error " + e);;
//        }
//    }


public Byte[] renderImageFromDb(Integer id, HttpServletResponse response) throws IOException{
        user dbuser = userRepo.findById(id).get();

        byte[] byteArray = new byte[dbuser.getImage().length];

        int i = 0;

    for (Byte wrappedByte: dbuser.getImage()) {
        byteArray[i++] = wrappedByte;
    }
    response.setContentType("image/jpeg");
    InputStream is = new ByteArrayInputStream(byteArray);
    IOUtils.copy(is, response.getOutputStream());
    return new Byte[0];
}

}
