package com.example.movie_web_app_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies") //name of the collection
public class Movie {
    //POJO / Bean / Entity
    @Id
    private String id;
    private String title;
    private String description;
    private String poster;
    private String sPoster;
    private String priceRent;
    private String purchasePrice;
    private String isFeaturedMovie;

    public Movie() {
    }

    public Movie(String id, String title, String description, String poster, String sPoster, String priceRent, String purchasePrice, String isFeaturedMovie) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.poster = poster;
        this.sPoster = sPoster;
        this.priceRent = priceRent;
        this.purchasePrice = purchasePrice;
        this.isFeaturedMovie = isFeaturedMovie;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getsPoster() {
        return sPoster;
    }

    public void setsPoster(String sPoster) {
        this.sPoster = sPoster;
    }

    public String getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(String priceRent) {
        this.priceRent = priceRent;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getIsFeaturedMovie() {
        return isFeaturedMovie;
    }

    public void setIsFeaturedMovie(String isFeaturedMovie) {
        this.isFeaturedMovie = isFeaturedMovie;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", sPoster='" + sPoster + '\'' +
                ", priceRent='" + priceRent + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", isFeaturedMovie='" + isFeaturedMovie + '\'' +
                '}';
    }
}

