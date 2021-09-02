package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM;

import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
