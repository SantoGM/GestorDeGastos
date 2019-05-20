package com.example.myapplication.businessLogic;

import com.example.myapplication.view.pojo.AccountPojo;

import java.util.ArrayList;

public class AbstractFacade {

    public ArrayList<PersistentDataModel> buildDataModel(String[] list, Class clazz) {
        ArrayList<PersistentDataModel> result = new ArrayList<>();
        for (int i = 0; i < list.length; i++){
            PersistentDataModel sm = new PersistentDataModel(list[i], i, clazz);
            result.add(sm);
        }
        return result;
    }
}
