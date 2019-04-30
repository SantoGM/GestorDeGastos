package com.example.myapplication.database.dao;

public class CategoryDAO extends AbstractDAO {

    private CategoryDAO parent;
    private String name;
    private String description;


    public CategoryDAO(Long id, CategoryDAO parent, String name, String description) {
        this.setId(id);
        this.parent = parent;
        this.name = name;
        this.description = description;
    }


    public CategoryDAO getParent() {
        return parent;
    }
    public void setParent(CategoryDAO parent) {
        this.parent = parent;
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
