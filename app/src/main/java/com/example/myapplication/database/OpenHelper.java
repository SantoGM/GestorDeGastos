package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GestorDeGastos";
    public static final int DATABASE_VERSION = 1;

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseContract.AccountEntry.SQL_CREATE_TABLE);
        db.execSQL(DataBaseContract.CategoryEntry.SQL_CREATE_TABLE);
        db.execSQL(DataBaseContract.PaymentEntry.SQL_CREATE_TABLE);
        db.execSQL(DataBaseContract.TransferenceEntry.SQL_CREATE_TABLE);
        db.execSQL(DataBaseContract.UserDataEntry.SQL_CREATE_TABLE);

        DataWorker dbw = new DataWorker(db);

        dbw.insertAccounts(this);
        dbw.insertCategories(this);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
