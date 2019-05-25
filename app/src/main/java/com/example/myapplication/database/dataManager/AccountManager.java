package com.example.myapplication.database.dataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.AccountPojo;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.database.DataBaseContract.AccountEntry;

public class AccountManager {


    public AccountManager() {
    }


    public List<AccountPojo> getAccounts(OpenHelper dbHelper) {
        List<AccountPojo> accounts;
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

        accounts = loadAccounts(accountCur);

        return accounts;
    }


    private List<AccountPojo> loadAccounts(Cursor cursor) {
        List<AccountPojo> accounts = new ArrayList<>();

        int accIdPos = cursor.getColumnIndex(AccountEntry._ID);
        int accNamePos = cursor.getColumnIndex(AccountEntry.COLUMN_NAME);
        int accDescriptionPos = cursor.getColumnIndex(AccountEntry.COLUMN_DESCRIPTION);
        int accBalancePos = cursor.getColumnIndex(AccountEntry.COLUMN_BALANCE);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(accIdPos);
            String name = cursor.getString(accNamePos);
            String description = cursor.getString(accDescriptionPos);
            Float balance = cursor.getFloat(accBalancePos);

            AccountPojo account = new AccountPojo(id, name, description, balance);
            accounts.add(account);
        }

        cursor.close();

        return accounts;
    }


    public AccountPojo findById(OpenHelper dbHelper, Long id) {
        AccountPojo account;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = AccountEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        String[] columns = {AccountEntry._ID,
                            AccountEntry.COLUMN_NAME,
                            AccountEntry.COLUMN_DESCRIPTION,
                            AccountEntry.COLUMN_BALANCE};

        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                                     columns,
                                     selection,
                                     selectionArgs,
                                     null,
                                     null,
                                     accountOrderBy);

        account = loadAccounts(accountCur).get(0);

        return account;
    }


    public AccountPojo findByName(OpenHelper dbHelper, String name) {
        AccountPojo account;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = AccountEntry.COLUMN_NAME + " = ?";
        String[] selectionArgs = {name};

        String[] columns = {AccountEntry._ID,
                            AccountEntry.COLUMN_NAME,
                            AccountEntry.COLUMN_DESCRIPTION,
                            AccountEntry.COLUMN_BALANCE};

        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                                     columns,
                                     selection,
                                     selectionArgs,
                                     null,
                                     null,
                                     accountOrderBy);

        account = loadAccounts(accountCur).get(0);

        return account;
    }


    public void insertAccount(OpenHelper dbHelper, AccountPojo account) throws IllegalArgumentException {

        Float balance;

        if (account.getName() == null || account.getName().isEmpty() || account.getName().trim() == "")
            throw new IllegalArgumentException("The name of the account cannot be empty");

        if (account.getBalance() == null)
            balance = 0f;
        else
            balance = account.getBalance();

        ContentValues values = new ContentValues();
        values.put(AccountEntry._ID, "");
        values.put(AccountEntry.COLUMN_NAME, account.getName());
        values.put(AccountEntry.COLUMN_DESCRIPTION, account.getDescription());
        values.put(AccountEntry.COLUMN_BALANCE, balance);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(AccountEntry.TABLE_NAME, null, values);
    }


    public void updateAccount(OpenHelper dbHelper, AccountPojo account, int accountId) {
        String selection = AccountEntry._ID + " = ?";
        String[] selectionArgs = {Integer.toString(accountId)};

        ContentValues values = new ContentValues();
        values.put(AccountEntry._ID, accountId);
        values.put(AccountEntry.COLUMN_NAME, account.getName());
        values.put(AccountEntry.COLUMN_DESCRIPTION, account.getDescription());
        values.put(AccountEntry.COLUMN_BALANCE, account.getBalance());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(AccountEntry.TABLE_NAME, values, selection, selectionArgs);
    }

}
