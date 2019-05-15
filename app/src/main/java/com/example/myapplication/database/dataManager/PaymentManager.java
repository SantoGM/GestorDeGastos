package com.example.myapplication.database.dataManager;

import java.util.Date;

public class PaymentManager {

    private static PaymentManager paymentManager = null;

    private Long id;
    private Date date;
    private Float amount;
    private CategoryManager category;
    private AccountManager account;
    private String description;
    private Boolean creditCard;


    private PaymentManager() {
    }

    public static PaymentManager getInstance() {
        if (paymentManager == null) {
            paymentManager = new PaymentManager();
        }
        return paymentManager;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public CategoryManager getCategory() {
        return category;
    }
    public void setCategory(CategoryManager category) {
        this.category = category;
    }
    public AccountManager getAccount() {
        return account;
    }
    public void setAccount(AccountManager account) {
        this.account = account;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(Boolean creditCard) {
        this.creditCard = creditCard;
    }

}
