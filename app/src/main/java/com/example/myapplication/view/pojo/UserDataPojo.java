package com.example.myapplication.view.pojo;

public class UserDataPojo extends AbstractPojo {

    private String name;
    private String email;
    private Integer pin;

    public UserDataPojo(String name, String email, Integer pin){
        setName(name);
        setEmail(email);
        setPin(pin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    @Override
    public String nameToShow() {
        return getName();
    }
}
