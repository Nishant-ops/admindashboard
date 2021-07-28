package com.Alphalyte.Jwt_Admin_dashboard.Service.User;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserGroupMasterService {

    @Autowired
    UserGroupMasterRepo userGroupMasterRepo;

    @Autowired
    UserReposoritries userRepo;

    @Autowired
    UserLogReportRepo userLogReportRepo;

    public List<UserGroupMaster> GetAllGroups(){
        return userGroupMasterRepo.findAll();
    }

    public ResponseEntity GetById(int id){
        if (userGroupMasterRepo.existsById(id)){
            ResponseEntity.ok(userGroupMasterRepo.getById(id));
        }
        return ResponseEntity.ok("Invalid group id");
    }

    public List<String> GetGroupNames(){
        return userGroupMasterRepo.groupnames();
    }

    public ResponseEntity GetGroupCodeByName(String groupName){
        Integer gid = userGroupMasterRepo.call(groupName);
        if (gid != null) {
            return ResponseEntity.ok(gid);
        }return ResponseEntity.ok("Invalid group name");
    }

    public ResponseEntity AddGroup(UserGroupMaster userGroupMaster){
        Integer gid = userGroupMasterRepo.call(userGroupMaster.getGroupname());
        if (gid == null) {
            userGroupMaster.setCreatedat(LocalDateTime.now());
            userGroupMasterRepo.save(userGroupMaster);

            //Saving Log------------------------------:)
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(userGroupMaster.getCreatedby()));
            userLogReport.setUsername(userGroupMaster.getCreatedby());
            userLogReport.setLogType("Created");
            userLogReport.setFormName("User Group Master");
            userLogReport.setLogDateTime(LocalDateTime.now());

            userLogReportRepo.save(userLogReport);

            return ResponseEntity.ok("group added");
        }
        return ResponseEntity.ok("Groupname already exists");
    }

    public ResponseEntity UpdateGroup(UserGroupMaster userGroupMaster){
        boolean exist = userGroupMasterRepo.existsById(userGroupMaster.getGid());
        if (exist){
            UserGroupMaster dbgroup = userGroupMasterRepo.getById(userGroupMaster.getGid());
            dbgroup.setModifiedat(LocalDateTime.now());
            dbgroup.setModifiedby(userGroupMaster.getModifiedby());
            dbgroup.setGroupname(userGroupMaster.getGroupname());
            dbgroup.setActive(userGroupMaster.isActive());
            userGroupMasterRepo.save(dbgroup);

            //Saving Log
            UserLogReport userLogReport = new UserLogReport();

            userLogReport.setUsercode(userRepo.getUsercodeFromName(userGroupMaster.getCreatedby()));
            userLogReport.setUsername(userGroupMaster.getModifiedby());
            userLogReport.setLogType("Updated");
            userLogReport.setFormName("User Group Master");
            userLogReport.setLogDateTime(LocalDateTime.now());
            userLogReportRepo.save(userLogReport);

            return ResponseEntity.ok(dbgroup);
        }
        return ResponseEntity.ok("invalid group code");
    }

    public ResponseEntity DeleteGroupByName(String groupname, String username){
        if (!groupname.equals("disable")) {
            if (userGroupMasterRepo.groupnames().contains(groupname)){
                int gid = userGroupMasterRepo.call("disable");
                int user_gid = userGroupMasterRepo.call(groupname);
                for (com.Alphalyte.Jwt_Admin_dashboard.Model.User.user user :userRepo.groupUsers(user_gid)){
                    user.setGroup_name(userGroupMasterRepo.getById(gid));
                }

                userGroupMasterRepo.deleteById(userGroupMasterRepo.call(groupname));

                //Saving Log Report
                UserLogReport userLogReport = new UserLogReport();

                userLogReport.setUsercode(userRepo.getUsercodeFromName(username));
                userLogReport.setUsername(username);
                userLogReport.setFormName("User Group Master");
                userLogReport.setLogType("Delete");
                userLogReport.setLogDateTime(LocalDateTime.now());
                userLogReportRepo.save(userLogReport);

                return ResponseEntity.ok("Group deleted successfully");
            }
            return ResponseEntity.ok("Group not found");
        }
        return ResponseEntity.ok("Cannot delete default group");
    }


}
