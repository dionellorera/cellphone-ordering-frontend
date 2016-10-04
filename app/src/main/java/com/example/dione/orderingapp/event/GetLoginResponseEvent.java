package com.example.dione.orderingapp.event;

/**
 * Created by dione on 29 Sep 2016.
 */

public class GetLoginResponseEvent {
    private String username;
    private String password;
    public GetLoginResponseEvent(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
