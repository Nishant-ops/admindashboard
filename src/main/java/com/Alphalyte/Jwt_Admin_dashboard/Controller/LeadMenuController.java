package com.Alphalyte.Jwt_Admin_dashboard.Controller;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.FollowUp;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead.LeadRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Service.LeadService.LeadService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.LeadForm;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.followUpRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.follow_up_Response;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.leadResponse;
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
    public ResponseEntity<List<leadResponse>> getAllLeads(){
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
    public ResponseEntity<List<leadResponse>> getAllLeadsAssignToUsercode(@PathVariable("usercode") int id)
    {
        return service.getallLeadsFromAssignUsercode(id);
    }

    @GetMapping("/lead/followUp")
    public ResponseEntity<List<follow_up_Response>> getallFollowUps()
    {
        return service.getAllFollowUps();
    }


    @GetMapping("/lead/followUp/{usercode}")
    public ResponseEntity<List<follow_up_Response>> getAllFollowupsFromUsercode(@PathVariable("usercode") int usercode)
    {
        return service.getAllFollowUpFromAssignUsercode(usercode);
    }

    @GetMapping("/lead/followUpBy/{id}")
    public ResponseEntity<?> getFollowUpById(@PathVariable("id") String uid)
    {
        return service.getFollowUpFromId(uid);
    }

    @PostMapping("/lead/followUp")
    public ResponseEntity<String> saveFollowUp(@RequestBody followUpRequest followUpRequest)
    {
        return service.addFollowUp(followUpRequest);
    }


    //TODO: check in postman
    @DeleteMapping("/lead/followUp/{id}")
    public ResponseEntity<String> deleteFollowUp(@PathVariable("id") String id)
    {
        return service.deleteFollowUp(id);
    }

    @PutMapping("/lead/followUp/{id}")
    public ResponseEntity<String> updateFollowUp(@PathVariable("id") String id , @RequestBody followUpRequest followUpRequest)
    {
        return service.updateFollowUp(id,followUpRequest);
    }


}
