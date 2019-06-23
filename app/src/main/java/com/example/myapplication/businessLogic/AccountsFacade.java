package com.example.myapplication.businessLogic;

import android.content.Context;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dataManager.AccountManager;
import com.example.myapplication.view.pojo.AccountPojo;

import java.util.ArrayList;
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

    public ArrayList<PersistentDataModel> getBankAccountsSpinnerModel(Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);
        AccountManager am = new AccountManager();
        ArrayList<PersistentDataModel> result = buildDataModel(am.getBankAccounts(oh), AccountPojo.class);
        oh.close();
        return result;
    }

    public ArrayList<PersistentDataModel> getCreditCardAccountSpinnerModel(Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);
        AccountManager am = new AccountManager();
        ArrayList<PersistentDataModel> result = buildDataModel(am.getCreditCardAccounts(oh), AccountPojo.class);
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

        return am.getAccounts(oh);

    }

    public List<AccountPojo> getAllAccountsAndCards(Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);

        AccountManager am = new AccountManager();

        List<AccountPojo> result = am.getAccounts(oh);

        oh.close();

        return result;
    }

    public void deleteAccount(Context ctx, Long id){
        AccountManager am = new AccountManager();
        OpenHelper oh = new OpenHelper(ctx);

        am.deleteAccount(oh,id);
        oh.close();
        setChanged();
        notifyObservers();

    }

    public void updateAccount(Context ctx, AccountPojo acc, Long id){

        AccountManager am = new AccountManager();
        OpenHelper oh = new OpenHelper(ctx);

        am.updateAccount(oh, acc, id);
        oh.close();
        setChanged();
        notifyObservers();

    }
}
