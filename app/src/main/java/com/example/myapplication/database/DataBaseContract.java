package com.example.myapplication.database;

import android.provider.BaseColumns;

public final class DataBaseContract {

    private DataBaseContract() {}


    public static final class AccountEntry implements BaseColumns {
        public static final String TABLE_NAME = "accounts";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_BALANCE = "balance";
        public static final String COLUMN_DISABLE = "disable";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        COLUMN_TYPE + " INTEGER NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_BALANCE + " REAL NOT NULL, " +
                        COLUMN_DISABLE + " INTEGER DEFAULT 0 )";

        public static final String getQName(String columnName) {
            return TABLE_NAME + "." + columnName;
        }
    }


    public static final class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "cateogory";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DISABLE = "disable";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE  IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_DISABLE + " INTEGER DEFAULT 0 )";

        public static final String getQName(String columnName) {
            return TABLE_NAME + "." + columnName;
        }
    }


    public static final class PaymentEntry implements BaseColumns {
        public static final String TABLE_NAME = "payments";

        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_ID_CATEGORY = "id_category";
        public static final String COLUMN_ID_ACCOUNT = "id_account";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IS_CREDIT_CARD = "is_credit_card";
        public static final String COLUMN_DISABLE = "disable";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE  IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_DATE + " TEXT NOT NULL, " +
                        COLUMN_AMOUNT + " REAL NOT NULL, " +
                        COLUMN_ID_CATEGORY + " INTEGER NOT NULL, " +
                        COLUMN_ID_ACCOUNT + " INTEGER NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_IS_CREDIT_CARD + " INTEGER NOT NULL, " +
                        COLUMN_DISABLE + " INTEGER DEFAULT 0 )";

        public static final String getQName(String columnName) {
            return TABLE_NAME + "." + columnName;
        }
    }


    public static final class TransferenceEntry implements BaseColumns {
        public static final String TABLE_NAME = "transferences";

        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_ID_ACCOUNT_ORG = "id_account_origin";
        public static final String COLUMN_ID_ACCOUNT_DEST = "id_account_destination";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DISABLE = "disable";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE  IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_DATE + " TEXT NOT NULL, " +
                        COLUMN_AMOUNT + " REAL NOT NULL, " +
                        COLUMN_ID_ACCOUNT_ORG + " INTEGER NOT NULL, " +
                        COLUMN_ID_ACCOUNT_DEST + " INTEGER NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_DISABLE + " INTEGER DEFAULT 0 )";

        public static final String getQName(String columnName) {
            return TABLE_NAME + "." + columnName;
        }
    }


    public static final class UserDataEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_data";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PIN = "pin";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_EMAIL + " TEXT NOT NULL, " +
                        COLUMN_PIN + " INTEGER NOT NULL )";

        public static final String getQName(String columnName) {
            return TABLE_NAME + "." + columnName;
        }
    }

}
