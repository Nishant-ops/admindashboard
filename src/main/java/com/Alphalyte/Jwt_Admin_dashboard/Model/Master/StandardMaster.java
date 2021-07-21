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
public class StandardMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String StandardName;
    private String StdNameRoman;
    private String CreatedBy;
    private LocalDateTime CreatedAt;
    @Nullable
    private String ModifiedBy;
    @Nullable
    private LocalDateTime ModifiedAt;

    public StandardMaster(String standardName, String stdNameRoman, String createdBy,
                          @Nullable String modifiedBy, @Nullable LocalDateTime modifiedAt) {
        StandardName = standardName;
        StdNameRoman = stdNameRoman;
        CreatedBy = createdBy;
        CreatedAt = LocalDateTime.now();
        ModifiedBy = modifiedBy;
        ModifiedAt = modifiedAt;
    }
}
