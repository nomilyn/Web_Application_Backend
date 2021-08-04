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

    @PostMapping(value = "/movies", consumes = { //consume -> sending data to the body of the request
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        service.insertIntoMovies(movie);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity getmovies() {
        var customizedResponse = new CustomizedResponse("A list of movies", service.getMovies());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getAMovie(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Movie with id " +id, Collections.singletonList((service.getAMovie(id))));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/isFeaturedMovie")
    public ResponseEntity getFeaturedMovies(@RequestParam(value = "featured") String f) {
        var customizedResponse = new CustomizedResponse("A list of featured movies ", service.getFeaturedMovies(f));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/title")
    public ResponseEntity getTitleMovies(@RequestParam(value = "title") String f) {
        var customizedResponse = new CustomizedResponse("A list of movies where title contains " + f, service.getTitleMovies(f));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
/* Not Working with a Message
    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteAMovie(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Movie id with "+ id+ " has been deleted", Collections.singletonList((service.deleteAMovie(id))));
        }
        catch(Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
*/
    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteAMovie(@PathVariable("id") String id) {
        service.deleteAMovie(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity updateAMovie(@RequestBody Movie movie) {
        service.updateAMovie(movie);
        return new ResponseEntity(HttpStatus.OK);
    }
}
