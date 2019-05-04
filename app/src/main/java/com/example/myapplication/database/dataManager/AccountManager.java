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


    public List<AccountDAO> getAccounts() {
        return this.accounts;
    }


    public static void loadFromDB(OpenHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {AccountEntry._ID,
                            AccountEntry.COLUMN_NAME,
                            AccountEntry.COLUMN_DESCRIPTION,
                            AccountEntry.COLUMN_BALANCE};
        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                                     columns,
                                     null,
                                     null,
                                     null,
                                     null,
                                     accountOrderBy);

        loadAccounts(accountCur);
    }


    private static void loadAccounts(Cursor cursor) {
        int accIdPos = cursor.getColumnIndex(AccountEntry._ID);
        int accNamePos = cursor.getColumnIndex(AccountEntry.COLUMN_NAME);
        int accDescriptionPos = cursor.getColumnIndex(AccountEntry.COLUMN_DESCRIPTION);
        int accBalancePos = cursor.getColumnIndex(AccountEntry.COLUMN_BALANCE);

        AccountManager am = getInstance();
        am.accounts.clear();

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(accIdPos);
            String name = cursor.getString(accNamePos);
            String description = cursor.getString(accDescriptionPos);
            Float balance = cursor.getFloat(accBalancePos);

            AccountDAO accountDAO = new AccountDAO(id, name, description, balance);
            am.accounts.add(accountDAO);
        }

        cursor.close();
    }


    public AccountDAO findById(Long id) {
        for (AccountDAO account : accounts) {
            if (id.equals(account.getId()))  //TODO chk if equals works or we should change it to ==
                return account;
        }
        return null;
    }


    public AccountDAO findByName(String name) {
        for (AccountDAO account : accounts) {
            if (name.equals(account.getName()))
                return account;
        }
        return null;
    }

}
