package com.example.myapplication.businessLogic;

import com.example.myapplication.view.pojo.AbstractPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AbstractFacade extends Observable {

    public ArrayList<PersistentDataModel> buildDataModel(List<? extends AbstractPojo> list, Class clazz) {
        ArrayList<PersistentDataModel> result = new ArrayList<>();

        for (AbstractPojo pojo: list) {
            PersistentDataModel sm = new PersistentDataModel(pojo.nameToShow(), pojo.getId(), clazz);
            result.add(sm);
        }
        return result;
    }
}
