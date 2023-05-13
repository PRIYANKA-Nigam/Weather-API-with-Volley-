package com.example.weatherapp.News;

public class NewsModel {
    String image,title,date,bulk,description,content;

    public NewsModel(String image, String title, String date, String bulk) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.bulk = bulk;
    }

    public NewsModel(String image, String title, String date, String bulk, String description, String content) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.bulk = bulk;
        this.description = description;
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getBulk() {
        return bulk;
    }
}
