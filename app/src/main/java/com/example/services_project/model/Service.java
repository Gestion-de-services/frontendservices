package com.example.services_project.model;

public class Service {
    private int id;
    private String category;
    private String title;
    private String description;
    private int imageResId;

    public Service(int id, String category, String title, String description, int imageResId) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public int getId() { return id; }
    public String getCategory() { return category; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getImageResId() { return imageResId; }
}
