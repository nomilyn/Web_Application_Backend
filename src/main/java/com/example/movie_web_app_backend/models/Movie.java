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
    private String rating;

    public Movie() {
    }

    public Movie(String id, String title, String description, String rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}

