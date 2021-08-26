package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepo extends JpaRepository<Lead, String> {

    @Query(value = "SELECT * FROM leads WHERE assign_to_usercode=?1",nativeQuery = true)
    List<Lead> getAllLeadsAssignToUsercode(int usercode);
}
