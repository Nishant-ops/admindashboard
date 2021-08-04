package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.BoardMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.BoardMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Service
public class BoardMasterService {
    @Autowired
    BoardMasterRepo boardMasterRepo;
    @Autowired
    UserReposoritries userRepo;
    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<BoardMaster> GetAllBoards(){
        return boardMasterRepo.findAll();
    }

    public BoardMaster AddBoard(@RequestBody BoardMaster boardMaster,@RequestParam String username){
        boardMaster.setCreatedAt(LocalDateTime.now());
        boardMaster.setCreatedBy(username);
        UserLogReport userLogReport = new UserLogReport();

        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("Board Master");
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);
        boardMasterRepo.save(boardMaster);


        return boardMaster;
    }

    public void DeleteBoard(@PathVariable int id,@RequestParam String username){
        UserLogReport userLogReport = new UserLogReport();

        userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
        userLogReport.setUsername(username);
        userLogReport.setFormName("Board Master");
        userLogReport.setLogType("Delete");
        userLogReport.setLogDateTime(LocalDateTime.now());
        userLogReportRepo.save(userLogReport);
        boardMasterRepo.deleteById(id);
    }

    public ResponseEntity<?> UpdateBoard(@RequestBody BoardMaster boardMaster, @RequestParam String username){
        boolean exist = boardMasterRepo.existsById(boardMaster.getId());
        if (exist){
            BoardMaster dbuser = boardMasterRepo.getById(boardMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(boardMaster.getModifiedBy());
            dbuser.setBoardAbb(boardMaster.getBoardAbb());
            dbuser.setBoardName(boardMaster.getBoardName());

            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
            userLogReport.setUsername(username);
            userLogReport.setFormName("Board Master");
            userLogReport.setLogType("Insert");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            boardMasterRepo.save(dbuser);

            return ResponseEntity.ok("Board Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
}
