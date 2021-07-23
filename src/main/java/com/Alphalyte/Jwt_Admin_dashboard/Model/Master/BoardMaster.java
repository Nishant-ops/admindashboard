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
public class BoardMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String BoardName;
    private String BoardAbb;
    private String CreatedBy;
    private LocalDateTime CreatedAt;
    private String ModifiedBy;
    private LocalDateTime ModifiedAt;

    public BoardMaster(String boardName, String boardAbb, String createdBy,
                       LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt) {
        BoardName = boardName;
        BoardAbb = boardAbb;
        CreatedBy = createdBy;
        CreatedAt = createdAt;
        ModifiedBy = modifiedBy;
        ModifiedAt = modifiedAt;
    }
}
