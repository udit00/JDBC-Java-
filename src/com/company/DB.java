package com.company;

public class DB {
    private String URL;
    private String userName;
    private String password;

    DB(String URL, String userName, String password){
        this.URL=URL;
        this.userName=userName;
        this.password=password;
    }

    protected String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    protected String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    protected String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
