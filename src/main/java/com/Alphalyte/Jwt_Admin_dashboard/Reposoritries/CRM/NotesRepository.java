package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM;

import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Note, Long> {
}
