package com.example.myapplication.view.pojo;

import java.util.Date;

public class TransferemcePojo extends AbstractPojo implements MovementPojo {

    private Date date;
    private Float amount;
    private AccountPojo accountOrigin;
    private AccountPojo accountDestiny;
    private String Description;


    public TransferemcePojo() {
    }

    public TransferemcePojo(Long id, Date date, Float amount, AccountPojo accountOrigin, AccountPojo accountDestiny, String description) {
        this.setId(id);
        this.date = date;
        this.amount = amount;
        this.accountOrigin = accountOrigin;
        this.accountDestiny = accountDestiny;
        Description = description;
    }


    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public AccountPojo getAccountOrigin() {
        return accountOrigin;
    }
    public void setAccountOrigin(AccountPojo accountOrigin) {
        this.accountOrigin = accountOrigin;
    }
    public AccountPojo getAccountDestiny() {
        return accountDestiny;
    }
    public void setAccountDestiny(AccountPojo accountDestiny) {
        this.accountDestiny = accountDestiny;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

}
