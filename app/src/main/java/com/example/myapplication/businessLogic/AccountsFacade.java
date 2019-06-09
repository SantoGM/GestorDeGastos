package com.example.myapplication.businessLogic;

import android.content.Context;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dataManager.AccountManager;
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

    public ArrayList<PersistentDataModel> getAccountsSpinnerModel(Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);
        AccountManager am = new AccountManager();
        ArrayList<PersistentDataModel> result = buildDataModel(am.getAccounts(oh), AccountPojo.class);
        oh.close();
        return result;
    }
}
