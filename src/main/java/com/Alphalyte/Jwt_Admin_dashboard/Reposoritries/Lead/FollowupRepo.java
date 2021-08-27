package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowupRepo extends JpaRepository<FollowUp,String> {

    @Query(value = "SELECT * FROM follow_up WHERE follow_up_by_usercode=?",nativeQuery = true)
    List<FollowUp> getAllFollowUpFromUsercode(int usercode);

    @Query(value = "SELECT COUNT(*) FROM follow_up WHERE lead_id=?", nativeQuery = true)
    Integer getAllFollowUpsFromLeadId(String uid);

    @Query(value = "SELECT * FROM follow_up WHERE lead_id=?", nativeQuery = true)
    List<FollowUp> getFollowUpByLeadId(String uid);
}
