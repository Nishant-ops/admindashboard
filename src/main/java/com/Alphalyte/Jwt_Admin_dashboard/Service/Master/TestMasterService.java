package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.TestMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.TestMasterRepo;
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
public class TestMasterService {
    @Autowired
    TestMasterRepo testMasterRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<TestMaster> GetAllTests(){
        return testMasterRepo.findAll();
    }

    public ResponseEntity<?> AddTest(@RequestBody TestMaster testMaster, @RequestParam String username){
        testMaster.setCreatedAt(LocalDateTime.now());
        testMaster.setCreatedBy(username);

        //saving log
        UserLogReport userLogReport = new UserLogReport();

        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("Test Master");
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);

        testMasterRepo.save(testMaster);

        return ResponseEntity.ok("Added Test: " + testMaster.getMockTestName());
    }

    public ResponseEntity<?> DeleteTest(@PathVariable int id, @RequestParam String username){
        boolean exist = testMasterRepo.existsById(id);
        if (exist) {
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Test Master");
            userLogReport.setLogType("Delete");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            testMasterRepo.deleteById(id);
            return ResponseEntity.ok("Test deleted");
        }
        return ResponseEntity.ok("Invalid id");
    }

    public ResponseEntity<?> UpdateTest(@RequestBody TestMaster testMaster, @RequestParam String username){
        boolean exist = testMasterRepo.existsById(testMaster.getId());
        if (exist){
            TestMaster dbuser = testMasterRepo.getById(testMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(username);
            dbuser.setMockTestName(testMaster.getMockTestName());
            dbuser.setLastYearPaper(testMaster.getLastYearPaper());

            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Test Master");
            userLogReport.setLogType("Update");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            testMasterRepo.save(dbuser);

            return ResponseEntity.ok("Test Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
}
