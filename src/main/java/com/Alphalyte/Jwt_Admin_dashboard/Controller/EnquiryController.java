package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Enquiry.Enquiry;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Enquiry.QuickEnquiry;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Enquiry.EnquiryRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Service.EnquiryService.EnquiryService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.EnquiryService.QuickEnquiryService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.EnquiryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EnquiryController {

    @Autowired
    EnquiryService enquiryService;

    @Autowired
    QuickEnquiryService quickEnquiryService;

    /*----------------------ENQUIRY FORM-------------------------------*/

    @GetMapping(value = "/enquiry")
    public ResponseEntity<List<Enquiry>> getAllEnquiry(){
        return enquiryService.getAllEnquiries();
    }


    @GetMapping(value = "/enquiry/{id}")
    public ResponseEntity<?> getEnquiryById(@PathVariable("id") long id){
        return enquiryService.getEnquiryById(id);
    }

    @PostMapping(value = "/enquiry")
    public ResponseEntity<String> postEnquiry(@RequestBody EnquiryForm enquiryForm){
        return enquiryService.addEnquiry(enquiryForm);
    }

    @PutMapping(value = "/enquiry/{id}")
    public ResponseEntity<String> updateEnquiry(@PathVariable("id") long id, @RequestBody EnquiryForm enquiryForm){
        return enquiryService.updateEnquiry(id, enquiryForm);
    }

    @DeleteMapping(value = "/enquiry/{id}")
    public ResponseEntity<String> deleteEnquiry(@PathVariable("id") long id){
        return enquiryService.deleteEnquiry(id);
    }
    /*---------------------------QUICK ENQUIRY FORM-----------------------------------------*/


    @GetMapping(value = "/quickenquiry")
    public ResponseEntity<List<QuickEnquiry>> getAllQuickEnquiries(){
        return quickEnquiryService.getAllQuickEnquiries();
    }

    @GetMapping(value = "/quickenquiry/{id}")
    public ResponseEntity<?> getQuickEnquiryById(@PathVariable("id") long id){
        return quickEnquiryService.getQuickEnquiryById(id);
    }


    @PostMapping(value = "/quickenquiry")
    public ResponseEntity<String> addQuickEnquiry(@RequestBody QuickEnquiry enquiry){
        return quickEnquiryService.addQuickEnquiry(enquiry);
    }

    @PutMapping(value = "/quickenquiry/{id}")
    public ResponseEntity<String> updateQuickEnquiry(@PathVariable("id") long id, QuickEnquiry enquiry){
        return quickEnquiryService.updateQuickEnquiry(id, enquiry);
    }

    @DeleteMapping(value = "/quickenquiry/{id}")
    public ResponseEntity<String> deleteQuickEnquiry(@PathVariable("id") long id){
        return quickEnquiryService.deleteQuickEnquiry(id);
    }


}
