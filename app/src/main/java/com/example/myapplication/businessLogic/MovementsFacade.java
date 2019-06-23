package com.example.myapplication.businessLogic;

import android.content.Context;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dataManager.AccountManager;
import com.example.myapplication.database.dataManager.CategoryManager;
import com.example.myapplication.database.dataManager.MovementManager;
import com.example.myapplication.view.pojo.AccountPojo;
import com.example.myapplication.view.pojo.CategoryPojo;
import com.example.myapplication.view.pojo.PaymentPojo;
import com.example.myapplication.view.pojo.TransferencePojo;

import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
public class MovementsFacade extends AbstractFacade {

    private static MovementsFacade instance;

    private MovementsFacade(){
        // Singleton
    }

    public static MovementsFacade getInstance() {
        if (instance == null) {
            instance = new MovementsFacade();
        }
        return instance;
    }

    public void saveExpense(Date date, Float amount, Long categoryID, Long accountID, String detail, Context ctx) throws IllegalArgumentException{
        CategoryManager cm = new CategoryManager();
        AccountManager am = new AccountManager();
        OpenHelper oh = new OpenHelper(ctx);

        CategoryPojo category = cm.findById(oh, categoryID);
        AccountPojo account = am.findById(oh, accountID);

        PaymentPojo paymentToSave = new PaymentPojo(null, date, amount, category, account, detail, Boolean.FALSE);

        MovementManager mm  = new MovementManager();
        mm.insertPayment(oh, paymentToSave);
        oh.close();
        setChanged();
        notifyObservers();
    }

    public List<PaymentPojo> obtainLastFiveMovementsByAccount(Context ctx, Long accountID) {
        OpenHelper oh = new OpenHelper(ctx);
        MovementManager mm = new MovementManager();
        List<PaymentPojo> result = mm.getAllPaymentsByAccount(oh, new Date(0, 0, 1), new Date(3000-1900, 11, 31), accountID);
        if (result.size()>5){
            result = result.subList(0, 5);
        }
        oh.close();
        return result;
    }

    public List<PaymentPojo> getExpensesBetween(Date since, Date until, Context ctx) {

        OpenHelper oh = new OpenHelper(ctx);
        MovementManager mmg = new MovementManager();

        List<PaymentPojo> payments = mmg.getAllExpenses(oh, since, until);

        oh.close();

        return payments;
    }


    public List<PaymentPojo> getExpensesBetweenAndCats(List<String> cats,Date since,  Date until, Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);
        MovementManager mmg = new MovementManager();

        List<PaymentPojo> payments = mmg.getAllExpensesFilterCat(oh,cats, since, until);

        oh.close();

        return payments;
    }

    public void saveTransfer(Date date, Long originID, Long destinationID, Float amount, String detail, Context ctx) {
        AccountManager am = new AccountManager();
        OpenHelper oh = new OpenHelper(ctx);

        AccountPojo originAccount = am.findById(oh, originID);
        AccountPojo destinationAccount = am.findById(oh, destinationID);

        TransferencePojo transferToSave = new TransferencePojo(null, date, amount, originAccount, destinationAccount, detail);

        MovementManager mm  = new MovementManager();
        mm.insertTransference(oh, transferToSave);
        oh.close();
        setChanged();
    }
}
