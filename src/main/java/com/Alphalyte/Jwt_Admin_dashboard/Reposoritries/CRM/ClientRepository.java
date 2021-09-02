package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM;

import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
