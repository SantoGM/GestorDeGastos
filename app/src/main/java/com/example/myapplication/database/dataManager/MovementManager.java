package com.example.myapplication.database.dataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DataBaseContract.PaymentEntry;
import com.example.myapplication.database.DataBaseContract.TransferenceEntry;
import com.example.myapplication.database.DataBaseContract.CategoryEntry;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.AccountPojo;
import com.example.myapplication.view.pojo.CategoryPojo;
import com.example.myapplication.view.pojo.MovementPojo;
import com.example.myapplication.view.pojo.PaymentPojo;
import com.example.myapplication.view.pojo.TransferencePojo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MovementManager {

    private AccountManager am;
    private CategoryManager cm;


    public MovementManager() {
        am = new AccountManager();
        cm = new CategoryManager();
    }


    public PaymentPojo findPaymentById(OpenHelper dbHelper, Long id) {
        PaymentPojo payment;
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

        payment = loadPayments(dbHelper, paymentCur).get(0);

        return payment;
    }



    public List<PaymentPojo> getAllPayments(OpenHelper dbHelper, Date dateFrom, Date dateTo) {
        List<PaymentPojo> payments;
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
        String paymentOrderBy = PaymentEntry.COLUMN_DATE + " DESC";

        Cursor paymentCur = db.query(PaymentEntry.TABLE_NAME,
                                     columns,
                                     selection,
                                     selectionArgs,
                                     null,
                                     null,
                                      paymentOrderBy);

        payments = loadPayments(dbHelper, paymentCur);

        return payments;
    }


    private List<PaymentPojo> loadPayments(OpenHelper dbHelper, Cursor cursor) {
        List<PaymentPojo> payments = new ArrayList<>();

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
            CategoryPojo category = cm.findById(dbHelper, categoryId);
            AccountPojo account = am.findById(dbHelper, accountId);
            Boolean creditCard = intToBoolean(creditCardInt);

            PaymentPojo payment = new PaymentPojo(id, date, amount, category, account, description, creditCard);

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


    public TransferencePojo findTransferenceById(OpenHelper dbHelper, Long id) {
        TransferencePojo transference;
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

        transference = loadTransferences(dbHelper, transferenceCur).get(0);

        return transference;
    }


    public List<TransferencePojo> getAllTransferences(OpenHelper dbHelper, Date dateFrom, Date dateTo) {
        List<TransferencePojo> transferences;
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

        transferences = loadTransferences(dbHelper, transferenceCur);

        return transferences;
    }


    private List<TransferencePojo> loadTransferences(OpenHelper dbHelper, Cursor cursor) {
        List<TransferencePojo> transferences = new ArrayList<>();

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
            AccountPojo accountOrg = am.findById(dbHelper, accountOrgId);
            AccountPojo accountDest = am.findById(dbHelper, accountDestId);

            TransferencePojo transference = new TransferencePojo(id, date, amount, accountOrg, accountDest, description);

            transferences.add(transference);
        }

        cursor.close();

        return transferences;
    }


    public void insertPayment(OpenHelper dbHelper, PaymentPojo payment) {

        AccountPojo account;
        CategoryPojo category;

        if (payment.getDate() == null)
            throw new IllegalArgumentException("The date of the payment cannot be empty");

        if (payment.getAmount() == null | payment.getAmount() == 0)
            throw new IllegalArgumentException("The amount of the payment cannot be empty");

        if (payment.getAccount() == null)
            throw new IllegalArgumentException("The acount of the payment cannot be empty");

        if (payment.getCategory() == null)
            throw new IllegalArgumentException("The category of the payment cannot be empty");

        if (payment.getCreditCard() == null)
            throw new IllegalArgumentException("The credit card field of the payment cannot be empty");


        account = am.findById(dbHelper, payment.getAccount().getId());
        if (account == null)
            throw new IllegalArgumentException("The account does not exist");

        category = cm.findById(dbHelper, payment.getCategory().getId());
        if (category == null)
            throw new IllegalArgumentException("The category does not exist");


        String date = dateToString(payment.getDate());
        Integer creditCard = booleanToInt(payment.getCreditCard());

        ContentValues values = new ContentValues();
        values.put(PaymentEntry.COLUMN_DATE, date);
        values.put(PaymentEntry.COLUMN_AMOUNT, payment.getAmount());
        values.put(PaymentEntry.COLUMN_ID_CATEGORY, payment.getCategory().getId());
        values.put(PaymentEntry.COLUMN_ID_ACCOUNT, payment.getAccount().getId());
        values.put(PaymentEntry.COLUMN_DESCRIPTION, payment.getDetail());
        values.put(PaymentEntry.COLUMN_IS_CREDIT_CARD, creditCard);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Long result = db.insert(PaymentEntry.TABLE_NAME, null, values);
        db.close();
    }


    public void updatePayment(OpenHelper dbHelper, PaymentPojo payment, Long paymentId) {
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


    public void insertTransference(OpenHelper dbHelper, TransferencePojo transference) {

        AccountPojo account;

        if (transference.getDate() == null)
            throw new IllegalArgumentException("The date of the transference cannot be empty");

        if (transference.getAmount() == null | transference.getAmount() == 0)
            throw new IllegalArgumentException("The amount of the transference cannot be empty");

        if (transference.getAccountOrigin() == null)
            throw new IllegalArgumentException("The account origin of the transference cannot be empty");

        if (transference.getAccountDestiny() == null)
            throw new IllegalArgumentException("The account destiny of the transference cannot be empty");


        account = am.findById(dbHelper, transference.getAccountOrigin().getId());
        if (account == null)
            throw new IllegalArgumentException("The account origin does not exist");

        account = am.findById(dbHelper, transference.getAccountDestiny().getId());
        if (account == null)
            throw new IllegalArgumentException("The account destiny does not exist");


        String date = dateToString(transference.getDate());

        ContentValues values = new ContentValues();
        values.put(TransferenceEntry.COLUMN_DATE, date);
        values.put(TransferenceEntry.COLUMN_AMOUNT, transference.getAmount());
        values.put(TransferenceEntry.COLUMN_ID_ACCOUNT_ORG, transference.getAccountOrigin().getId());
        values.put(TransferenceEntry.COLUMN_ID_ACCOUNT_DEST, transference.getAccountDestiny().getId());
        values.put(TransferenceEntry.COLUMN_DESCRIPTION, transference.getDetail());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TransferenceEntry.TABLE_NAME, null, values);
    }


    public void updateTransference(OpenHelper dbHelper, TransferencePojo transference, Long transferenceId) {

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

        List<PaymentPojo> payments = getAllPayments(dbHelper, dateFrom, dateTo);
        List<TransferencePojo> transferences = getAllTransferences(dbHelper, dateFrom, dateTo);

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


    public List<PaymentPojo> getPaymentByCategoryName(OpenHelper dbHelper, String categoryName, Date dateFrom, Date dateTo) {
        List<PaymentPojo> payments;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String from = dateToString(dateFrom);
        String to   = dateToString(dateTo);

        String tablesWithJoin = PaymentEntry.TABLE_NAME
                              + " JOIN " + CategoryEntry.TABLE_NAME
                                         + " ON " + PaymentEntry.getQName(PaymentEntry.COLUMN_ID_CATEGORY)
                                                  + " = " + CategoryEntry.getQName(CategoryEntry._ID);

        String[] columns = {PaymentEntry.getQName(PaymentEntry._ID),
                            PaymentEntry.getQName(PaymentEntry.COLUMN_DATE),
                            PaymentEntry.getQName(PaymentEntry.COLUMN_AMOUNT),
                            PaymentEntry.getQName(PaymentEntry.COLUMN_ID_CATEGORY),
                            PaymentEntry.getQName(PaymentEntry.COLUMN_ID_ACCOUNT),
                            PaymentEntry.getQName(PaymentEntry.COLUMN_DESCRIPTION),
                            PaymentEntry.getQName(PaymentEntry.COLUMN_IS_CREDIT_CARD)};

        String selection = CategoryEntry.getQName(CategoryEntry.COLUMN_NAME) + " = ? "
                         + " and " + PaymentEntry.getQName(PaymentEntry.COLUMN_DATE) + " >= ? "
                         + " and " + PaymentEntry.getQName(PaymentEntry.COLUMN_DATE) + " <= ?";

        String[] selectionArgs = {categoryName, from, to};

        String paymentOrderBy = PaymentEntry.getQName(PaymentEntry.COLUMN_DATE) + " ASC";


        Cursor paymentCur = db.query(tablesWithJoin,
                                     columns,
                                     selection,
                                     selectionArgs,
                                     null,
                                     null,
                                     paymentOrderBy);

        payments = loadPayments(dbHelper, paymentCur);

        return payments;
    }


    public List<PaymentPojo> getPaymentByCategoryId(OpenHelper dbHelper, Long categoryId, Date dateFrom, Date dateTo) {
        List<PaymentPojo> payments;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String from = dateToString(dateFrom);
        String to   = dateToString(dateTo);

        String table = PaymentEntry.TABLE_NAME;

        String[] columns = {PaymentEntry._ID,
                            PaymentEntry.COLUMN_DATE,
                            PaymentEntry.COLUMN_AMOUNT,
                            PaymentEntry.COLUMN_ID_CATEGORY,
                            PaymentEntry.COLUMN_ID_ACCOUNT,
                            PaymentEntry.COLUMN_DESCRIPTION,
                            PaymentEntry.COLUMN_IS_CREDIT_CARD};

        String selection = PaymentEntry.COLUMN_ID_CATEGORY + " = ? "
                + " and " + PaymentEntry.COLUMN_DATE + " >= ? "
                + " and " + PaymentEntry.COLUMN_DATE + " <= ?";

        String[] selectionArgs = {String.valueOf(categoryId), from, to};

        String paymentOrderBy = PaymentEntry.COLUMN_DATE + " ASC";


        Cursor paymentCur = db.query(table,
                                     columns,
                                     selection,
                                     selectionArgs,
                                     null,
                                     null,
                                     paymentOrderBy);

        payments = loadPayments(dbHelper, paymentCur);

        return payments;
    }


    public List<PaymentPojo> getPaymentByCategoryId(OpenHelper dbHelper, Long categoryId) {
        List<PaymentPojo> payments;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String table = PaymentEntry.TABLE_NAME;

        String[] columns = {PaymentEntry._ID,
                            PaymentEntry.COLUMN_DATE,
                            PaymentEntry.COLUMN_AMOUNT,
                            PaymentEntry.COLUMN_ID_CATEGORY,
                            PaymentEntry.COLUMN_ID_ACCOUNT,
                            PaymentEntry.COLUMN_DESCRIPTION,
                            PaymentEntry.COLUMN_IS_CREDIT_CARD};

        String selection = PaymentEntry.COLUMN_ID_CATEGORY + " = ?";

        String[] selectionArgs = {String.valueOf(categoryId)};

        String paymentOrderBy = PaymentEntry.COLUMN_DATE + " ASC";


        Cursor paymentCur = db.query(table,
                                     columns,
                                     selection,
                                     selectionArgs,
                                     null,
                                     null,
                                     paymentOrderBy);

        payments = loadPayments(dbHelper, paymentCur);

        return payments;
    }


}
