package com.feedback.feedback.movies;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieReader {

    @Autowired
    private FeedbackService service;


    public List<Feedback> uploadMovieComments() throws IOException {
        String movieTitle = "Phantom Thread";
        File file = new File("D:\\FACULTATE\\LICENTA\\dataset\\Phantom_Thread.csv");
        MappingIterator<Movie> personIter = new CsvMapper().readerWithTypedSchemaFor(Movie.class).readValues(file);
        Comparator<Feedback> comparator = Comparator.comparing(Feedback::getLikes);
        List<Feedback> comments = personIter.readAll()
                .stream()
                .map(m ->
                        new Feedback(
                                null,
                                movieTitle, //title
                                m.getCommentText().replaceAll("[\\t\\n\\r]+", "").replaceAll("[^\\p{L}\\p{N}\\p{P}\\p{Z}]", ""), //content
                                movieTitle, //productCode
                                null,
                                (long) m.getLikes(), //likes
                                Instant.now().toEpochMilli()))
                .sorted(comparator.reversed())
                .collect(Collectors.toList());
        //TODO: last one 600->700
        List<Feedback> sendToBeSaved = comments.subList(1040,1155);
        String tenant="movies";
        sendToBeSaved.forEach(m->service.save(m,tenant));
        return comments;
    }
}
