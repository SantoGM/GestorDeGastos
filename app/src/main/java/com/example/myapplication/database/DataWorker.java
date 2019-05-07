package com.example.myapplication.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DataWorker {
    private SQLiteDatabase mDb;

    public DataWorker(SQLiteDatabase db) {
        mDb = db;
    }

    public void insertAccounts() {
        insertAccount("Cuenta Base", "-",0.00);
        insertUser();
    }

    private void insertAccount(String accName, String accDesc, Double accBalance) {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.AccountEntry.COLUMN_NAME, accName);
        values.put(DataBaseContract.AccountEntry.COLUMN_DESCRIPTION, accDesc);
        values.put(DataBaseContract.AccountEntry.COLUMN_BALANCE, accBalance);

        long newRowId = mDb.insert(DataBaseContract.AccountEntry.TABLE_NAME, null, values);
    }

    private void insertUser() {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.UserDataEntry.COLUMN_NAME, "Santi");
        values.put(DataBaseContract.UserDataEntry.COLUMN_EMAIL, "san@san.com");
        values.put(DataBaseContract.UserDataEntry.COLUMN_PIN, "1234");

        long newRowId = mDb.insert(DataBaseContract.UserDataEntry.TABLE_NAME, null, values);
    }
}
