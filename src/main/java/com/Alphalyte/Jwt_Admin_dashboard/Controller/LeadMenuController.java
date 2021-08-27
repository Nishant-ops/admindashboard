package com.Alphalyte.Jwt_Admin_dashboard.Controller;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.FollowUp;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import com.Alphalyte.Jwt_Admin_dashboard.Service.LeadService.LeadService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.LeadForm;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.FollowUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LeadMenuController {

    @Autowired
    LeadService service;


    @PostMapping(value = "/lead")
    public ResponseEntity<String> saveLead(@RequestBody LeadForm lead){
        return service.saveLead(lead);
    }

    @GetMapping(value = "/lead")
    public ResponseEntity<List<Lead>> getAllLeads(){
        return service.getAllLeads();
    }

    @DeleteMapping(value="/lead/{id}")
    public ResponseEntity<String> deletebyusercode(@PathVariable("id") String id)
    {
        return service.deletebyid(id);
    }

    @PutMapping(value = "/lead/{id}")
    public ResponseEntity<String> updateLead(@PathVariable("id") String id, @RequestBody LeadForm lead){
        return service.updateLead(id,lead);
    }

    @PutMapping("/lead/status/{id}")
    public ResponseEntity<String> updateLeadStatus(@PathVariable("id") String id, @RequestBody String status){
        return service.updateLeadStatus(id, status);
    }

    @PutMapping("/lead/assign/{id}")
    public ResponseEntity<String> updateLeadAssign(@PathVariable("id") String id, @RequestBody int usercode){
        return service.updateAssignTo(usercode, id);
    }

    @GetMapping("/lead/assign/{usercode}")
    public ResponseEntity<List<Lead>> getAllLeadsAssignToUsercode(@PathVariable("usercode") int id)
    {
        return service.getallLeadsFromAssignUsercode(id);
    }




    @GetMapping("/lead/followUp")
    public ResponseEntity<List<FollowUp>> getallFollowUps()
    {
        return service.getAllFollowUps();
    }


    @GetMapping("/lead/followUp/{usercode}")
    public ResponseEntity<List<FollowUp>> getAllFollowupsFromUsercode(@PathVariable("usercode") int usercode)
    {
        return service.getAllFollowUpFromAssignUsercode(usercode);
    }

    @GetMapping("/lead/followUpById/{id}")
    public ResponseEntity<?> getFollowUpById(@PathVariable("id") String uid)
    {
        return service.getFollowUpFromId(uid);
    }

    @GetMapping("/temp/{id}")
    public ResponseEntity<?> getFollowUpByLeadId(@PathVariable("id") String uid)
    {
        return service.getCountFollowUpFromLeadId(uid);
    }


    @PostMapping("/lead/followUp")
    public ResponseEntity<String> saveFollowUp(@RequestBody FollowUpRequest followUpRequest)
    {
        return service.addFollowUp(followUpRequest);
    }


    @DeleteMapping("/lead/followUp/{id}")
    public ResponseEntity<String> deleteFollowUp(@PathVariable("id") String id)
    {
        return service.deleteFollowUp(id);
    }



    @PutMapping("/lead/followUp/{id}")
    public ResponseEntity<String> updateFollowUp(@PathVariable("id") String id , @RequestBody FollowUpRequest followUpRequest)
    {
        return service.updateFollowUp(id,followUpRequest);
    }


}
