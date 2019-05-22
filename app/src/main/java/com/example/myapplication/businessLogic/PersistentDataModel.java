package com.example.myapplication.businessLogic;

import android.support.annotation.NonNull;

public class PersistentDataModel {
    private String toShow;
    private Long id;
    private Class entity;

    public PersistentDataModel(String toShow, Long id, Class entity) {
        this.toShow = toShow;
        this.id = id;
        this.entity = entity;
    }

    public String getToShow() {
        return toShow;
    }

    public void setToShow(String toShow) {
        this.toShow = toShow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Class getEntity() {
        return entity;
    }

    public void setEntity(Class entity) {
        this.entity = entity;
    }

    @NonNull
    @Override
    public String toString() {
        return getToShow();
    }
}
