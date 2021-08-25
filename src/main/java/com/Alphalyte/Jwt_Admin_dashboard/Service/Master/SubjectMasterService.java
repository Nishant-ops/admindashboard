package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.StateMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.SubjectMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.StateMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.SubjectMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubjectMasterService {
    @Autowired
    SubjectMasterRepo subjectMasterRepo;

    @Autowired
    UserReposoritries userRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<SubjectMaster> GetAllSubjects(){
        return subjectMasterRepo.findAll();
    }

    public ResponseEntity<?> AddSubject(@RequestBody SubjectMaster subjectMaster, @RequestParam String username){
        subjectMaster.setCreatedAt(LocalDateTime.now());
        subjectMaster.setCreatedBy(username);

        //saving log
        UserLogReport userLogReport = new UserLogReport();

//        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("Subject Master");
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);

        subjectMasterRepo.save(subjectMaster);

        return ResponseEntity.ok("Added Subject: " + subjectMaster.getSubjectName());
    }

    public ResponseEntity<?> DeleteSubject(@PathVariable int id, @RequestParam String username){
        boolean exist = subjectMasterRepo.existsById(id);
        if (exist) {
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Subject Master");
            userLogReport.setLogType("Delete");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            subjectMasterRepo.deleteById(id);
            return ResponseEntity.ok("Subject deleted");
        }
        return ResponseEntity.ok("Invalid id");
    }

    public ResponseEntity<?> UpdateSubject(@RequestBody SubjectMaster subjectMaster, @RequestParam String username){
        boolean exist = subjectMasterRepo.existsById(subjectMaster.getId());
        if (exist){
            SubjectMaster dbuser = subjectMasterRepo.getById(subjectMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(username);
            dbuser.setSubjectName(subjectMaster.getSubjectName());
            dbuser.setSubjectAbb(subjectMaster.getSubjectAbb());

            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Subject Master");
            userLogReport.setLogType("Update");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            subjectMasterRepo.save(dbuser);

            return ResponseEntity.ok("Subject Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
}
