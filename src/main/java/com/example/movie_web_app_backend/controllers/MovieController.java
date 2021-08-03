package com.example.movie_web_app_backend.controllers;

import com.example.movie_web_app_backend.CustomizedResponse;
import com.example.movie_web_app_backend.models.Movie;
import com.example.movie_web_app_backend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping("/movies")
    public ResponseEntity getmovies() {
        var customizedResponse = new CustomizedResponse("A list of movies", service.getMovies());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    // /movies/rating?rating=PG_13 &limit = 15
    @GetMapping("/movies/rating")
    public ResponseEntity getmoviesByRating(@RequestParam(value = "rate") String r) {
        var customizedResponse = new CustomizedResponse("A list of movies with the rating", service.getMoviesWithRating(r));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getAMovie(@PathVariable("id") String id) {
        //System.out.println(id);
        //System.out.println(service.getAMovie(id));
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Movie with id " +id, Collections.singletonList((service.getAMovie(id))));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteAMovie(@PathVariable("id") String id) {
        service.deleteAMovie(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/movies", consumes = { //consume -> sending data to the body of the request
            MediaType.APPLICATION_JSON_VALUE
    })

    public ResponseEntity addMovie(@RequestBody Movie movie) {
        service.insertIntoMovies(movie);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

}
