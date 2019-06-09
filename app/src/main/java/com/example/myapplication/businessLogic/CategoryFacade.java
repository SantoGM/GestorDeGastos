package com.example.myapplication.businessLogic;

import android.content.Context;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dataManager.AccountManager;
import com.example.myapplication.database.dataManager.CategoryManager;
import com.example.myapplication.database.dataManager.MovementManager;
import com.example.myapplication.view.pojo.AccountPojo;
import com.example.myapplication.view.pojo.CategoryPojo;
import com.example.myapplication.view.pojo.PaymentPojo;

import java.util.ArrayList;
import java.util.Date;

public class CategoryFacade extends AbstractFacade{

    private static CategoryFacade instance;

    private CategoryFacade(){
        // Singleton
    }

    public static CategoryFacade getInstance(){
        if (instance == null){
            instance = new CategoryFacade();
        }
        return instance;
    }


    public void saveCategory(String name, String description,Context ctx){

        OpenHelper dbHelper = new OpenHelper(ctx);

        CategoryPojo category = new CategoryPojo(null, name, description);

        CategoryManager cm  = new CategoryManager();

        cm.insertCategory(dbHelper, category);

        dbHelper.close();
        setChanged();
        notifyObservers();
    }

    public ArrayList<PersistentDataModel> getCategoryDataModel(Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);
        CategoryManager cm = new CategoryManager();
        ArrayList<PersistentDataModel> result = buildDataModel(cm.getCategories(oh), CategoryPojo.class);
        oh.close();
        return result;
    }
}
