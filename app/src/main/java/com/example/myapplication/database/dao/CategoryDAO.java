package com.example.myapplication.database.dao;

public class CategoryDAO extends AbstractDAO {

    private String name;
    private String description;


    public CategoryDAO(Long id, String name, String description) {
        this.setId(id);
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
