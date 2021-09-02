package com.Alphalyte.Jwt_Admin_dashboard.Model.CRM;


import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ProjectRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "PROJECT_TBL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROJECT_TBL")
public class Project {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "enquiry_sequence"),
                    @Parameter(name = "initial_value", value = "100768"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @Column(name = "proj_name")
    private String pname;
    private long commitment;
    private String status;
    private String description;
    private String remark;
    private Long clientId;
    private LocalDate date;
    @Lob
    private byte[] file;

    public Project(ProjectRequest projectRequest) {
        this.pname = projectRequest.getPname();
        this.commitment = projectRequest.getCommitment();
        this.status = projectRequest.getStatus();
        this.description = projectRequest.getDescription();
        this.remark = projectRequest.getRemark();
        this.date = projectRequest.getDate();
    }
}
