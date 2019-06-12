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

    public ArrayList<PersistentDataModel> getAccountsSpinnerModel(Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);
        AccountManager am = new AccountManager();
        ArrayList<PersistentDataModel> result = buildDataModel(am.getAccounts(oh), AccountPojo.class);
        oh.close();
        return result;
    }

    public void saveAccount(String nameAccount, Integer type, String descAccount, Float balanceAccount, Context ctx){
        AccountManager am = new AccountManager();
        OpenHelper oh = new OpenHelper(ctx);

      //  AccountPojo account = am.findByName(oh, nameAccount);

        AccountPojo newAccount = new AccountPojo(null, nameAccount, type, descAccount, balanceAccount);

        am.insertAccount(oh, newAccount);
        oh.close();
        setChanged();
        notifyObservers();
    }

    public List<AccountPojo> loadAccounts(Context ctx){
        AccountManager am = new AccountManager();
        OpenHelper oh = new OpenHelper(ctx);

        List<AccountPojo> accounts = am.getAccounts(oh);

        return accounts;

    }
}
