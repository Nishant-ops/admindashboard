package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.CourseMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.CourseMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseMasterService {

    @Autowired
    CourseMasterRepo courseMasterRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<CourseMaster> GetAllCourses(){
        return courseMasterRepo.findAll();
    }

    public ResponseEntity<?> AddCourse(@RequestBody CourseMaster courseMaster, @RequestParam String username){
        courseMaster.setCreatedAt(LocalDateTime.now());
        courseMaster.setCreatedBy(username);

        //saving log
        UserLogReport userLogReport = new UserLogReport();

        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("Course Master");
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);

        courseMasterRepo.save(courseMaster);

        return ResponseEntity.ok("Added course: " + courseMaster.getCourseName());
    }

    public ResponseEntity<?> DeleteCourse(@PathVariable int id, @RequestParam String username){
        boolean exist = courseMasterRepo.existsById(id);
        if (exist) {
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Course Master");
            userLogReport.setLogType("Delete");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            courseMasterRepo.deleteById(id);
            return ResponseEntity.ok("Course deleted");
        }
        return ResponseEntity.ok("Invalid id");
    }

    public ResponseEntity<?> UpdateCourse(@RequestBody CourseMaster courseMaster, @RequestParam String username){
        boolean exist = courseMasterRepo.existsById(courseMaster.getId());
        if (exist){
            CourseMaster dbuser = courseMasterRepo.getById(courseMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(username);
            dbuser.setCourseName(courseMaster.getCourseName());
            dbuser.setCourseAbb(courseMaster.getCourseAbb());

            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Course Master");
            userLogReport.setLogType("Update");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

             courseMasterRepo.save(dbuser);

            return ResponseEntity.ok("Course Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }


}
