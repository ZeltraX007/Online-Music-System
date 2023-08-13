package com.example.musicappserver.Model;

public class Uploads {

    private String name;
    private String url;
    private String songsCategory;

    public Uploads(String name, String url, String songsCategory) {
        this.name = name;
        this.url = url;
        this.songsCategory = songsCategory;
    }

    public Uploads() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSongsCategory() {
        return songsCategory;
    }

    public void setSongsCategory(String songsCategory) {
        this.songsCategory = songsCategory;
    }
}
