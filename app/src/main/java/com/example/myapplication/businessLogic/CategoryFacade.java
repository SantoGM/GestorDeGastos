package com.example.myapplication.businessLogic;

import android.content.Context;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dataManager.CategoryManager;
import com.example.myapplication.view.pojo.CategoryPojo;

import java.util.ArrayList;

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

    public ArrayList<PersistentDataModel> getCategoryDataModel(Context ctx) {
        OpenHelper oh = new OpenHelper(ctx);
        CategoryManager.getInstance().loadFromDB(oh);
        return buildDataModel(CategoryManager.getInstance().getCategories(), CategoryPojo.class);
    }
}
