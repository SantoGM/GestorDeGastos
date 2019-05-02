package com.example.myapplication.database.dataManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dao.AccountDAO;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.database.DataBaseContract.AccountEntry;

public class AccountManager {

    private static AccountManager accountManager = null;

    private List<AccountDAO> accounts;


    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (accountManager == null) {
            accountManager = new AccountManager();
            accountManager.accounts = new ArrayList<>();
        }
        return accountManager;
    }


    public List<AccountDAO> getAll(OpenHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {AccountEntry._ID,
                            AccountEntry.COLUMN_NAME,
                            AccountEntry.COLUMN_DESCRIPTION,
                            AccountEntry.COLUMN_BALANCE};

        Cursor Accoutncur = db.query(AccountEntry.TABLE_NAME,
                                     columns,
                            null,
                         null,
                            null,
                              null,
                            AccountEntry.COLUMN_NAME + " DESC");

        Accoutncur.close();/*TODO Delete this line and close the cursor properly when it is needed*/

        return new ArrayList<AccountDAO>(); /*TODO put the proper return*/
    }

}
