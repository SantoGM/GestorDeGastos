package com.example.myapplication.database.dao;

import java.util.Date;
import java.util.List;

public class CreditCardDAO extends AccountDAO {

    private Date closingDate;
    private Date expirationDate;


    public CreditCardDAO(Long id, String name, Float balance, String description, Date closingDate, Date expirationDate) {
        super(id, name, description, balance);
        this.closingDate = closingDate;
        this.expirationDate = expirationDate;
    }


    public Date getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
