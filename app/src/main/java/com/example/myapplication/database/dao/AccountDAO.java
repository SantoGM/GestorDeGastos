package com.example.myapplication.database.dao;

import java.util.List;

public class AccountDAO extends AbstractDAO {

    private String name;
    private Float balance;
    private List<MovementDAO> movements;


    public AccountDAO(Long id, String name, Float balance, List<MovementDAO> movements) {
        this.setId(id);
        this.name = name;
        this.balance = balance;
        this.movements = movements;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }
    public List<MovementDAO> getMovements() {
        return movements;
    }
    public void setMovements(List<MovementDAO> movements) {
        this.movements = movements;
    }

}
