package com.example.myapplication.database.dataManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DataBaseContract.PaymentEntry;
import com.example.myapplication.database.DataBaseContract.TransferenceEntry;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.AccountPojo;
import com.example.myapplication.view.pojo.CategoryPojo;
import com.example.myapplication.view.pojo.PaymenyPojo;
import com.example.myapplication.view.pojo.TransferemcePojo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovementManager {

    private static MovementManager movementManager = null;


    private MovementManager() {}

    public static MovementManager getInstance() {
        if (movementManager == null) {
            movementManager = new MovementManager();
        }
        return movementManager;
    }


    public List<PaymenyPojo> getAllPayments(OpenHelper dbHelper) {
        List<PaymenyPojo> payments;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {PaymentEntry._ID,
                            PaymentEntry.COLUMN_DATE,
                            PaymentEntry.COLUMN_AMOUNT,
                            PaymentEntry.COLUMN_ID_CATEGORY,
                            PaymentEntry.COLUMN_ID_ACCOUNT,
                            PaymentEntry.COLUMN_DESCRIPTION,
                            PaymentEntry.COLUMN_IS_CREDIT_CARD};
        String paymentOrderBy = PaymentEntry.COLUMN_DATE + " ASC";

        Cursor paymentCur = db.query(PaymentEntry.TABLE_NAME,
                                     columns,
                                     null,
                                     null,
                                     null,
                                     null,
                                      paymentOrderBy);

        payments = loadPayments(paymentCur);

        return payments;

    }


    private List<PaymenyPojo> loadPayments(Cursor cursor) {
        CategoryManager cm = CategoryManager.getInstance();
        AccountManager am = AccountManager.getInstance();

        List<PaymenyPojo> payments = new ArrayList<>();

        int payIdPos = cursor.getColumnIndex(PaymentEntry._ID);
        int payDatePos = cursor.getColumnIndex(PaymentEntry.COLUMN_DATE);
        int payAmountPos = cursor.getColumnIndex(PaymentEntry.COLUMN_AMOUNT);
        int payCategoryPos = cursor.getColumnIndex(PaymentEntry.COLUMN_ID_CATEGORY);
        int payAccountPos = cursor.getColumnIndex(PaymentEntry.COLUMN_ID_ACCOUNT);
        int payDescriptionPos = cursor.getColumnIndex(PaymentEntry.COLUMN_DESCRIPTION);
        int payCCPos = cursor.getColumnIndex(PaymentEntry.COLUMN_IS_CREDIT_CARD);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(payIdPos);
            String dateString = cursor.getString(payDatePos);
            Float amount = cursor.getFloat(payAmountPos);
            Long categoryId = cursor.getLong(payCategoryPos);
            Long accountId = cursor.getLong(payAccountPos);
            String description = cursor.getString(payDescriptionPos);
            Integer creditCardInt = cursor.getInt(payCCPos);

            Date date = stringToDate(dateString);
            CategoryPojo category = cm.findById(categoryId);
            AccountPojo account = am.findById(accountId);
            Boolean creditCard = intToBoolean(creditCardInt);

            PaymenyPojo payment = new PaymenyPojo(id, date, amount, category, account, description, creditCard);

            payments.add(payment);
        }

        cursor.close();

        return payments;
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


    private Boolean intToBoolean(Integer creditCardInt) {
        return creditCardInt > 0;
    }


    public List<TransferemcePojo> getAllTransferences(OpenHelper dbHelper) {
        List<TransferemcePojo> transferences;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {TransferenceEntry._ID,
                            TransferenceEntry.COLUMN_DATE,
                            TransferenceEntry.COLUMN_AMOUNT,
                            TransferenceEntry.COLUMN_ID_ACCOUNT_ORG,
                            TransferenceEntry.COLUMN_ID_ACCOUNT_DEST,
                            TransferenceEntry.COLUMN_DESCRIPTION};
        String transferenceOrderBy = TransferenceEntry.COLUMN_DATE + " ASC";

        Cursor transferenceCur = db.query(TransferenceEntry.TABLE_NAME,
                                          columns,
                                          null,
                                          null,
                                          null,
                                          null,
                                          transferenceOrderBy);

        transferences = loadTransferences(transferenceCur);

        return transferences;
    }

    private List<TransferemcePojo> loadTransferences(Cursor cursor) {
        AccountManager am = AccountManager.getInstance();

        List<TransferemcePojo> transferences = new ArrayList<>();

        int transIdPos = cursor.getColumnIndex(TransferenceEntry._ID);
        int transDatePos = cursor.getColumnIndex(TransferenceEntry.COLUMN_DATE);
        int transAmountPos = cursor.getColumnIndex(TransferenceEntry.COLUMN_AMOUNT);
        int transAccountOrgPos = cursor.getColumnIndex(TransferenceEntry.COLUMN_ID_ACCOUNT_ORG);
        int transAccountDestPos = cursor.getColumnIndex(TransferenceEntry.COLUMN_ID_ACCOUNT_DEST);
        int transDescriptionPos = cursor.getColumnIndex(TransferenceEntry.COLUMN_DESCRIPTION);
        
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(transIdPos);
            String dateString = cursor.getString(transDatePos);
            Float amount = cursor.getFloat(transAmountPos);
            Long accountOrgId = cursor.getLong(transAccountOrgPos);
            Long accountDestId = cursor.getLong(transAccountDestPos);
            String description = cursor.getString(transDescriptionPos);

            Date date = stringToDate(dateString);
            AccountPojo accountOrg = am.findById(accountOrgId);
            AccountPojo accountDest = am.findById(accountDestId);

            TransferemcePojo transference = new TransferemcePojo(id, date, amount, accountOrg, accountDest, description);

            transferences.add(transference);
        }

        cursor.close();

        return transferences;
    }


}
