package com.example.myapplication.view.pojo;

public class AccountPojo extends AbstractPojo{

    private String name;
    private String description;
    private Float balance;


    public AccountPojo() {
    }

    public AccountPojo(Long id, String name, String description, Float balance) {
        setId(id);
        this.name = name;
        this.description = description;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }

}
