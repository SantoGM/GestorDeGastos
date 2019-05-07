package com.example.myapplication.database.dataManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DataBaseContract.PaymentEntry;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.PaymenyPojo;

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
        List<PaymenyPojo> payments = new ArrayList<>();

        int payIdPos = cursor.getColumnIndex(PaymentEntry._ID);
        int payDatePos = cursor.getColumnIndex(PaymentEntry.COLUMN_DATE);
        int payAmountPos = cursor.getColumnIndex(PaymentEntry.COLUMN_AMOUNT);
        int payCategoryPos = cursor.getColumnIndex(PaymentEntry.COLUMN_ID_CATEGORY);
        int payAccountPos = cursor.getColumnIndex(PaymentEntry.COLUMN_ID_ACCOUNT);
        int payDescriptionPos = cursor.getColumnIndex(PaymentEntry.COLUMN_DESCRIPTION);
        int payCCPos = cursor.getColumnIndex(PaymentEntry.COLUMN_IS_CREDIT_CARD);

        //MovementManager mm = getInstance();

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(payIdPos);
            //Date date = cursor.get   // TODO finish with this - find the way to handle dates
                                       //
        }
        /*


        AccountManager am = getInstance();
        am.accounts.clear();

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(accIdPos);
            String name = cursor.getString(accNamePos);
            String description = cursor.getString(accDescriptionPos);
            Float balance = cursor.getFloat(accBalancePos);

            AccountPojo account = new AccountPojo(id, name, description, balance);
            am.accounts.add(account);
        }

*/
        cursor.close();


        return payments;

    }


}
