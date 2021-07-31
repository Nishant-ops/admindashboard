package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.BoardMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.ClassGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.ClassGroupMasterRepo;
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
public class ClassGroupMasterService {
    @Autowired
    ClassGroupMasterRepo classGroupMasterRepo;

    @Autowired
    UserReposoritries userRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<ClassGroupMaster> GetAllClassGroups(){
        return classGroupMasterRepo.findAll();
    }

    public ResponseEntity<?> AddClassGroup(@RequestBody ClassGroupMaster classGroupMasterMaster, @RequestParam String username){
        classGroupMasterMaster.setCreatedAt(LocalDateTime.now());
        classGroupMasterMaster.setCreatedBy(username);

        //saving log
        UserLogReport userLogReport = new UserLogReport();

        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("Class Group Master");
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);

        classGroupMasterRepo.save(classGroupMasterMaster);

        return ResponseEntity.ok("Added class group: " + classGroupMasterMaster.getClassGroup());
    }

    public ResponseEntity<?> DeleteClassGroup(@PathVariable int id, @RequestParam String username){
        boolean exist = classGroupMasterRepo.existsById(id);
        if (exist) {
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Class Group Master");
            userLogReport.setLogType("Delete");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            classGroupMasterRepo.deleteById(id);
            return ResponseEntity.ok("Class Group deleted");
        }
        return ResponseEntity.ok("Invalid id");
    }

    public ResponseEntity<?> UpdateClassGroup(@RequestBody ClassGroupMaster classGroupMaster, @RequestParam String username){
        boolean exist = classGroupMasterRepo.existsById(classGroupMaster.getId());
        if (exist){
            ClassGroupMaster dbuser = classGroupMasterRepo.getById(classGroupMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setClassGroup(classGroupMaster.getClassGroup());
            dbuser.setModifiedBy(username);
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Class Group Master");
            userLogReport.setLogType("Update");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            classGroupMasterRepo.save(dbuser);

            return ResponseEntity.ok("Class Group Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
}
