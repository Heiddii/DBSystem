package com.example.villariveradbsystem.loginprogram;

public class LoginDatabase {
    private String username, password, firstname, lastname, admin;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getAdmin(){
        return admin;
    }


    public LoginDatabase(String username, String password, String firstname, String lastname, String admin) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.admin = admin;
    }
}
