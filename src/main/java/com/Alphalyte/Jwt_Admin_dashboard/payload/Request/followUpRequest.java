package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class followUpRequest {
    private LocalDate date;
    private int usercode;
    private LocalDate nextCallDate;
    private String status;
    private String reason;
    private String conversation;
}
