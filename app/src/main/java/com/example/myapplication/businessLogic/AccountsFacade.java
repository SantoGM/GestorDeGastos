package com.example.myapplication.businessLogic;

import com.example.myapplication.view.pojo.AccountPojo;

import java.util.ArrayList;

public class AccountsFacade extends AbstractFacade{

    private static AccountsFacade instance;

    private AccountsFacade(){
        // Singleton
    }

    public static AccountsFacade getInstance(){
        if(instance == null){
               instance = new AccountsFacade();
        }
        return instance;
    }

    public ArrayList<PersistentDataModel> getAccountsSpinnerModel() {
        return buildDataModel(new String[] {"Cuenta BBVA", "Cuenta Santander", "Tarjeta MasterCard", "TarjetaVisa"}, AccountPojo.class);
    }
}
