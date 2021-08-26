package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.ExamMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.ExamMasterRepo;
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
public class ExamMasterService {
    @Autowired
    ExamMasterRepo examMasterRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<ExamMaster> GetAllExams(){
        return examMasterRepo.findAll();
    }

    public ResponseEntity<?> AddExam(@RequestBody ExamMaster examMaster, @RequestParam String username){
        examMaster.setCreatedAt(LocalDateTime.now());
        examMaster.setCreatedBy(username);

        //saving log
        UserLogReport userLogReport = new UserLogReport();

        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("Exam Master");
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);

        examMasterRepo.save(examMaster);

        return ResponseEntity.ok("Added exam: " + examMaster.getExamName());
    }

    public ResponseEntity<?> DeleteExam(@PathVariable int id, @RequestParam String username){
        boolean exist = examMasterRepo.existsById(id);
        if (exist) {
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Exam Master");
            userLogReport.setLogType("Delete");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            examMasterRepo.deleteById(id);
            return ResponseEntity.ok("Exam deleted");
        }
        return ResponseEntity.ok("Invalid id");
    }

    public ResponseEntity<?> UpdateExam(@RequestBody ExamMaster examMaster, @RequestParam String username){
        boolean exist = examMasterRepo.existsById(examMaster.getId());
        if (exist){
            ExamMaster dbuser = examMasterRepo.getById(examMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(username);
            dbuser.setExamName(examMaster.getExamName());
            dbuser.setExamAbb(examMaster.getExamAbb());

            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Exam Master");
            userLogReport.setLogType("Update");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            examMasterRepo.save(dbuser);

            return ResponseEntity.ok("Exam Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
}
