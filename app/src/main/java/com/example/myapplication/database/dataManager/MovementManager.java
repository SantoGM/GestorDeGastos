package com.example.myapplication.database.dataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DataBaseContract.PaymentEntry;
import com.example.myapplication.database.DataBaseContract.TransferenceEntry;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.AccountPojo;
import com.example.myapplication.view.pojo.CategoryPojo;
import com.example.myapplication.view.pojo.MovementPojo;
import com.example.myapplication.view.pojo.PaymenyPojo;
import com.example.myapplication.view.pojo.TransferemcePojo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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


    public PaymenyPojo findPaymentById(OpenHelper dbHelper, Long id) {
        PaymenyPojo payment;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = PaymentEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

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
                                     selection,
                                     selectionArgs,
                                     null,
                                     null,
                                     paymentOrderBy);

        payment = loadPayments(paymentCur).get(0);

        return payment;
    }



    public List<PaymenyPojo> getAllPayments(OpenHelper dbHelper, Date dateFrom, Date dateTo) {
        List<PaymenyPojo> payments;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String from = dateToString(dateFrom);
        String to   = dateToString(dateTo);

        String selection = PaymentEntry.COLUMN_DATE + " >= ? and " + PaymentEntry.COLUMN_DATE + " <= ?";
        String[] selectionArgs = {from, to};

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
                                     selection,
                                     selectionArgs,
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


    private String dateToString(Date dateDate) {
        String pattern = "yyyy-MM-dd";

        DateFormat df = new SimpleDateFormat(pattern);

        String date = df.format(dateDate);

        return date;
    }


    private Boolean intToBoolean(Integer creditCardInt) {
        return creditCardInt > 0;
    }


    private Integer booleanToInt(Boolean creditCardBool) {
        return creditCardBool ? 1 : 0;
    }


    public TransferemcePojo findTransferenceById(OpenHelper dbHelper, Long id) {
        TransferemcePojo transference;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = TransferenceEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        String[] columns = {TransferenceEntry._ID,
                            TransferenceEntry.COLUMN_DATE,
                            TransferenceEntry.COLUMN_AMOUNT,
                            TransferenceEntry.COLUMN_ID_ACCOUNT_ORG,
                            TransferenceEntry.COLUMN_ID_ACCOUNT_DEST,
                            TransferenceEntry.COLUMN_DESCRIPTION};
        String transferenceOrderBy = TransferenceEntry.COLUMN_DATE + " ASC";

        Cursor transferenceCur = db.query(TransferenceEntry.TABLE_NAME,
                                          columns,
                                          selection,
                                          selectionArgs,
                                          null,
                                          null,
                                          transferenceOrderBy);

        transference = loadTransferences(transferenceCur).get(0);

        return transference;
    }


    public List<TransferemcePojo> getAllTransferences(OpenHelper dbHelper, Date dateFrom, Date dateTo) {
        List<TransferemcePojo> transferences;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String from = dateToString(dateFrom);
        String to   = dateToString(dateTo);

        String selection = TransferenceEntry.COLUMN_DATE + " >= ? and " + TransferenceEntry.COLUMN_DATE + " <= ?";
        String[] selectionArgs = {from, to};

        String[] columns = {TransferenceEntry._ID,
                            TransferenceEntry.COLUMN_DATE,
                            TransferenceEntry.COLUMN_AMOUNT,
                            TransferenceEntry.COLUMN_ID_ACCOUNT_ORG,
                            TransferenceEntry.COLUMN_ID_ACCOUNT_DEST,
                            TransferenceEntry.COLUMN_DESCRIPTION};
        String transferenceOrderBy = TransferenceEntry.COLUMN_DATE + " ASC";

        Cursor transferenceCur = db.query(TransferenceEntry.TABLE_NAME,
                                          columns,
                                          selection,
                                          selectionArgs,
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


    public void insertPayment(OpenHelper dbHelper, PaymenyPojo payment) {
        String date = dateToString(payment.getDate());
        Integer creditCard = booleanToInt(payment.getCreditCard());

        ContentValues values = new ContentValues();
        values.put(PaymentEntry._ID, "");
        values.put(PaymentEntry.COLUMN_DATE, date);
        values.put(PaymentEntry.COLUMN_AMOUNT, payment.getAmount());
        values.put(PaymentEntry.COLUMN_ID_CATEGORY, payment.getCategory().getId());
        values.put(PaymentEntry.COLUMN_ID_ACCOUNT, payment.getAccount().getId());
        values.put(PaymentEntry.COLUMN_DESCRIPTION, payment.getDetail());
        values.put(PaymentEntry.COLUMN_IS_CREDIT_CARD, creditCard);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(PaymentEntry.TABLE_NAME, null, values);
    }


    public void updatePayment(OpenHelper dbHelper, PaymenyPojo payment, Long paymentId) {
        CategoryManager cm = CategoryManager.getInstance();
        AccountManager am = AccountManager.getInstance();

        String selection = PaymentEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(paymentId)};

        String date = dateToString(payment.getDate());
        Integer creditCard = booleanToInt(payment.getCreditCard());

        ContentValues values = new ContentValues();
        values.put(PaymentEntry._ID, paymentId);
        values.put(PaymentEntry.COLUMN_DATE, date);
        values.put(PaymentEntry.COLUMN_AMOUNT, payment.getAmount());
        values.put(PaymentEntry.COLUMN_ID_CATEGORY, payment.getCategory().getId());
        values.put(PaymentEntry.COLUMN_ID_ACCOUNT, payment.getAccount().getId());
        values.put(PaymentEntry.COLUMN_DESCRIPTION, payment.getDetail());
        values.put(PaymentEntry.COLUMN_IS_CREDIT_CARD, creditCard);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(PaymentEntry.TABLE_NAME, values, selection, selectionArgs);
    }


    public void insertTransference(OpenHelper dbHelper, TransferemcePojo transference) {
        String date = dateToString(transference.getDate());

        ContentValues values = new ContentValues();
        values.put(TransferenceEntry._ID, "");
        values.put(TransferenceEntry.COLUMN_DATE, date);
        values.put(TransferenceEntry.COLUMN_AMOUNT, transference.getAmount());
        values.put(TransferenceEntry.COLUMN_ID_ACCOUNT_ORG, transference.getAccountOrigin().getId());
        values.put(TransferenceEntry.COLUMN_ID_ACCOUNT_DEST, transference.getAccountDestiny().getId());
        values.put(TransferenceEntry.COLUMN_DESCRIPTION, transference.getDetail());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TransferenceEntry.TABLE_NAME, null, values);
    }


    public void updateTransference(OpenHelper dbHelper, TransferemcePojo transference, Long transferenceId) {
        AccountManager am = AccountManager.getInstance();

        String selection = TransferenceEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(transferenceId)};

        String date = dateToString(transference.getDate());

        ContentValues values = new ContentValues();
        values.put(TransferenceEntry._ID, transferenceId);
        values.put(TransferenceEntry.COLUMN_DATE, date);
        values.put(TransferenceEntry.COLUMN_AMOUNT, transference.getAmount());
        values.put(TransferenceEntry.COLUMN_ID_ACCOUNT_ORG, transference.getAccountOrigin().getId());
        values.put(TransferenceEntry.COLUMN_ID_ACCOUNT_DEST, transference.getAccountDestiny().getId());
        values.put(TransferenceEntry.COLUMN_DESCRIPTION, transference.getDetail());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(TransferenceEntry.TABLE_NAME, values, selection, selectionArgs);
    }


    public List<MovementPojo> getAllMovements(OpenHelper dbHelper, Date dateFrom, Date dateTo) {

        Comparator<MovementPojo> comparator = new Comparator<MovementPojo>() {
            @Override
            public int compare(MovementPojo o1, MovementPojo o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        };

        List<PaymenyPojo> payments = getAllPayments(dbHelper, dateFrom, dateTo);
        List<TransferemcePojo> transferences = getAllTransferences(dbHelper, dateFrom, dateTo);

        List<MovementPojo> movements = new ArrayList<>();

        movements.addAll(payments);
        movements.addAll(transferences);

        Collections.sort(movements, comparator);

        /*
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String from = dateToString(dateFrom);
        String to   = dateToString(dateTo);

        String query = " select ID, DATE, AMOUNT "
                     + " from ("
                     + "     select "
                     +           PaymentEntry._ID + " as ID, "
                     +           PaymentEntry.COLUMN_DATE + " as DATE, "
                     +           PaymentEntry.COLUMN_AMOUNT + " as AMOUNT "
                     + "     from "
                     +           PaymentEntry.TABLE_NAME
                     + "     where "
                     +           PaymentEntry.COLUMN_DATE + " between ? and ? "
                     + "     union "
                     + "     select "
                     +           TransferenceEntry._ID + " as ID, "
                     +           TransferenceEntry.COLUMN_DATE + " as DATE, "
                     +           TransferenceEntry.COLUMN_AMOUNT + " as AMOUNT "
                     + "     from "
                     +           TransferenceEntry.TABLE_NAME
                     + "     where "
                     +           TransferenceEntry.COLUMN_DATE + " between ? and ? "
                     + "       ) "
                     + " order by DATE asc";

        String[] selectionArgs = {from,
                                  to,
                                  from,
                                  to};

        Cursor movementsCur = db.rawQuery(query, selectionArgs);

        movements = loadMovements(movementsCur);
        */

        return movements;
    }


}
