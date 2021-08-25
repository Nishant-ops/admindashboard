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

        repo.save(dblead);
        return new ResponseEntity<>("Lead saved", HttpStatus.CREATED);
    }




    public ResponseEntity<List<Lead>> getAllLeads(){
        return new ResponseEntity<>(repo.findAll() , HttpStatus.OK);
    }

}
