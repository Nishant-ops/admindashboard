package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.BoardMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.BoardMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class BoardMasterService {
    @Autowired
    BoardMasterRepo boardMasterRepo;

    public List<BoardMaster> GetAllBoards(){
        return boardMasterRepo.findAll();
    }

    public BoardMaster AddBoard(@RequestBody BoardMaster boardMaster){
        boardMaster.setCreatedAt(LocalDateTime.now());
        boardMasterRepo.save(boardMaster);
        return boardMaster;
    }

    public void DeleteBoard(@PathVariable int id){

        boardMasterRepo.deleteById(id);
    }

    public ResponseEntity<?> UpdateBoard(@RequestBody BoardMaster boardMaster){
        boolean exist = boardMasterRepo.existsById(boardMaster.getId());
        if (exist){
            BoardMaster dbuser = boardMasterRepo.getById(boardMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(boardMaster.getModifiedBy());
            dbuser.setBoardAbb(boardMaster.getBoardAbb());
            dbuser.setBoardName(boardMaster.getBoardName());
            boardMasterRepo.save(dbuser);

            return ResponseEntity.ok("Board Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
}
