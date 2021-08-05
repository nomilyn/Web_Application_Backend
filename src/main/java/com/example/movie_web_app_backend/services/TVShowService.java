package com.example.movie_web_app_backend.services;

import com.example.movie_web_app_backend.CustomizedResponse;
import com.example.movie_web_app_backend.models.Movie;
import com.example.movie_web_app_backend.models.TVShow;
import com.example.movie_web_app_backend.models.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TVShowService {
    @Autowired
    private TVShowRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertIntoTVShows(TVShow tvShow) {
        repository.insert(tvShow);
    }

    public List<TVShow> getTVShows() {
        //business logic
        return repository.findAll();
    }

    public Optional<TVShow> getATVShow(String id) throws Exception {
        Optional <TVShow> tvShow = repository.findById(id);
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
        repository.deleteById(id);
    }

    public TVShow editATVShow(String id, TVShow newTVShowData) {

        //get the resource based on the id
        Optional<TVShow> tvshow = repository.findById(id);

        //validation code to validate the id
        tvshow.get().setTitle(newTVShowData.getTitle());
        tvshow.get().setDescription(newTVShowData.getDescription());
        tvshow.get().setPoster(newTVShowData.getPoster());
        tvshow.get().setsPoster(newTVShowData.getsPoster());
        tvshow.get().setPriceRent(newTVShowData.getPriceRent());
        tvshow.get().setPurchasePrice(newTVShowData.getPurchasePrice());
        tvshow.get().setIsFeaturedTVShow(newTVShowData.getIsFeaturedTVShow());

        //update the found resource with the new data
        TVShow updateTVShow = repository.save(tvshow.get());

        return updateTVShow; //commit the update by saving it
    }
}
