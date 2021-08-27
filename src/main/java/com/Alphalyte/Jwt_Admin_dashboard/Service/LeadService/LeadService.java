package com.Alphalyte.Jwt_Admin_dashboard.Service.LeadService;


import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.FollowUp;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead.LeadRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead.FollowupRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserRepository;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.LeadForm;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.FollowUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeadService {

    @Autowired
    LeadRepo leadRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    FollowupRepo followUpRepo;


    public ResponseEntity<String> saveLead(LeadForm lead){

        user assignTo = userRepo.getById(lead.getUsercode());




            Lead dblead = new Lead();
            if(lead.getName()!=null) {
                dblead.setName(lead.getName());
            }else
                return new ResponseEntity<>("Name is null",HttpStatus.BAD_REQUEST);

            if(lead.getGender()!=null&&(lead.getGender().equals("Male")||lead.getGender().equals("Female"))) {
                dblead.setGender(lead.getGender());
            }else
                return new ResponseEntity<>("gender cannot be Null or wrong",HttpStatus.BAD_REQUEST);

            if(lead.getMobile() > 6000000000L && lead.getMobile() < 9999999999L) {
                dblead.setMobile(lead.getMobile());
            }else
                return new ResponseEntity<>("Phone number is not valid",HttpStatus.BAD_REQUEST);

            if(lead.getEmail()!=null) {
                dblead.setEmail(lead.getEmail());
            } else
                return new ResponseEntity<>("email is null",HttpStatus.BAD_REQUEST);
            dblead.setDate(lead.getDate());
            dblead.setAssignTo(assignTo);
            if(lead.getGuardianName()!=null) {
                dblead.setGuardianName(lead.getGuardianName());
            } else
                return new ResponseEntity<>("guardian name cannot be null",HttpStatus.BAD_REQUEST);

            if(lead.getGuardianMobile() > 6000000000L && lead.getGuardianMobile() < 9999999999L) {
                dblead.setGuardianMobile(lead.getGuardianMobile());
            }
            else new ResponseEntity<>("Phone number is not valid",HttpStatus.BAD_REQUEST);

            if(lead.getStatus()!=null) {
                dblead.setStatus(lead.getStatus());
            } else
                return new ResponseEntity<>("status cannot be null",HttpStatus.BAD_REQUEST);

            dblead.setLeadSource(lead.getLeadSource());
            dblead.setRemark(lead.getRemark());

            if(lead.getAddress()!=null) {
                dblead.setAddress(lead.getAddress());
            } else
                return new ResponseEntity<>("address cannot be null",HttpStatus.BAD_REQUEST);

            if(lead.getBoard()!=null) {
                dblead.setBoard(lead.getBoard());
            } else
                return new ResponseEntity<>("Board cannot be null",HttpStatus.BAD_REQUEST);

            if(lead.getCity()!=null) {
                dblead.setCity(lead.getCity());
            } else
                return new ResponseEntity<>("City cannot be null",HttpStatus.BAD_REQUEST);

            if(lead.getCollege()!=null) {
                dblead.setCollege(lead.getCollege());
            } else
                return new ResponseEntity<>("College cannot be null",HttpStatus.BAD_REQUEST);

            if(lead.getCountry()!=null) {
                dblead.setCountry(lead.getCountry());
            } else
                return new ResponseEntity<>("Country cannot be null",HttpStatus.BAD_REQUEST);

            if(lead.getDegree()!=null) {
                dblead.setDegree(lead.getDegree());
            } else
                return new ResponseEntity<>("Degree cannot be null",HttpStatus.BAD_REQUEST);

            if(lead.getState()!=null) {
                dblead.setState(lead.getState());
            } else
                return new ResponseEntity<>("state cannot be null",HttpStatus.BAD_REQUEST);

            dblead.setMedium(lead.getMedium());

            leadRepo.save(dblead);
            return new ResponseEntity<>("Lead saved", HttpStatus.CREATED);
        }


    public ResponseEntity<List<Lead>> getAllLeads(){
        return new ResponseEntity<>(leadRepo.findAll() , HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<String> deletebyid(String id) {
       if (leadRepo.existsById(id)) {
           Lead lead = leadRepo.getById(id);
           lead.setAssignTo(null);
           leadRepo.deleteById(id);
           return new ResponseEntity<String>("Lead deleted", HttpStatus.OK);
       }
       else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> updateLead(String id , LeadForm lead){
        if (leadRepo.existsById(id)) {
            Lead dblead = leadRepo.getById(id);

            user assignTo = userRepo.getById(lead.getUsercode());


                if(lead.getName()!=null) {
                    dblead.setName(lead.getName());
                }else
                    return new ResponseEntity<>("Name is null",HttpStatus.BAD_REQUEST);

                if(lead.getGender()!=null&&(lead.getGender().equals("Male")||lead.getGender().equals("Female"))) {
                    dblead.setGender(lead.getGender());
                }else
                    return new ResponseEntity<>("gender cannot be Null or wrong",HttpStatus.BAD_REQUEST);

                if(lead.getMobile() > 6000000000L && lead.getMobile() < 9999999999L) {
                    dblead.setMobile(lead.getMobile());
                }else
                    return new ResponseEntity<>("Phone number is not valid",HttpStatus.BAD_REQUEST);

                if(lead.getEmail()!=null) {
                    dblead.setEmail(lead.getEmail());
                } else
                    return new ResponseEntity<>("email is null",HttpStatus.BAD_REQUEST);
                dblead.setDate(lead.getDate());
                dblead.setAssignTo(assignTo);
                if(lead.getGuardianName()!=null) {
                    dblead.setGuardianName(lead.getGuardianName());
                } else
                    return new ResponseEntity<>("guardian name cannot be null",HttpStatus.BAD_REQUEST);

                if(lead.getGuardianMobile() > 6000000000L && lead.getGuardianMobile() < 9999999999L) {
                    dblead.setGuardianMobile(lead.getGuardianMobile());
                }
                else new ResponseEntity<>("Phone number is not valid",HttpStatus.BAD_REQUEST);

                if(lead.getStatus()!=null) {
                    dblead.setStatus(lead.getStatus());
                } else
                    return new ResponseEntity<>("status cannot be null",HttpStatus.BAD_REQUEST);

                dblead.setLeadSource(lead.getLeadSource());
                dblead.setRemark(lead.getRemark());

                if(lead.getAddress()!=null) {
                    dblead.setAddress(lead.getAddress());
                } else
                    return new ResponseEntity<>("address cannot be null",HttpStatus.BAD_REQUEST);

                if(lead.getBoard()!=null) {
                    dblead.setBoard(lead.getBoard());
                } else
                    return new ResponseEntity<>("Board cannot be null",HttpStatus.BAD_REQUEST);

                if(lead.getCity()!=null) {
                    dblead.setCity(lead.getCity());
                } else
                    return new ResponseEntity<>("City cannot be null",HttpStatus.BAD_REQUEST);

                if(lead.getCollege()!=null) {
                    dblead.setCollege(lead.getCollege());
                } else
                    return new ResponseEntity<>("College cannot be null",HttpStatus.BAD_REQUEST);

                if(lead.getCountry()!=null) {
                    dblead.setCountry(lead.getCountry());
                } else
                    return new ResponseEntity<>("Country cannot be null",HttpStatus.BAD_REQUEST);

                if(lead.getDegree()!=null) {
                    dblead.setDegree(lead.getDegree());
                } else
                    return new ResponseEntity<>("Degree cannot be null",HttpStatus.BAD_REQUEST);

                if(lead.getState()!=null) {
                    dblead.setState(lead.getState());
                } else
                    return new ResponseEntity<>("state cannot be null",HttpStatus.BAD_REQUEST);

                dblead.setMedium(lead.getMedium());




            leadRepo.save(dblead);

            return new ResponseEntity<>("Lead updated", HttpStatus.OK);
        }
        else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }








    @Transactional
    public ResponseEntity<String> updateLeadStatus(String uuid, String status){
        if (leadRepo.existsById(uuid)){
            Lead lead = leadRepo.getById(uuid);
            lead.setStatus(status);
            return new ResponseEntity<>("LEAD STATUS UPDATED", HttpStatus.OK);
        }
        else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<String> updateAssignTo(int usercode, String uuid){
        if (leadRepo.existsById(uuid)){
            Lead lead = leadRepo.getById(uuid);
            user user = userRepo.getById(usercode);
            lead.setAssignTo(user);
            return new ResponseEntity<>("LEAD ASSIGNED TO: " + user.getUsername(), HttpStatus.OK);
        }
        else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<List<Lead>> getallLeadsFromAssignUsercode(int usercode)
    {
        if(userRepo.existsById(usercode))
        {
            return new ResponseEntity<>(leadRepo.getAllLeadsAssignToUsercode(usercode)
            ,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    /*-----------------------------------------FOLLOW UP------------------------------------------------*/

    public ResponseEntity<List<FollowUp>> getAllFollowUps()
    {
        return new ResponseEntity<>(followUpRepo.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<List<FollowUp>> getAllFollowUpFromAssignUsercode(int usercode)
    {
        if(userRepo.existsById(usercode))
        {
            return new ResponseEntity<>(followUpRepo.getAllFollowUpFromUsercode(usercode) ,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getFollowUpFromId(String uid) {
        if(followUpRepo.existsById(uid))
        {
            return new ResponseEntity<>(followUpRepo.getById(uid), HttpStatus.OK);
        }
        return new ResponseEntity<>("uid does not exist by uid",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> getCountFollowUpFromLeadId(String uid) {
        if(leadRepo.existsById(uid))
        {
            return new ResponseEntity<>(followUpRepo.getFollowUpByLeadId(uid) , HttpStatus.OK);
        }
        return new ResponseEntity<>("uid does not exist by uid",HttpStatus.BAD_REQUEST);
    }




    public ResponseEntity<String> addFollowUp(FollowUpRequest followUpRequest) {

        FollowUp followUp = new FollowUp();

        if (userRepo.existsById(followUpRequest.getUsercode())) {

            user user = userRepo.getById(followUpRequest.getUsercode());
            followUp.setFollowUpBy(user);

        } else return new ResponseEntity<>("Invalid usercode", HttpStatus.NOT_FOUND);

        if (leadRepo.existsById(followUpRequest.getLeadId())){
            followUp.setLead(leadRepo.getById(followUpRequest.getLeadId()));
        } else return new ResponseEntity<>("Invalid lead", HttpStatus.NOT_FOUND);

        followUp.setDate(followUpRequest.getDate());
        followUp.setConversation(followUpRequest.getConversation());
        followUp.setReason(followUpRequest.getReason());
        followUp.setNextCallDate(followUpRequest.getNextCallDate());
        followUp.setStatus(followUpRequest.getStatus());

        followUpRepo.save(followUp);


        return new ResponseEntity<>("FollowUp created",HttpStatus.CREATED);
    }







    public ResponseEntity<String> deleteFollowUp(String id)
    {
        if(followUpRepo.existsById(id))
        {
            followUpRepo.deleteById(id);
            return new ResponseEntity<>("Follow Up Deleted",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateFollowUp(String id, FollowUpRequest followUpRequest) {
        if(followUpRepo.existsById(id))
        {
            FollowUp followUp = followUpRepo.getById(id);

            if (userRepo.existsById(followUpRequest.getUsercode())) {
                user user = userRepo.getById(followUpRequest.getUsercode());
                followUp.setFollowUpBy(user);
            } else return new ResponseEntity<>("Invalid usercode", HttpStatus.NOT_FOUND);

            followUp.setDate(followUpRequest.getDate());
            followUp.setStatus(followUpRequest.getStatus());
            followUp.setConversation(followUp.getConversation());
            followUp.setNextCallDate(followUpRequest.getNextCallDate());
            followUp.setReason(followUpRequest.getReason());

            followUpRepo.save(followUp);

            return new ResponseEntity<>("FollowUp Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}//end class
