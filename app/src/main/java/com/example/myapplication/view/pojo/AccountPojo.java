package com.example.myapplication.view.pojo;

/**
 * Class which contains the representation of an account for the <i>view</i>
 */
public class AccountPojo extends AbstractPojo{

    private String name;
    private Integer type;
    private String description;
    private Float balance;

    private Integer disable;


    public AccountPojo() {
    }

    public AccountPojo(Long id, String name, Integer type, String description, Float balance) {
        setId(id);
        this.name = name;
        this.type = type;
        this.description = description;
        this.balance = balance;
    }

    public AccountPojo(Long id, String name, Integer type, String description, Float balance, Integer disable) {
        setId(id);
        this.name = name;
        this.type = type;
        this.description = description;
        this.balance = balance;
        this.disable = disable;
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
    public Integer getDisable() {
        return disable;
    }
    public void setDisable(Integer disable) {
        this.disable = disable;
    }


    @Override
    public String nameToShow() {
        return getName();
    }
}
