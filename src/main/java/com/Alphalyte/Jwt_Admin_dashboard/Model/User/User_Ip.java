package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

import javax.persistence.*;

@Entity
public class User_Ip {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int id;
    @Column(nullable = false)
    @JoinColumn()
    public String user_id;
//    public int usercode;
    public String IpAddress;

//    public User_Ip(String user_id, String ipAddress, int usercode) {
//        this.user_id = user_id;
//        this.IpAddress = ipAddress;
//        this.usercode = usercode;
//    }


    public User_Ip(String username, String ip) {
        this.user_id = username;
        this.IpAddress = ip;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }
}
