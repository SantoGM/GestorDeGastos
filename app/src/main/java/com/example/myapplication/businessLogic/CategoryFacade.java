package com.example.myapplication.businessLogic;

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

    public ArrayList<PersistentDataModel> getCategoryDataModel() {
        return buildDataModel(new String[] {"Servicios", "Conga", "Super", "Estupefacientes"}, CategoryPojo.class);
    }
}
