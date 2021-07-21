package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.BoardMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardMasterRepo extends JpaRepository<BoardMaster,Integer> {
}
