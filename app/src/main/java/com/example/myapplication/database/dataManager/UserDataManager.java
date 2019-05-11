package com.example.myapplication.database.dataManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.service.autofill.UserData;

import com.example.myapplication.database.DataBaseContract;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.UserDataPojo;

public class UserDataManager {

    private static UserDataManager instance;
    private static UserDataPojo userData;

    private UserDataManager(){
    }

    public static UserDataManager getInstance(OpenHelper oh){
        if (instance == null){
            instance = new UserDataManager();
        }

        getUserData(oh);

        return instance;
    }

    private static void getUserData(OpenHelper oh){

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
    }

    private static void loadUser(Cursor cursor) {
        int usrNamePos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_NAME);
        int usrMailPos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_EMAIL);
        int usrPinPos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_PIN);

        cursor.moveToNext();
        String name = cursor.getString(usrNamePos);
        String email = cursor.getString(usrMailPos);
        Integer pin = cursor.getInt(usrPinPos);

        userData = new UserDataPojo(name, email, pin);

        cursor.close();
    }

    public UserDataPojo getUserData(){
        return userData;
    }
}
