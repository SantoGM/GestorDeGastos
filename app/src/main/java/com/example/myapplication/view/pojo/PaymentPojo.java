package com.example.myapplication.view.pojo;

import com.example.myapplication.view.extras.DateHelper;

import java.util.Date;

public class PaymentPojo extends AbstractPojo implements MovementPojo {

    private Date date;
    private Float amount;
    private CategoryPojo category;
    private AccountPojo account;
    private String detail;
    private Boolean creditCard;


    public PaymentPojo() {
    }

    public PaymentPojo(Long id, Date date, Float amount, CategoryPojo category, AccountPojo account, String detail, Boolean creditCard) {
        this.setId(id);
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.detail = detail;
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
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Boolean getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(Boolean creditCard) {
        this.creditCard = creditCard;
    }


    @Override
    public String[] showDetails() {
        return new String[]{String.valueOf(this.getId()),
                            String.valueOf(this.date),
                            String.valueOf(this.amount),
                            this.category.getName(),
                            this.account.getName(),
                            this.detail,
                            String.valueOf(this.creditCard)};
    }

    @Override
    public String nameToShow() {
        return DateHelper.toStringDDMMYYYY(getDate()) + "- $ " + getAmount() + "(" + getAccount().getName() + ")";
    }
}
