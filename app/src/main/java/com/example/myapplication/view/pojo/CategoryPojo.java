package com.example.myapplication.view.pojo;

public class CategoryPojo extends AbstractPojo{

    private String name;
    private String description;


    public CategoryPojo() {
    }

    public CategoryPojo(Long id, String name, String description) {
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

    @Override
    public String nameToShow() {
        return getName();
    }
}
