package com.example.myapplication.view.pojo;

/**
 * Class which contains the representation of an account for the <i>view</i>
 */
public class AccountPojo extends AbstractPojo{

    private String name;
    private Integer type;
    private String description;
    private Float balance;


    public AccountPojo() {
    }

    public AccountPojo(Long id, String name, Integer type, String description, Float balance) {
        setId(id);
        this.name = name;
        this.type = type;
        this.description = description;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
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

    @Override
    public String nameToShow() {
        return getName();
    }
}
