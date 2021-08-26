package com.Alphalyte.Jwt_Admin_dashboard.Service.EnquiryService;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Enquiry.QuickEnquiry;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Enquiry.QuickEnquiryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuickEnquiryService {

    @Autowired
    QuickEnquiryRepo repo;

    public ResponseEntity<List<QuickEnquiry>> getAllQuickEnquiries(){
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<?> getQuickEnquiryById(long id){
        if (repo.existsById(id)) {
            return new ResponseEntity<>(repo.getById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> addQuickEnquiry(QuickEnquiry enquiry){
        System.out.println(enquiry);
        repo.save(enquiry);
        return new ResponseEntity<>("Quick enquiry saved.",HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuickEnquiry(long id, QuickEnquiry enquiry){
        if (repo.existsById(id)){
            enquiry.setId(id);
            repo.save(enquiry);
            return new ResponseEntity<>("Enquiry Updated", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Invalid id", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteQuickEnquiry(long id){
        if (repo.existsById(id)){
            repo.deleteById(id);
            return new ResponseEntity<>("Enquiry deleted", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Invalid id", HttpStatus.NOT_FOUND);
    }


}
