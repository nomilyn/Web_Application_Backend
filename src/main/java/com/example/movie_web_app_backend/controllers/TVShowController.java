package com.example.movie_web_app_backend.controllers;

import com.example.movie_web_app_backend.CustomizedResponse;
import com.example.movie_web_app_backend.models.TVShow;
import com.example.movie_web_app_backend.services.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TVShowController {
    @Autowired
    private TVShowService service;

    @PostMapping(value = "/tvshows", consumes = { //consume -> sending data to the body of the request
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addTVShow(@RequestBody TVShow tvShow) {
        service.insertIntoTVShows(tvShow);
        return new ResponseEntity(tvShow, HttpStatus.OK);
    }

    @GetMapping("/tvshows")
    public ResponseEntity gettvshows() {
        var customizedResponse = new CustomizedResponse("A list of TV Shows", service.getTVShows());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/tvshows/{id}")
    public ResponseEntity getATVShow(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("TV Show with id " +id, Collections.singletonList((service.getATVShow(id))));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/tvshows/isFeaturedTVShow")
    public ResponseEntity getFeaturedTVShows(@RequestParam(value = "featured") String f) {
        var customizedResponse = new CustomizedResponse("A list of featured TV shows ", service.getFeaturedTVShows(f));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/tvshows/title")
    public ResponseEntity getTitleTVShows(@RequestParam(value = "title") String f) {
        var customizedResponse = new CustomizedResponse("A list of TV Shows where title contains " + f, service.getTitleTVShows(f));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    /* Not Working with a Message
        @DeleteMapping("/tvshows/{id}")
        public ResponseEntity deleteATVShow(@PathVariable("id") String id) {
            CustomizedResponse customizedResponse = null;
            try {
                customizedResponse = new CustomizedResponse("Movie id with "+ id+ " has been deleted", Collections.singletonList((serviceTV.deleteATVShow(id))));
            }
            catch(Exception e) {
                customizedResponse = new CustomizedResponse(e.getMessage(), null);
                return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }
    */

    @DeleteMapping("/tvshows/{id}")
    public ResponseEntity deleteATVShow(@PathVariable("id") String id) {
        service.deleteATVShow(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/tvshows/{id}",consumes = { //consume -> sending data to the body of the request
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editATVShow(@PathVariable("id") String id, @RequestBody TVShow newTVShow) {
        var customizedResponse = new CustomizedResponse(" TV Show with ID: " + id + " was updated successfully " , Collections.singletonList(service.editATVShow(id, newTVShow)));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
