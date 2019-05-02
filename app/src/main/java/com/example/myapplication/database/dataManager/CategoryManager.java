package com.example.myapplication.database.dataManager;

public class CategoryManager {

    private static CategoryManager categoryManager = null;

    private Long id;
    private String name;
    private String description;
    private CategoryManager parent;


    private CategoryManager() {
    }

    public static CategoryManager getInstance() {
        if (categoryManager == null) {
            categoryManager = new CategoryManager();
        }
        return categoryManager;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public CategoryManager getParent() {
        return parent;
    }
    public void setParent(CategoryManager parent) {
        this.parent = parent;
    }

}
