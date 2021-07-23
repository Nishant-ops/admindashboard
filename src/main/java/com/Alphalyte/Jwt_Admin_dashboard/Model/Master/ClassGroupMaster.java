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
@Getter
@Setter
@NoArgsConstructor
public class ClassGroupMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ClassGroup;
//    private String ClassGroupAbb;
    private String CreatedBy;
    private LocalDateTime CreatedAt;
    @Nullable
    private String ModifiedBy;
    @Nullable
    private LocalDateTime ModifiedAt;


    public ClassGroupMaster(String classGroup, /*String classGroupAbb,*/ String createdBy,
                            @Nullable String modifiedBy, @Nullable LocalDateTime modifiedAt) {
        ClassGroup = classGroup;
//        ClassGroupAbb = classGroupAbb;
        CreatedBy = createdBy;
        CreatedAt = LocalDateTime.now();
        ModifiedBy = modifiedBy;
        ModifiedAt = modifiedAt;
    }
}
