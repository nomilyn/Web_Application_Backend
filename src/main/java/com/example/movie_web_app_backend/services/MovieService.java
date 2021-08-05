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

    public void insertIntoMovies(Movie movie) {
        repository.insert(movie);
    }

    public List<Movie> getMovies() {
        //validation
        //calculation
        //call your model
        //business logic
        return repository.findAll();
    }

    public Optional<Movie> getAMovie(String id) throws Exception {
        Optional <Movie> movie = repository.findById(id);
        //This is saying that if movie ref variable does not contain a value then
        if(!movie.isPresent()) { //movie is not present
            throw new Exception("Movie with id " + id + " is not found");
        }
        return movie;
    }

    public List<Movie> getFeaturedMovies(String f) {
        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("isFeaturedMovie").is(f));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getTitleMovies(String f) {
        //business logic
        Query query = new Query();
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    // Not Working
    /*public Optional<Movie> deleteAMovie(String id) throws Exception {
        Optional <Movie> movie = repository.findById(id);
        if(!movie.isPresent()) { //movie is not present
            throw new Exception("Movie with id " + id + " is not found");
        }
        return movie;
    }*/
//Working
    public void deleteAMovie(String id) {
        repository.deleteById(id);
    }

    public Movie editAMovie(String id, Movie newMovieData) {

        //get the resource based on the id
        Optional<Movie> movie = repository.findById(id);

        //validation code to validate the id
        movie.get().setTitle(newMovieData.getTitle());
        movie.get().setDescription(newMovieData.getDescription());
        movie.get().setPoster(newMovieData.getPoster());
        movie.get().setsPoster(newMovieData.getsPoster());
        movie.get().setPriceRent(newMovieData.getPriceRent());
        movie.get().setPurchasePrice(newMovieData.getPurchasePrice());
        movie.get().setIsFeaturedMovie(newMovieData.getIsFeaturedMovie());

        //update the found resource with the new data
        Movie updateMovie = repository.save(movie.get());

        return updateMovie; //commit the update by saving it
    }
}
