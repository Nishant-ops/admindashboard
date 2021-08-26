package com.Alphalyte.Jwt_Admin_dashboard.Controller;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead.LeadRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Service.LeadService.LeadService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.LeadForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LeadMenuController {

    @Autowired
    LeadService service;

    @Autowired
    LeadRepo repo;

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
    public ResponseEntity<List<Lead>> getallLeadsAssigntousercode(@PathVariable("usercode") int id)
    {
        return service.getallLeadsFromAssignUsercode(id);
    }

}
