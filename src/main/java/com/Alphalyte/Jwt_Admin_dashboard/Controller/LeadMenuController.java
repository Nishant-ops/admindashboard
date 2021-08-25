package com.Alphalyte.Jwt_Admin_dashboard.Controller;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import com.Alphalyte.Jwt_Admin_dashboard.Service.LeadService.LeadService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.LeadForm;
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

    @DeleteMapping(value="/lead/{usercode}")
    public ResponseEntity<String> deletebyusercode(@PathVariable int usercode)
    {
        return service.deletebyid(usercode);
    }


}
