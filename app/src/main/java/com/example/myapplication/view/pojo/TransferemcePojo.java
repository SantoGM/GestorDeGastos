package com.example.myapplication.view.pojo;

import java.util.Date;

public class TransferemcePojo extends AbstractPojo implements MovementPojo {

    private Date date;
    private Float amount;
    private AccountPojo accountOrigin;
    private AccountPojo accountDestiny;
    private String description;


    public TransferemcePojo() {
    }

    public TransferemcePojo(Long id, Date date, Float amount, AccountPojo accountOrigin, AccountPojo accountDestiny, String description) {
        this.setId(id);
        this.date = date;
        this.amount = amount;
        this.accountOrigin = accountOrigin;
        this.accountDestiny = accountDestiny;
        this.description = description;
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
        return description;
    }
    public void setDescription(String description) {
        description = description;
    }


    @Override
    public String[] showDetails() {
        String[] details = {String.valueOf(this.getId()),
                            String.valueOf(this.date),
                            String.valueOf(this.amount),
                            this.accountOrigin.getName(),
                            this.accountDestiny.getName(),
                            this.description};
        return details;
    }

    @Override
    public String nameToShow() {
        return getDate() + "- ^[" + getAccountOrigin().getName() + ":" + getAccountDestiny().getName() + "]";
    }
}
