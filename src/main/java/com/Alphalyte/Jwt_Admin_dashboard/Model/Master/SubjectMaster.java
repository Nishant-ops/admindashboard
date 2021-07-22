package com.Alphalyte.Jwt_Admin_dashboard.Model.Master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SubjectMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String SubjectName;
    private String SubjectAbb;
    private String CreatedBy;
    private LocalDateTime CreatedAt;
    private String ModifiedBy;
    private LocalDateTime ModifiedAt;

    public SubjectMaster(String subjectName, String subjectAbb, String createdBy,
                          String modifiedBy) {
        SubjectName = subjectName;
        SubjectAbb = subjectAbb;
        CreatedBy = createdBy;
        ModifiedBy = modifiedBy;
    }
}
