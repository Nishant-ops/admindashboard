package com.Alphalyte.Jwt_Admin_dashboard.Service.EnquiryService;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Enquiry.Enquiry;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Enquiry.EnquiryRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserRepository;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.EnquiryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryService {

    @Autowired
    EnquiryRepo repo;

    @Autowired
    UserRepository userRepo;

    public ResponseEntity<List<Enquiry>> getAllEnquiries(){
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<?> getEnquiryById(long id){
        if (repo.existsById(id)){
            return new ResponseEntity<>(repo.getById(id), HttpStatus.OK);
        }
        else return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addEnquiry(EnquiryForm enquiryForm){
        Enquiry dbenquiry = new Enquiry();
        dbenquiry.setBatch(enquiryForm.getBatch());
        dbenquiry.setFirstName(enquiryForm.getFirstName());
        dbenquiry.setLastName(enquiryForm.getLastName());
        dbenquiry.setGender(enquiryForm.getGender());
        dbenquiry.setDateOfEnquiry(enquiryForm.getDateOfEnquiry());
        dbenquiry.setDateOfBirth(enquiryForm.getDateOfBirth());
        dbenquiry.setPhone(enquiryForm.getPhone());
        dbenquiry.setEmail(enquiryForm.getEmail());
        dbenquiry.setGuardianName(enquiryForm.getGuardianName());
        dbenquiry.setGuardianPhone(enquiryForm.getGuardianPhone());
        dbenquiry.setAddress(enquiryForm.getAddress());
        dbenquiry.setCountry(enquiryForm.getCountry());
        dbenquiry.setState(enquiryForm.getState());
        dbenquiry.setCity(enquiryForm.getCity());
        dbenquiry.setInstitute(enquiryForm.getInstitute());
        dbenquiry.setStandard(enquiryForm.getStandard());
        dbenquiry.setSemester(enquiryForm.getSemester());
        dbenquiry.setBoard(enquiryForm.getBoard());
        dbenquiry.setMedium(enquiryForm.getMedium());
        dbenquiry.setGrossAmount(enquiryForm.getGrossAmount());
        dbenquiry.setComittedAmount(enquiryForm.getComittedAmount());
        dbenquiry.setPrevClassPercent(enquiryForm.getPrevClassPercent());
        dbenquiry.setNextCallDate(enquiryForm.getNextCallDate());
        dbenquiry.setFbLink(enquiryForm.getFbLink());
        dbenquiry.setTag(enquiryForm.getTag());
        dbenquiry.setEnquirySource(enquiryForm.getEnquirySource());

        if (userRepo.existsById(enquiryForm.getUsercode())){
            dbenquiry.setAssignTo(userRepo.getById(enquiryForm.getUsercode()));
        } else return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        dbenquiry.setCareerObjective(enquiryForm.getCareerObjective());
        dbenquiry.setRemark(enquiryForm.getRemark());

        repo.save(dbenquiry);

        return new ResponseEntity<>("Enquiry submitted",HttpStatus.CREATED);
    }


    public ResponseEntity<String> updateEnquiry(long id, EnquiryForm enquiryForm){
        if (repo.existsById(id)) {
            Enquiry dbenquiry = new Enquiry();
            dbenquiry.setBatch(enquiryForm.getBatch());
            dbenquiry.setFirstName(enquiryForm.getFirstName());
            dbenquiry.setLastName(enquiryForm.getLastName());
            dbenquiry.setGender(enquiryForm.getGender());
            dbenquiry.setDateOfEnquiry(enquiryForm.getDateOfEnquiry());
            dbenquiry.setDateOfBirth(enquiryForm.getDateOfBirth());
            dbenquiry.setPhone(enquiryForm.getPhone());
            dbenquiry.setEmail(enquiryForm.getEmail());
            dbenquiry.setGuardianName(enquiryForm.getGuardianName());
            dbenquiry.setGuardianPhone(enquiryForm.getGuardianPhone());
            dbenquiry.setAddress(enquiryForm.getAddress());
            dbenquiry.setCountry(enquiryForm.getCountry());
            dbenquiry.setState(enquiryForm.getState());
            dbenquiry.setCity(enquiryForm.getCity());
            dbenquiry.setInstitute(enquiryForm.getInstitute());
            dbenquiry.setStandard(enquiryForm.getStandard());
            dbenquiry.setSemester(enquiryForm.getSemester());
            dbenquiry.setBoard(enquiryForm.getBoard());
            dbenquiry.setMedium(enquiryForm.getMedium());
            dbenquiry.setGrossAmount(enquiryForm.getGrossAmount());
            dbenquiry.setComittedAmount(enquiryForm.getComittedAmount());
            dbenquiry.setPrevClassPercent(enquiryForm.getPrevClassPercent());
            dbenquiry.setNextCallDate(enquiryForm.getNextCallDate());
            dbenquiry.setFbLink(enquiryForm.getFbLink());
            dbenquiry.setTag(enquiryForm.getTag());
            dbenquiry.setEnquirySource(enquiryForm.getEnquirySource());

            if (userRepo.existsById(enquiryForm.getUsercode())) {
                dbenquiry.setAssignTo(userRepo.getById(enquiryForm.getUsercode()));
            } else return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

            dbenquiry.setCareerObjective(enquiryForm.getCareerObjective());
            dbenquiry.setRemark(enquiryForm.getRemark());

            repo.save(dbenquiry);

            return new ResponseEntity<>("Enquiry updated", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid id", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> deleteEnquiry(long id){
        if (repo.existsById(id)){
            repo.deleteById(id);
            return new ResponseEntity<>("Enquiry deleted",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Invalid id",HttpStatus.NOT_FOUND);
    }


}//end class
