package com.feedback.feedback.movies;

import com.feedback.feedback.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieReader reader;

    @GetMapping(value = "/import-movie")
    public List<Feedback> importMovie(){
        try {
            return reader.uploadMovieComments();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
