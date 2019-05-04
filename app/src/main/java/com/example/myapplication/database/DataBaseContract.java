package com.example.myapplication.database;

import android.provider.BaseColumns;

public final class DataBaseContract {

    private DataBaseContract() {}

    public static final class AccountEntry implements BaseColumns {
        public static final String TABLE_NAME = "accounts";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_BALANCE = "balance";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_BALANCE + " REAL NOT NULL )";
    }

    public static final class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "cateogory";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT )";
    }

    public static final class PaymentEntry implements BaseColumns {
        public static final String TABLE_NAME = "accounts";

        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_ID_CATEGORY = "id_category";
        public static final String COLUMN_ID_ACCOUNT = "id_account";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IS_CREDIT_CARD = "is_credit_card";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_DATE + " TEXT NOT NULL, " +
                        COLUMN_AMOUNT + " REAL NOT NULL, " +
                        COLUMN_ID_CATEGORY + " INTEGER NOT NULL, " +
                        COLUMN_ID_ACCOUNT + " INTEGER NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_IS_CREDIT_CARD + " INTEGER NOT NULL )";
    }

    public static final class TransferenceEntry implements BaseColumns {
        public static final String TABLE_NAME = "transferences";

        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_ID_ACCOUNT_ORG = "id_account_origin";
        public static final String COLUMN_ID_ACCOUNT_DEST = "id_account_destination";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_DATE + " TEXT NOT NULL, " +
                        COLUMN_AMOUNT + " REAL NOT NULL, " +
                        COLUMN_ID_ACCOUNT_ORG + " INTEGER NOT NULL, " +
                        COLUMN_ID_ACCOUNT_DEST + " INTEGER NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT )";
    }

}
