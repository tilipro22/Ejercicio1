package com.tili.model;

public class Item {

    private String id;
    private String title;
    private String category_id;
    private String name;

    public Item(String id, String title, String category_id, String name) {
        this.id = id;
        this.title = title;
        this.category_id = category_id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
