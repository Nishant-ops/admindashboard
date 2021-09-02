package com.Alphalyte.Jwt_Admin_dashboard.Model.CRM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "NOTE_TBL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "Date_Time")
    private LocalDateTime dateTime;

    private String noteBy;

    private String note;

    public String getNoteBy() {
        return noteBy;
    }

    public Note(LocalDateTime dateTime, String noteBy, String note) {
        this.dateTime = dateTime;
        this.noteBy = noteBy;
        this.note = note;
    }
}
