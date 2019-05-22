package com.example.myapplication.businessLogic;

import com.example.myapplication.view.pojo.PaymenyPojo;

import java.util.Date;

public class ExpenseFacade extends AbstractFacade {

    private static ExpenseFacade instance;

    private ExpenseFacade(){
        // Singleton
    }

    public static ExpenseFacade getInstance() {
        if (instance == null) {
            instance = new ExpenseFacade();
        }
        return instance;
    }

    public void saveExpense(Date date, Float amount, Long categoryID, Long accountID, String detail){
        // Get Cateogry by ID
        // Get Account by ID
        // Obtain account Type
        PaymenyPojo paymentToSave = new PaymenyPojo(null, date, amount, , , detail, );
    }
}
