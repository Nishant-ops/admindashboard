package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Enquiry.Enquiry;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Enquiry.EnquiryRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Service.EnquiryService.EnquiryService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.EnquiryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EnquiryController {

    @Autowired
    EnquiryService service;

    @GetMapping(value = "/enquiry")
    public ResponseEntity<List<Enquiry>> getAllEnquiry(){
        return service.getAllEnquiries();
    }


    @GetMapping(value = "/enquiry/{id}")
    public ResponseEntity<?> getEnquiryById(@PathVariable("id") long id){
        return service.getEnquiryById(id);
    }

    @PostMapping(value = "/enquiry")
    public ResponseEntity<String> postEnquiry(@RequestBody EnquiryForm enquiryForm){
        return service.addEnquiry(enquiryForm);
    }

    @PutMapping(value = "/enquiry/{id}")
    public ResponseEntity<String> updateEnquiry(@PathVariable("id") long id, @RequestBody EnquiryForm enquiryForm){
        return service.updateEnquiry(id, enquiryForm);
    }

    @DeleteMapping(value = "/enquiry/{id}")
    public ResponseEntity<String> deleteEnquiry(@PathVariable("id") long id){
        return service.deleteEnquiry(id);
    }

}
