package com.Alphalyte.Jwt_Admin_dashboard.Service.LeadService;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead.LeadRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.LeadForm;
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
    UserReposoritries userRepo;

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



}//end class
