package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.ExamMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.StandardMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.ExamMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.StandardMasterRepo;
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
public class StandardMasterService {
    @Autowired
    StandardMasterRepo standardMasterRepo;

    @Autowired
    UserReposoritries userRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<StandardMaster> GetAllStandards(){
        return standardMasterRepo.findAll();
    }

    public ResponseEntity<?> AddStandard(@RequestBody StandardMaster standardMaster, @RequestParam String username){
        standardMaster.setCreatedAt(LocalDateTime.now());
        standardMaster.setCreatedBy(username);

        //saving log
        UserLogReport userLogReport = new UserLogReport();

        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("Standard Master");
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);

        standardMasterRepo.save(standardMaster);

        return ResponseEntity.ok("Added Standard: " + standardMaster.getStandardName());
    }

    public ResponseEntity<?> DeleteStandard(@PathVariable int id, @RequestParam String username){
        boolean exist = standardMasterRepo.existsById(id);
        if (exist) {
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Standard Master");
            userLogReport.setLogType("Delete");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            standardMasterRepo.deleteById(id);
            return ResponseEntity.ok("Standard deleted");
        }
        return ResponseEntity.ok("Invalid id");
    }

    public ResponseEntity<?> UpdateStandard(@RequestBody StandardMaster standardMaster, @RequestParam String username){
        boolean exist = standardMasterRepo.existsById(standardMaster.getId());
        if (exist){
            StandardMaster dbuser = standardMasterRepo.getById(standardMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(username);
            dbuser.setStandardName(standardMaster.getStandardName());
            dbuser.setStdNameRoman(standardMaster.getStdNameRoman());

            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Standard Master");
            userLogReport.setLogType("Update");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            standardMasterRepo.save(dbuser);

            return ResponseEntity.ok("Standard Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
}
