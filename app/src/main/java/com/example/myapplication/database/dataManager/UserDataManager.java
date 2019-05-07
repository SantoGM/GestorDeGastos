package com.example.myapplication.database.dataManager;

import android.database.Cursor;

import com.example.myapplication.database.DataBaseContract;

public class UserDataManager {

    private static UserDataManager instance;

    private UserDataManager(){
    }

    public static UserDataManager getInstance(){
        if (instance == null){
            instance = new UserDataManager();
        }
        return instance;
    }

    public static void getUserData(){

    }

    private static void loadUser(Cursor cursor) {
        int usrNamePos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_NAME);
        int usrMailPos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_EMAIL);
        int usrPinPos = cursor.getColumnIndex(DataBaseContract.UserDataEntry.COLUMN_PIN);

        String name = cursor.getString(usrNamePos);
        String email = cursor.getString(usrMailPos);
        Integer pin = cursor.getInt(usrPinPos);

        //AccountDAO accountDAO = new AccountDAO(id, name, description, balance);

        cursor.close();
    }
}
