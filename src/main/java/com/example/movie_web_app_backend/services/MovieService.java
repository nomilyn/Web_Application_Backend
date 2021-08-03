package com.example.movie_web_app_backend.services;

import com.example.movie_web_app_backend.models.Movie;
import com.example.movie_web_app_backend.models.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> getMovies() {
        //validation
        //calculation
        //call your model
        //business logic
        return repository.findAll();
    }

    public List<Movie> getMoviesWithRating(String r) {
        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("rating").is(r));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public void insertIntoMovies(Movie movie) {
        repository.insert(movie);
    }

    public Optional<Movie> getAMovie(String id) throws Exception {
        Optional <Movie> movie = repository.findById(id);
        //This is saying that if movie ref variable does not contain a value then
        if(!movie.isPresent()) { //movie is not present
            throw new Exception("Movie with id " + id + " is not found");
        }
        return movie;
    }

    public void deleteAMovie(String id) {
        repository.deleteById(id);
    }
}
