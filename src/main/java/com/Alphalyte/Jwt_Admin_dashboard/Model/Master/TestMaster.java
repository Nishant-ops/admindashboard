package com.Alphalyte.Jwt_Admin_dashboard.Model.Master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TestMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String MockTestName;
    private String LastYearPaper;
    private String CreatedBy;
    private LocalDateTime CreatedAt;
    @Nullable
    private String ModifiedBy;
    @Nullable
    private LocalDateTime ModifiedAt;

    public TestMaster(String mockTestName, String lastYearPaper, String createdBy,
                      @Nullable String modifiedBy, @Nullable LocalDateTime modifiedAt) {
        MockTestName = mockTestName;
        LastYearPaper = lastYearPaper;
        CreatedBy = createdBy;
        CreatedAt = LocalDateTime.now();
        ModifiedBy = modifiedBy;
        ModifiedAt = modifiedAt;
    }
}
