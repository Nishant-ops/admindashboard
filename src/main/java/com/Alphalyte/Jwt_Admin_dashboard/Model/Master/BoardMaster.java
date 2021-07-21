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
                       String modifiedBy, LocalDateTime modifiedAt) {
        this.BoardName = boardName;
        this.BoardAbb = boardAbb;
        this.CreatedBy = createdBy;
        this.ModifiedBy = modifiedBy;
        this.ModifiedAt = modifiedAt;
    }
}
