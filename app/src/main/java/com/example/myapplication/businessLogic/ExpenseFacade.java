package com.example.myapplication.businessLogic;

import android.content.Context;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dataManager.AccountManager;
import com.example.myapplication.database.dataManager.CategoryManager;
import com.example.myapplication.database.dataManager.MovementManager;
import com.example.myapplication.view.pojo.AccountPojo;
import com.example.myapplication.view.pojo.CategoryPojo;
import com.example.myapplication.view.pojo.PaymentPojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void saveExpense(Date date, Float amount, Long categoryID, Long accountID, String detail, Context ctx){
        CategoryManager cm = new CategoryManager();
        AccountManager am = new AccountManager();
        OpenHelper oh = new OpenHelper(ctx);

        CategoryPojo cateogry = cm.findById(oh, categoryID);
        AccountPojo account = am.findById(oh, accountID);

        PaymentPojo paymentToSave = new PaymentPojo(null, date, amount, cateogry, account, detail, Boolean.FALSE);

        MovementManager mm  = new MovementManager();
        mm.insertPayment(oh, paymentToSave);
        oh.close();
        setChanged();
        notifyObservers();
    }

    public ArrayList<PersistentDataModel> getExpensesListrModel(Context ctx) {
        ArrayList<PersistentDataModel> result = buildDataModel(obtainLastFiveMovements(ctx), PaymentPojo.class);
        return result;
    }

    public List<PaymentPojo> obtainLastFiveMovements(Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);
        MovementManager mm = new MovementManager();
        List<PaymentPojo> result = mm.getAllPayments(oh, new Date(0, 0, 1), new Date(2020-1900, 11, 31));
        if (result.size()>5){
            result = result.subList(0, 5);
        }
        oh.close();
        return result;
    }
}