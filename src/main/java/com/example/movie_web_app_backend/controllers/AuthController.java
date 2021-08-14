package com.example.movie_web_app_backend.controllers;

import com.example.movie_web_app_backend.CustomizedResponse;
import com.example.movie_web_app_backend.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody UserModel user) {
        try {
            //This is for username and password
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            //This is for email and password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            var response = new CustomizedResponse( "You login Successfully", null);
            return new ResponseEntity( response, HttpStatus.OK);
        }
        catch (BadCredentialsException ex) {
            var response = new CustomizedResponse( "Your email/password were entered incorrectly..", null);
            return new ResponseEntity( response, HttpStatus.UNAUTHORIZED);
        }
    }
}
