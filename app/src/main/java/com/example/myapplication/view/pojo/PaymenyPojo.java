package com.example.myapplication.view.pojo;

import java.util.Date;

public class PaymenyPojo extends AbstractPojo implements MovementPojo {

    private Date date;
    private Float amount;
    private CategoryPojo category;
    private AccountPojo account;
    private String Detail;
    private Boolean creditCard;


    public PaymenyPojo() {
    }

    public PaymenyPojo(Long id, Date date, Float amount, CategoryPojo category, AccountPojo account, String detail, Boolean creditCard) {
        this.setId(id);
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.account = account;
        Detail = detail;
        this.creditCard = creditCard;
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
    public CategoryPojo getCategory() {
        return category;
    }
    public void setCategory(CategoryPojo category) {
        this.category = category;
    }
    public AccountPojo getAccount() {
        return account;
    }
    public void setAccount(AccountPojo account) {
        this.account = account;
    }
    public String getDetail() {
        return Detail;
    }
    public void setDetail(String detail) {
        Detail = detail;
    }
    public Boolean getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(Boolean creditCard) {
        this.creditCard = creditCard;
    }

}
