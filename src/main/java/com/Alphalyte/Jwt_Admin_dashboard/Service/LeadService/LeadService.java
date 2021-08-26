package com.Alphalyte.Jwt_Admin_dashboard.Service.LeadService;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.FollowUp;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead.LeadRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead.FollowupRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserRepository;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.LeadForm;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.followUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeadService {

    @Autowired
    LeadRepo repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    FollowupRepo followUpRepo;


    public ResponseEntity<String> saveLead(LeadForm lead){

        user assignTo = userRepo.getById(lead.getUsercode());

        Lead dblead = new Lead();
        dblead.setName(lead.getName());
        dblead.setGender(lead.getGender());
        dblead.setMobile(lead.getMobile());
        dblead.setEmail(lead.getEmail());
        dblead.setDate(lead.getDate());
        dblead.setAssignTo(assignTo);
        dblead.setGuardianName(lead.getGuardianName());
        dblead.setGuardianMobile(lead.getGuardianMobile());
        dblead.setStatus(lead.getStatus());
        dblead.setLeadSource(lead.getLeadSource());
        dblead.setRemark(lead.getRemark());
        dblead.setAddress(lead.getAddress());
        dblead.setBoard(lead.getBoard());
        dblead.setCity(lead.getCity());
        dblead.setCollege(lead.getCollege());
        dblead.setCountry(lead.getCountry());
        dblead.setDegree(lead.getDegree());
        dblead.setState(lead.getState());
        dblead.setMedium(lead.getMedium());
//        System.out.println(dblead.getId());
        repo.save(dblead);
        return new ResponseEntity<>("Lead saved", HttpStatus.CREATED);
    }


    public ResponseEntity<List<Lead>> getAllLeads(){
        return new ResponseEntity<>(repo.findAll() , HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<String> deletebyid(String id) {
       if (repo.existsById(id)) {
           Lead lead = repo.getById(id);
           lead.setAssignTo(null);
           repo.deleteById(id);
           return new ResponseEntity<String>("Lead deleted", HttpStatus.OK);
       }
       else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> updateLead(String id , LeadForm lead){
        if (repo.existsById(id)) {
            Lead dblead = repo.getById(id);

            user assignTo = userRepo.getById(lead.getUsercode());


            dblead.setName(lead.getName());
            dblead.setGender(lead.getGender());
            dblead.setMobile(lead.getMobile());
            dblead.setEmail(lead.getEmail());
            dblead.setDate(lead.getDate());
            dblead.setAssignTo(assignTo);
            dblead.setGuardianName(lead.getGuardianName());
            dblead.setGuardianMobile(lead.getGuardianMobile());
            dblead.setStatus(lead.getStatus());
            dblead.setLeadSource(lead.getLeadSource());
            dblead.setRemark(lead.getRemark());
            dblead.setAddress(lead.getAddress());
            dblead.setBoard(lead.getBoard());
            dblead.setCity(lead.getCity());
            dblead.setCollege(lead.getCollege());
            dblead.setCountry(lead.getCountry());
            dblead.setDegree(lead.getDegree());
            dblead.setState(lead.getState());
            dblead.setMedium(lead.getMedium());

            repo.save(dblead);

            return new ResponseEntity<>("Lead updated", HttpStatus.OK);
        }
        else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<String> updateLeadStatus(String uuid, String status){
        if (repo.existsById(uuid)){
            Lead lead = repo.getById(uuid);
            lead.setStatus(status);
            return new ResponseEntity<>("LEAD STATUS UPDATED", HttpStatus.OK);
        }
        else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<String> updateAssignTo(int usercode, String uuid){
        if (repo.existsById(uuid)){
            Lead lead = repo.getById(uuid);
            user user = userRepo.getById(usercode);
            lead.setAssignTo(user);
            return new ResponseEntity<>("LEAD ASSIGNED TO: " + user.getUsername(), HttpStatus.OK);
        }
        else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<List<Lead>> getallLeadsFromAssignUsercode(int usercode)
    {
        if(userRepo.existsById(usercode))
        {
            return new ResponseEntity<>(repo.getAllLeadsAssignToUsercode(usercode)
            ,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<FollowUp>> getallFollowUps()
    {
        return new ResponseEntity<>(followUpRepo.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<List<FollowUp>> getallFollowUpFromAssignUsercode(int usercode)
    {
        if(userRepo.existsById(usercode))
        {
            return new ResponseEntity<>(followUpRepo.getAllFollowUpFromUsercode(usercode)
                    ,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> savefollowup(followUpRequest followUpRequest) {
        user assignTo=userRepo.getById(followUpRequest.getUsercode());

        FollowUp followUp=new FollowUp();
        followUp.setDate(followUpRequest.getDate());
        followUp.setAssignTo(assignTo);
        followUp.setConversation(followUpRequest.getConversation());
        followUp.setReason(followUpRequest.getReason());
        followUp.setNextCallDate(followUpRequest.getNextCallDate());
        followUp.setStatus(followUpRequest.getStatus());
        followUpRepo.save(followUp);


        return new ResponseEntity<>("FollowUp created",HttpStatus.CREATED);
    }
}//end class
