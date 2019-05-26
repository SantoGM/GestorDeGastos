package com.example.myapplication.database.dataManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DataBaseContract;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.UserDataPojo;

public class UserDataManager {

    private static UserDataManager instance;
    private static UserDataPojo userData;

    private UserDataManager(){
    }

    public static UserDataManager getInstance(){
        if (instance == null){
            instance = new UserDataManager();
        }
        return instance;
    }

    public UserDataPojo getUserData(Context ctx){

        OpenHelper oh = new OpenHelper(ctx);

        SQLiteDatabase db = oh.getReadableDatabase();
        String[] columns = {"ROWID",
                DataBaseContract.UserDataEntry.COLUMN_NAME,
                DataBaseContract.UserDataEntry.COLUMN_EMAIL,
                DataBaseContract.UserDataEntry.COLUMN_PIN};
        String where = "ROWID = ?";

        Cursor userDataCursor = db.query(DataBaseContract.UserDataEntry.TABLE_NAME,
                columns,
                where,
                new String[]{"1"},
                null,
                null,
                null);

        loadUser(userDataCursor);
        db.close();
        oh.close();

        return userData;
    }

    private static void loadUser(Cursor cursor) {
        int usrNamePos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_NAME);
        int usrMailPos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_EMAIL);
        int usrPinPos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_PIN);

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            String name = cursor.getString(usrNamePos);
            String email = cursor.getString(usrMailPos);
            Integer pin = cursor.getInt(usrPinPos);

            userData = new UserDataPojo(name, email, pin);

            cursor.close();
        }
    }

    public void registerUser(String name, String email, Integer PIN, Context ctx){

        OpenHelper oh = new OpenHelper(ctx);

        SQLiteDatabase db = oh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseContract.UserDataEntry.COLUMN_NAME, name);
        values.put(DataBaseContract.UserDataEntry.COLUMN_EMAIL, email);
        values.put(DataBaseContract.UserDataEntry.COLUMN_PIN, PIN);

        db.insert(DataBaseContract.UserDataEntry.TABLE_NAME, null, values);
        db.close();
        oh.close();
    }
}
