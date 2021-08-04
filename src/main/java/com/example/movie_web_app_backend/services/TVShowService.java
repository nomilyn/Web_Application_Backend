package com.example.movie_web_app_backend.services;

import com.example.movie_web_app_backend.models.TVShow;
import com.example.movie_web_app_backend.models.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TVShowService {
    @Autowired
    private TVShowRepository repositoryTV;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertIntoTVShows(TVShow tvShow) {
        repositoryTV.insert(tvShow);
    }

    public List<TVShow> getTVShows() {
        //business logic
        return repositoryTV.findAll();
    }

    public Optional<TVShow> getATVShow(String id) throws Exception {
        Optional <TVShow> tvShow = repositoryTV.findById(id);
        //This is saying that if movie ref variable does not contain a value then
        if(!tvShow.isPresent()) { //movie is not present
            throw new Exception("TV Show with id " + id + " is not found");
        }
        return tvShow;
    }

    public List<TVShow> getFeaturedTVShows(String f) {
        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("isFeaturedTVShow").is(f));
        List<TVShow> tvShows = mongoTemplate.find(query, TVShow.class);
        return tvShows;
    }

    public List<TVShow> getTitleTVShows(String f) {
        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(f));
        List<TVShow> tvShows = mongoTemplate.find(query, TVShow.class);
        return tvShows;
    }

    /* Not Working
    public Optional<TVShow> deleteATVShow(String id) throws Exception {
        Optional <TVShow> tvShow = repositoryTV.findById(id);
        if(!tvShow.isPresent()) { //movie is not present
            throw new Exception("TV Show with id " + id + " is not found");
        }
        return tvShow;
    } */
//Working
    public void deleteATVShow(String id) {
        repositoryTV.deleteById(id);
    }

    public void updateATVShow(TVShow tvShow) {
        repositoryTV.save(tvShow);
    }
}
