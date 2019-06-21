package com.example.myapplication.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DataWorker {
    private final SQLiteDatabase mDb;

    public DataWorker(SQLiteDatabase db) {
        mDb = db;
    }

    public void insertAccounts() {
        insertAccount("Cuenta Base", "-",500.00);
    }

    private void insertAccount(String accName, String accDesc, Double accBalance) {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.AccountEntry.COLUMN_NAME, accName);
        values.put(DataBaseContract.AccountEntry.COLUMN_DESCRIPTION, accDesc);
        values.put(DataBaseContract.AccountEntry.COLUMN_BALANCE, accBalance);
        values.put(DataBaseContract.AccountEntry.COLUMN_DISABLE, 0);
        values.put(DataBaseContract.AccountEntry.TYPE, 0);
        mDb.insert(DataBaseContract.AccountEntry.TABLE_NAME, null, values);
    }

    private void insertUser() {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.UserDataEntry.COLUMN_NAME, "Santi");
        values.put(DataBaseContract.UserDataEntry.COLUMN_EMAIL, "san@san.com");
        values.put(DataBaseContract.UserDataEntry.COLUMN_PIN, "1234");

        long newRowId = mDb.insert(DataBaseContract.UserDataEntry.TABLE_NAME, null, values);
    }

    public void insertCategory() {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.CategoryEntry.COLUMN_NAME, "Servicios");
        values.put(DataBaseContract.CategoryEntry.COLUMN_DESCRIPTION, "Luz, gas, cable, etc.");

        mDb.insert(DataBaseContract.CategoryEntry.TABLE_NAME, null, values);

        values.put(DataBaseContract.CategoryEntry.COLUMN_NAME, "Morfi");
        values.put(DataBaseContract.CategoryEntry.COLUMN_DESCRIPTION, "Almuerzo, Cena");

        mDb.insert(DataBaseContract.CategoryEntry.TABLE_NAME, null, values);
    }
}
