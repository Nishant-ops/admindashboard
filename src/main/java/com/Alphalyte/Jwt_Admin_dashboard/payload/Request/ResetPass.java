package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPass {
    private int usercode;
    private String newpass;

}
