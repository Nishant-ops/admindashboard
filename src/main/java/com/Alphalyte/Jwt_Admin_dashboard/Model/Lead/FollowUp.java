package com.Alphalyte.Jwt_Admin_dashboard.Model.Lead;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FollowUp {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    private LocalDate date;

    @OneToOne(cascade = CascadeType.MERGE)
    private user assignTo;

    private LocalDate nextCallDate;
    private String status;
    private String reason;
    private String conversation;

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAssignTo() {
        return assignTo.getUsername();
    }

    public LocalDate getNextCallDate() {
        return nextCallDate;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getConversation() {
        return conversation;
    }
}
