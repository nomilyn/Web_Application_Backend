package com.example.movie_web_app_backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface TVShowRepository extends MongoRepository<TVShow, String>{
}
