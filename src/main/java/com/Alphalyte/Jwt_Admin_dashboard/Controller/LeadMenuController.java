package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Service.LeadService.LeadService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.FollowUpRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.LeadForm;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.followUpResponse;
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


/*----------------------------------------------------Saving a lead-----------------------------------------------------------------*/
    @PostMapping(value = "/lead")
    public ResponseEntity<String> saveLead(@RequestBody LeadForm lead){
        return service.saveLead(lead);
    }
    /*----------------------------------------------------getting all lead-----------------------------------------------------------------*/
    @GetMapping(value = "/lead")
    public ResponseEntity<List<leadResponse>> getAllLeads(){
        return service.getAllLeads();
    }
    /*----------------------------------------------------Deleting a lead-----------------------------------------------------------------*/
    @DeleteMapping(value="/lead/{id}")
    public ResponseEntity<String> deletebyusercode(@PathVariable("id") String id)
    {
        return service.deletebyid(id);
    }
    /*----------------------------------------------------Updating a lead-----------------------------------------------------------------*/
    @PutMapping(value = "/lead/{id}")
    public ResponseEntity<String> updateLead(@PathVariable("id") String id, @RequestBody LeadForm lead){
        return service.updateLead(id,lead);
    }
    /*----------------------------------------------------Updating a Status-----------------------------------------------------------------*/
    @PutMapping("/lead/status/{id}")
    public ResponseEntity<String> updateLeadStatus(@PathVariable("id") String id, @RequestBody String status){
        return service.updateLeadStatus(id, status);
    }
    /*----------------------------------------------------Updating a Assign a Lead-----------------------------------------------------------------*/
    @PutMapping("/lead/assignBy/{id}")
    public ResponseEntity<String> updateLeadAssign(@PathVariable("id") String id, @RequestBody int usercode){
        return service.updateAssignTo(usercode, id);
    }
    /*----------------------------------------------------Getting all lead assign to a usercode-----------------------------------------------------------------*/
    @GetMapping("/lead/assign/{usercode}")
    public ResponseEntity<List<leadResponse>> getAllLeadsAssignToUsercode(@PathVariable("usercode") int id)
    {
        return service.getallLeadsFromAssignUsercode(id);
    }
    /*----------------------------------------------------Getting all Follow Up request-----------------------------------------------------------------*/
    @GetMapping("/lead/followUp")
    public ResponseEntity<List<followUpResponse>> getallFollowUps()
    {
        return service.getAllFollowUps();
    }

    /*----------------------------------------------------Getting all Follow up from assign usercode-----------------------------------------------------------------*/

    @GetMapping("/lead/followUp/{usercode}")
    public ResponseEntity<List<followUpResponse>> getAllFollowupsFromUsercode(@PathVariable("usercode") int usercode)
    {
        return service.getAllFollowUpFromAssignUsercode(usercode);
    }
    /*----------------------------------------------------Get a follow up----------------------------------------------------------------*/
    @GetMapping("/lead/followUpBy/{id}")
    public ResponseEntity<?> getFollowUpById(@PathVariable("id") String uid)
    {
        return service.getFollowUpFromId(uid);
    }
    /*----------------------------------------------------Saving a FollowUp-----------------------------------------------------------------*/
    @PostMapping("/lead/followUp")
    public ResponseEntity<String> saveFollowUp(@RequestBody FollowUpRequest followUpRequest)
    {
        return service.addFollowUp(followUpRequest);
    }

    /*----------------------------------------------------Deleting a FollowUp-----------------------------------------------------------------*/


    @DeleteMapping("/lead/followUp/{id}")
    public ResponseEntity<String> deleteFollowUp(@PathVariable("id") String id)
    {
        return service.deleteFollowUp(id);
    }
    /*----------------------------------------------------Updating a FollowUp-----------------------------------------------------------------*/
    @PutMapping("/lead/followUp/{id}")
    public ResponseEntity<String> updateFollowUp(@PathVariable("id") String id , @RequestBody FollowUpRequest followUpRequest)
    {
        return service.updateFollowUp(id,followUpRequest);
    }


}
