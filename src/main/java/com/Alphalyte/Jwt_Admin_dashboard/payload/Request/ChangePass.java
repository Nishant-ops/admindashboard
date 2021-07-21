package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePass {
    private int usercode;
    private String oldpass;
    private String newpass;

    public ChangePass(int usercode, String oldpass, String newpass) {
        this.usercode = usercode;
        this.oldpass = oldpass;
        this.newpass = newpass;
    }

}
