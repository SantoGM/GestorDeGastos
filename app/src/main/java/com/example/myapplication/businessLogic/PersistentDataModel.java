package com.example.myapplication.businessLogic;

public class PersistentDataModel {
    private String toShow;
    private Integer id;
    private Class entity;

    public PersistentDataModel(String toShow, Integer id, Class entity) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Class getEntity() {
        return entity;
    }

    public void setEntity(Class entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return getToShow();
    }
}
