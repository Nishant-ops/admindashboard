package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequest {
    private LocalDateTime dateTime;
    private int usercode;
    private long clientId;
    private String note;
}
