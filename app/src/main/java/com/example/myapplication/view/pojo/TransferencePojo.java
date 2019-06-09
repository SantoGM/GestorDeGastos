package com.example.myapplication.view.pojo;

import java.util.Date;

public class TransferencePojo extends AbstractPojo implements MovementPojo {

    private Date date;
    private Float amount;
    private AccountPojo accountOrigin;
    private AccountPojo accountDestiny;
    private String detail;


    public TransferencePojo() {
    }

    public TransferencePojo(Long id, Date date, Float amount, AccountPojo accountOrigin, AccountPojo accountDestiny, String detail) {
        this.setId(id);
        this.date = date;
        this.amount = amount;
        this.accountOrigin = accountOrigin;
        this.accountDestiny = accountDestiny;
        this.detail = detail;
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
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        detail = detail;
    }


    @Override
    public String[] showDetails() {
        String[] details = {String.valueOf(this.getId()),
                            String.valueOf(this.date),
                            String.valueOf(this.amount),
                            this.accountOrigin.getName(),
                            this.accountDestiny.getName(),
                            this.detail};
        return details;
    }

    @Override
    public String nameToShow() {
        return getDate() +" ["+ getAccountOrigin() + "-" + getAccountDestiny() + "]";
    }
}
