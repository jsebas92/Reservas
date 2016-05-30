package com.example.sebastian.myapplication;

import java.util.ArrayList;

/**
 * Created by sebastian on 28/05/16.
 */
public class Movie {
    private String title, thumbnailUrl;
    private int id;
    private String direccion;
    private ArrayList<String> genre;

    public Movie() {
    }

    public Movie(int id, String name, String thumbnailUrl, String direccion) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.id=id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

}


