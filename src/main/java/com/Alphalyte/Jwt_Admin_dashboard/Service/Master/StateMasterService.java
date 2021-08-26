package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.StateMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.StateMasterRepo;
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
public class StateMasterService {

    @Autowired
    StateMasterRepo stateMasterRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<StateMaster> GetAllStates(){
        return stateMasterRepo.findAll();
    }

    public ResponseEntity<?> AddState(@RequestBody StateMaster stateMaster, @RequestParam String username){
        stateMaster.setCreatedAt(LocalDateTime.now());
        stateMaster.setCreatedBy(username);

        //saving log
        UserLogReport userLogReport = new UserLogReport();

        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("State Master");
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);

        stateMasterRepo.save(stateMaster);

        return ResponseEntity.ok("Added Standard: " + stateMaster.getStateName());
    }

    public ResponseEntity<?> DeleteState(@PathVariable int id, @RequestParam String username){
        boolean exist = stateMasterRepo.existsById(id);
        if (exist) {
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("State Master");
            userLogReport.setLogType("Delete");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            stateMasterRepo.deleteById(id);
            return ResponseEntity.ok("State deleted");
        }
        return ResponseEntity.ok("Invalid id");
    }

    public ResponseEntity<?> UpdateState(@RequestBody StateMaster stateMaster, @RequestParam String username){
        boolean exist = stateMasterRepo.existsById(stateMaster.getId());
        if (exist){
            StateMaster dbuser = stateMasterRepo.getById(stateMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(username);
            dbuser.setStateName(stateMaster.getStateName());
            dbuser.setStateCode(stateMaster.getStateCode());

            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("State Master");
            userLogReport.setLogType("Update");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            stateMasterRepo.save(dbuser);

            return ResponseEntity.ok("State Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
}


