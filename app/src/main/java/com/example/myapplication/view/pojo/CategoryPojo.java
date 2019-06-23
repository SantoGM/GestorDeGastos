package com.example.myapplication.view.pojo;

/**
 * Class which contains the representation of a category for the <i>view</i>
 */
public class CategoryPojo extends AbstractPojo{

    private String name;
    private String description;
    private Integer disable;


    public CategoryPojo() {
    }

    public CategoryPojo(Long id, String name, String description) {
        this.setId(id);
        this.name = name;
        this.description = description;
    }

    public CategoryPojo(Long id, String name, String description, Integer disable) {
        this.setId(id);
        this.name = name;
        this.description = description;
        this.disable = disable;
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
    public Integer getDisable() {
        return disable;
    }
    public void setDisable(Integer disable) {
        this.disable = disable;
    }


    @Override
    public String nameToShow() {
        return getName();
    }
}
