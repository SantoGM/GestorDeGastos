package com.example.myapplication.database.dao;

import java.util.Date;

public class MovementDAO extends AbstractDAO {

    private Date date;
    private Float amount;
    private CategoryDAO category;


    public MovementDAO(Long id, Date date, Float amount, CategoryDAO category) {
        this.setId(id);
        this.date = date;
        this.amount = amount;
        this.category = category;
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
    public CategoryDAO getCategory() {
        return category;
    }
    public void setCategory(CategoryDAO category) {
        this.category = category;
    }
    
}
