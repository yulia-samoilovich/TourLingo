package com.example.tourlingo.model;

public class Country {
    private String country;
    private String comment;
    private String date;

    public Country(String country, String comment, String date) {
        this.country = country;
        this.comment = comment;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
