package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Lead;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Lead.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepo extends JpaRepository<Lead, Integer> {

}
