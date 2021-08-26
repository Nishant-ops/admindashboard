package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface followUp extends JpaRepository<FollowUp,String> {

    @Query(value = "SELECT * FROM follow_up WHERE assign_to_usercode=?1",nativeQuery = true)
    List<FollowUp> getAllFollowUpFromUsercode(int usercode);
}
