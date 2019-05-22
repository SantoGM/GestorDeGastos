package com.example.myapplication.view.pojo;

public abstract class AbstractPojo {

    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public abstract String nameToShow();
}
