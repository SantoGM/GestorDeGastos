package com.example.myapplication.view.pojo;

import java.util.Date;

public interface MovementPojo {

    Long getId();
    Date getDate();
    Float getAmount();
    String getDetail();
    String[] showDetails();
}
