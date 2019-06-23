package com.example.myapplication.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.StringPrepParseException;

import com.example.myapplication.database.dataManager.AccountManager;
import com.example.myapplication.database.dataManager.CategoryManager;
import com.example.myapplication.database.dataManager.MovementManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.myapplication.database.DataBaseContract.AccountEntry;
import static com.example.myapplication.database.DataBaseContract.CategoryEntry;
import static com.example.myapplication.database.DataBaseContract.PaymentEntry;

public class DataWorker {

    private final int DISABLE = 1;
    private final int ENABLE  = 0;
    private final int BANK_ACCOUNT = 0;
    private final int CREDIT_CARD_ACCOUNT = 1;

    private SQLiteDatabase mDb;
    private AccountManager am;
    private CategoryManager cm;
    private MovementManager mm;

    public DataWorker(SQLiteDatabase db) {
        this.mDb = db;
        this.am = new AccountManager();
        this.cm = new CategoryManager();
        this.mm = new MovementManager();
    }

    public void insertAccounts(OpenHelper dbHelper) {
        if (am.getAccounts(dbHelper).isEmpty()) {
            insertAccount("Credicop", BANK_ACCOUNT, "Cuenta sueldo",30000.00);
            insertAccount("Caval", CREDIT_CARD_ACCOUNT, "",0.00);
        }
    }

    private void insertAccount(String accName, Integer accType, String accDesc, Double accBalance) {
        ContentValues values = new ContentValues();
        values.put(AccountEntry.COLUMN_NAME, accName);
        values.put(AccountEntry.COLUMN_TYPE, accType);
        values.put(AccountEntry.COLUMN_DESCRIPTION, accDesc);
        values.put(AccountEntry.COLUMN_BALANCE, accBalance);
        values.put(AccountEntry.COLUMN_DISABLE, ENABLE);

        mDb.insert(AccountEntry.TABLE_NAME, null, values);
    }

    public void insertCategories(OpenHelper dbHelper) {
        if (cm.getCategories(dbHelper).isEmpty()) {
            insertCategory("Luz", "Edesur");
            insertCategory("Bondi", "Transporte");
            insertCategory("Gas", "Metrogas");
            insertCategory("Ort", "Facultad");
            insertCategory("Almuerzo", "Comidas en el trabajo");
            insertCategory("Joda", "Salidas");
            insertCategory("Telefono", "Movistar");
        }
    }


    private void insertCategory(String name, String description) {
        ContentValues values = new ContentValues();
        values.put(CategoryEntry.COLUMN_NAME, name);
        values.put(CategoryEntry.COLUMN_DESCRIPTION, description);
        values.put(CategoryEntry.COLUMN_DISABLE, ENABLE);

        mDb.insert(CategoryEntry.TABLE_NAME, null, values);
    }


    public void insertPayments(OpenHelper dbHelper){

        //Luz
        insertPayment(stringToDate("2019-03-11"), 400f, 1, 1, "Pago Edesur", 0);
        insertPayment(stringToDate("2019-04-08"), 450f, 1, 1, "Pago Edesur", 0);
        insertPayment(stringToDate("2019-05-06"), 475f, 1, 1, "Pago Edesur", 0);

        //Bondi
        insertPayment(stringToDate("2019-03-31"), 140f, 2, 1, "Carga SUBE", 0);
        insertPayment(stringToDate("2019-04-07"), 140f, 2, 1, "Carga SUBE", 0);
        insertPayment(stringToDate("2019-04-14"), 140f, 2, 1, "Carga SUBE", 0);
        insertPayment(stringToDate("2019-04-21"), 140f, 2, 1, "Carga SUBE", 0);
        insertPayment(stringToDate("2019-04-28"), 140f, 2, 1, "Carga SUBE", 0);
        insertPayment(stringToDate("2019-05-05"), 140f, 2, 1, "Carga SUBE", 0);
        insertPayment(stringToDate("2019-05-12"), 140f, 2, 1, "Carga SUBE", 0);
        insertPayment(stringToDate("2019-05-19"), 140f, 2, 1, "Carga SUBE", 0);
        insertPayment(stringToDate("2019-05-26"), 140f, 2, 1, "Carga SUBE", 0);

        //Gas
        insertPayment(stringToDate("2019-03-13"), 300f, 3, 1, "Pago MetroGas", 0);
        insertPayment(stringToDate("2019-04-11"), 345f, 3, 1, "Pago MetroGas", 0);
        insertPayment(stringToDate("2019-05-10"), 405f, 3, 1, "Pago MetroGas", 0);

        //ORT
        insertPayment(stringToDate("2019-03-01"), 6000f, 4, 1, "Pago Ort", 0);
        insertPayment(stringToDate("2019-04-01"), 6300f, 4, 1, "Pago Ort", 0);
        insertPayment(stringToDate("2019-05-01"), 6800f, 4, 1, "Pago Ort", 0);

        //Almuerzo
        insertPayment(stringToDate("2019-04-01"), 120f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-02"), 100f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-03"), 122f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-04"), 95f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-05"), 87f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-08"), 105f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-09"), 79f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-10"), 160f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-11"), 135f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-12"), 99f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-15"), 100f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-16"), 100f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-17"), 120f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-18"), 125f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-19"), 129f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-22"), 133f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-23"), 114f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-24"), 87f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-25"), 101f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-26"), 94f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-29"), 98f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-04-30"), 124f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-01"), 114f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-02"), 164f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-03"), 146f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-06"), 124f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-07"), 99f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-08"), 75f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-09"), 87f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-10"), 102f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-13"), 105f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-14"), 109f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-15"), 110f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-16"), 140f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-17"), 135f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-20"), 106f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-21"), 97f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-22"), 121f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-23"), 135f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-24"), 150f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-27"), 78f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-28"), 80f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-29"), 88f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-30"), 85f, 5, 1, "Comida", 0);
        insertPayment(stringToDate("2019-05-31"), 96f, 5, 1, "Comida", 0);

        //Joda
        insertPayment(stringToDate("2019-04-05"), 654f, 6, 1, "Bar", 0);
        insertPayment(stringToDate("2019-04-06"), 416f, 6, 1, "Bar", 0);
        insertPayment(stringToDate("2019-04-11"), 300f, 6, 1, "After", 0);
        insertPayment(stringToDate("2019-04-20"), 740f, 6, 1, "Boliche", 0);
        insertPayment(stringToDate("2019-04-27"), 788f, 6, 1, "Cena", 0);
        insertPayment(stringToDate("2019-05-03"), 500f, 6, 1, "Cine", 0);
        insertPayment(stringToDate("2019-05-04"), 465f, 6, 1, "Salida", 0);
        insertPayment(stringToDate("2019-05-08"), 465f, 6, 1, "Bar", 0);
        insertPayment(stringToDate("2019-05-12"), 321f, 6, 1, "Amigxs", 0);
        insertPayment(stringToDate("2019-05-17"), 150f, 6, 1, "Bar", 0);
        insertPayment(stringToDate("2019-05-18"), 150f, 6, 1, "Salida", 0);
        insertPayment(stringToDate("2019-05-31"), 200f, 6, 1, "Cine", 0);

        //Telefono
        insertPayment(stringToDate("2019-03-21"), 1000f, 7, 1, "Pago Movistar", 0);
        insertPayment(stringToDate("2019-04-21"), 800f, 7, 1, "Pago Movistar", 0);
        insertPayment(stringToDate("2019-05-21"), 400f, 7, 1, "Pago Tuenti", 0);
    }


    private void insertPayment(Date dateDate, Float amount, Integer categoryId, Integer accountId, String description, Integer creditCard){


        String dateString = dateToString(dateDate);
        //Integer creditCard = booleanToInt(payment.getCreditCard());

        ContentValues values = new ContentValues();
        values.put(PaymentEntry.COLUMN_DATE, dateString);
        values.put(PaymentEntry.COLUMN_AMOUNT, amount);
        values.put(PaymentEntry.COLUMN_ID_CATEGORY, categoryId);
        values.put(PaymentEntry.COLUMN_ID_ACCOUNT, accountId);
        values.put(PaymentEntry.COLUMN_DESCRIPTION, description);
        values.put(PaymentEntry.COLUMN_IS_CREDIT_CARD, creditCard);
        values.put(PaymentEntry.COLUMN_DISABLE, ENABLE);

        mDb.insert(PaymentEntry.TABLE_NAME, null, values);
    }


    private Date stringToDate(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(dateString);
        }
        catch (ParseException ex) {
            date = null;
        }
        return date;
    }

    private String dateToString(Date dateDate) {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String date = df.format(dateDate);
        return date;
    }



    private void insertUser() {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.UserDataEntry.COLUMN_NAME, "Santi");
        values.put(DataBaseContract.UserDataEntry.COLUMN_EMAIL, "san@san.com");
        values.put(DataBaseContract.UserDataEntry.COLUMN_PIN, "1234");

        long newRowId = mDb.insert(DataBaseContract.UserDataEntry.TABLE_NAME, null, values);
    }
}
